package com.frame.web;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	/**
	 * Note:
	 * 		Tomcat自动产生的session对象并不都是我们需要的有效登陆会话
	 * 		因此,必须启动计时器,对每个session作特定属性的查询
	 * 		比如,当用户登陆后,给session添加一个新属性"UserName"
	 * 
	 * 		SessionListener有时会无效(修改了类代码后),这时需要删除work目录,并重新启动tomcat!
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		//SessionManager.getInstance().addSession(arg0.getSession().getId(), arg0.getSession());
	}

	/*  Javadoc
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		SessionManager.getInstance().removeSession(arg0.getSession().getId());
	}
}
