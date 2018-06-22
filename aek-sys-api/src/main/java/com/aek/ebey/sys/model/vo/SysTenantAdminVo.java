package com.aek.ebey.sys.model.vo;

/**
 * 租户信息查询辅助类
 * 
 * @author Mr.han
 *
 */
public class SysTenantAdminVo {
	/**
	 * 管理员姓名
	 */
	private String realName;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
