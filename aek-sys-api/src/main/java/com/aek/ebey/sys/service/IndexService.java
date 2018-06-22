package com.aek.ebey.sys.service;


public interface IndexService {
	/**
	 * 找回密码发送验证码
	 * 
	 * @param account
	 *            账户(密码或者手机号)
	 * @return
	 */
	void sendCode(String account);
	
	/**
	 * 注册时发送验证码
	 * 
	 * @param account
	 * 				账户(密码或者手机号)
	 */
	void sendRstCode(String account);

	/**
	 * 重置密码
	 * 
	 * @param account
	 *            账户(密码或者手机号)
	 * @param code
	 *            验证码
	 * @param password
	 *            新密码
	 * @return
	 */
	boolean resetPassword(String account, String code, String password);

}
