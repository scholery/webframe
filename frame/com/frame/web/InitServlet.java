package com.frame.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.frame.email.EmailMessage;
import com.frame.email.EmailSenderCallBack;
import com.frame.email.EmailSenderQueue;

public class InitServlet extends HttpServlet {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			EmailSenderQueue.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			EmailSenderQueue.start(new EmailSenderCallBack() {
				
				@Override
				public void onSuccess(EmailMessage aMessage) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onFail(EmailMessage aMessage, Exception aException) {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
