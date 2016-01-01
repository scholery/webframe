/**
 * Copyright (C) 2000-2010 Nanjing Fujitsu Nanda Software Tech. Co.Ltd.(FNST) All rights reserved.
 */
/**
 * Project Vaniglia
 * User: Michele Aiello
 *
 * Copyright (C) 2003/2007  Michele Aiello
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package com.frame.email;

import org.apache.log4j.Logger;

public class MyQueuePublisher implements Runnable {

	private static final Logger logger = Logger
			.getLogger(MyQueuePublisher.class);

	private boolean shutdown = false;

	public MyQueuePublisher() {

	}

	@Override
	public void run() {

		while (!shutdown) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}

			try {
				
				java.util.Properties properties = new java.util.Properties();
				properties.setProperty("mail.transport.protocol", "smtp");
				properties.setProperty("mail.host", "smtp.fnst.cn.fujitsu.com");
				properties.setProperty("mail.transport.pool-size", "1");
				properties.setProperty("mail.smtp.auth", "true");

//				javax.mail.Session session = javax.mail.Session.getInstance(
//						properties, new Authenticator() {
//							public PasswordAuthentication getPasswordAuthentication() {
//								return new PasswordAuthentication("yecui",
//										"ni hao");
//							}
//						});
				
//				javax.mail.Address address = new javax.mail.internet.InternetAddress(
//				"qilin@cn.fujitsu.com");
				
				SerializableMessage message = new SerializableMessage();
				message.setProperties(properties);
				message.setSMTPValidate("1");
				message.setUserName("yecui");
				message.setPwd("ni hao");
				message.setTo("qilin@cn.fujitsu.com");
				message.setFrom("spif@cn.fujitsu.com");
				
				message.setSubject("Test #" + 1);
				message.setBody("Test");
				message.setCode("text/html;charset=GBK");
				
				// EmailMessage message = new EmailMessage();
				EmailSenderQueue.putMessageToQueue(new EmailMessage(message,true));
			} catch (Exception e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}

			logger
					.info("Queue size is now: "
							+ EmailSenderQueue.getQueueSize());
		}
	}

	public void shutdown() {
		shutdown = true;
	}
}
