package com.aek.ebey.sys.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.config.RedisRepository;
import com.aek.common.core.exception.BusinessException;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.sms.Sms;
import com.aek.common.core.sms.SmsResult;
import com.aek.common.core.util.SecurityUtil;
import com.aek.ebey.sys.core.SysConstants;
import com.aek.ebey.sys.enums.AccountType;
import com.aek.ebey.sys.model.SysUser;
import com.aek.ebey.sys.model.WxSysUser;
import com.aek.ebey.sys.model.bo.VerifyCode;
import com.aek.ebey.sys.service.EmailSendService;
import com.aek.ebey.sys.service.IndexService;
import com.aek.ebey.sys.service.SysUserService;
import com.aek.ebey.sys.service.WxSysUserService;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class IndexServiceImpl implements IndexService {

	@Autowired
	private SysUserService userService;

	@Autowired
	private Sms sms;

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private EmailSendService emailSenService;
	
	@Autowired
	private WxSysUserService wxSysUserService;

	@Override
	public void sendCode(String account) {

		// 查询是否在1分钟内发送过验证码，则不发送返回1分钟重试
		Date currTime = new Date();
		String key = SysConstants.SYS_GET_PASSWORD_CODE + account;

		String verifyCodeJson = this.redisRepository.get(key);
		if (StringUtils.isNotBlank(verifyCodeJson)) {

			VerifyCode currVerifyCode = JSON.parseObject(verifyCodeJson, VerifyCode.class);
			long date = currTime.getTime() - currVerifyCode.getSendTime().getTime();

			if ((date / 60000) < 1) {
				throw ExceptionFactory.create("U_019");
			}
		}

		// 判断账户类型 1 手机号，2邮箱
		int accountType = 0;
		SysUser user = this.userService.findByMobile(account);
		if (user != null) {
			accountType = AccountType.MOBILE.getNumber();
		} else {
			user = this.userService.findByEmail(account);
			if (user != null) {
				accountType = AccountType.EMAIL.getNumber();
			} else {
				throw ExceptionFactory.create("U_009");
			}
		}

		// 随机数6位
		String code = this.sms.randomCode(6);

		// 手机验证码
		if (accountType == AccountType.MOBILE.getNumber()) {
			SmsResult smsResult = this.sms.sendCode(account, code);
			if(null != smsResult){
				if(smsResult.getSuccess() != 1){
					if(smsResult.getErrcode() == 32){
						throw ExceptionFactory.create("U_022");
					}else{
						throw new BusinessException(smsResult.getMsg());
					}
				}
			} else {
				throw new BusinessException("验证码发送失败");
			}
		}

		// 邮箱发送验证码
		if (accountType == AccountType.EMAIL.getNumber()) {
			if (!this.emailSenService.sendByResetPassword(account, code, user.getRealName())) {
				throw ExceptionFactory.create("U_028");
			}
		}

		// 过期时间 30分钟
		long expireTime = 30 * 60;
		VerifyCode verifyCode = new VerifyCode();
		verifyCode.setCode(code);
		verifyCode.setSendTime(currTime);

		// 保存验证码
		String value = JSON.toJSONString(verifyCode);
		this.redisRepository.setExpire(key, value, expireTime);
	}
	
	@Override
	public void sendRstCode(String account) {
		//校验手机号不为空
		if(account != null && account.length() > 0){
			//校验是否注册，已注册直接返回
			if(userService.findByMobile(account) != null){
				throw ExceptionFactory.create("U_029");
			}else{
				// 查询是否在1分钟内发送过验证码，则不发送返回1分钟重试
				Date currTime = new Date();
				String key = SysConstants.SYS_REGISTER_CODE + account;

				String verifyCodeJson = this.redisRepository.get(key);
				if (StringUtils.isNotBlank(verifyCodeJson)) {

					VerifyCode currVerifyCode = JSON.parseObject(verifyCodeJson, VerifyCode.class);
					long date = currTime.getTime() - currVerifyCode.getSendTime().getTime();

					if ((date / 60000) < 1) {
						throw ExceptionFactory.create("U_019");
					}
				}
				
				// 随机数6位
				String code = this.sms.randomCode(6);
				
				// 手机验证码
				SmsResult smsResult = this.sms.sendCode(account, code);
				if (smsResult.getSuccess() != 1) {
					throw ExceptionFactory.create("U_022");
				}
				
				// 过期时间 30分钟
				long expireTime = 30 * 60;
				VerifyCode verifyCode = new VerifyCode();
				verifyCode.setCode(code);
				verifyCode.setSendTime(currTime);

				// 保存验证码
				String value = JSON.toJSONString(verifyCode);
				this.redisRepository.setExpire(key, value, expireTime);
			};
			
		};
	
	}

	@Override
	public boolean resetPassword(String account, String code, String password) {

		SysUser user = this.userService.findByAccount(account);
		String key = SysConstants.SYS_GET_PASSWORD_CODE + account;

		// 查询验证码是否在30分钟有效期内，否则重试
		String verifyCode = this.redisRepository.get(key);

		if (StringUtils.isNotBlank(verifyCode)) {
			VerifyCode currVerifyCode = JSON.parseObject(verifyCode, VerifyCode.class);

			// 判断验证码是否正确
			if (!currVerifyCode.getCode().equals(code)) {
				throw ExceptionFactory.create("U_021");
			}

			// 判断密码是否为空
			if (StringUtils.isEmpty(password)) {
				throw ExceptionFactory.create("U_004");
			}

		} else {
			// 缓存验证码不存在，提示重新发送
			throw ExceptionFactory.create("U_020");
		}

		boolean flag = false;
		user.setPassword(this.userService.encrypt(password));
		if (this.userService.updateById(user)) {
			this.redisRepository.del(key);
			flag = true;
			
			//更新微信绑定用户密码
			WxSysUser wxSysUser = wxSysUserService.getWxSysUser(user.getId());
			if(null != wxSysUser){
				wxSysUser.setPassword(SecurityUtil.encryptDes(password));
				wxSysUserService.updateById(wxSysUser);
			}
		}
		
		return flag;
	}



}
