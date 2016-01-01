package com.frame.email;

/**
 * Email Send Callback
 * 
 * @author kylin
 * @since 2008-2-21
 */
public interface EmailSenderCallBack {

	/**
	 * Handle Sueccess
	 * 
	 * @param aMessage
	 */
	public void onSuccess(EmailMessage aMessage);

	/**
	 * Handle Fail
	 * 
	 * @param aMessage
	 */
	public void onFail(EmailMessage aMessage, Exception aException);
}
