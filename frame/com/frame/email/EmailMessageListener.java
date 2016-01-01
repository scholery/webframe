/**
 * Copyright (C) 2000-2011 Nanjing Fujitsu Nanda Software Tech. Co.Ltd.(FNST) All rights reserved.
 */
package com.frame.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.mail.Transport;

import org.apache.log4j.Logger;
import org.vaniglia.messagequeue.Message;
import org.vaniglia.messagequeue.MessageListener;
import org.vaniglia.messagequeue.MessageQueueException;

/**
 * Email Queue Handle
 * 
 * @author kylin
 * @since 2008-2-21
 */
public class EmailMessageListener implements MessageListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(EmailMessageListener.class);

	private EmailSenderCallBack callBack;

	public EmailMessageListener(EmailSenderCallBack callBack) {
		super();
		this.callBack = callBack;
	}

	@Override
	public void handle(Message aMessage) throws MessageQueueException {

		try {

			logger.debug("process mail send");

			javax.mail.Message message = ((EmailMessage) aMessage)
					.getSerializableMessage().toMialMessage();

			if (message != null) {

				if (((EmailMessage) aMessage).isDebug() == false) {
					Transport.send(message);
					logger.debug("send mail success:"+message.getSubject());
				} else {
					logger.debug("-------Send Message " + new Date() + "---");
					logger.debug(((EmailMessage) aMessage)
							.getSerializableMessage().toString());
					logger.debug("---------------------------------------");
					logger.debug("-------Send Message Body" + new Date()
							+ "---");
					logger.debug(((EmailMessage) aMessage)
							.getSerializableMessage().getBody());
					logger.debug("---------------------------------------");

					String mailFileName = System.getProperty("user.dir")
							+ "/mail"
							+ System.currentTimeMillis()
							+ (((EmailMessage) aMessage)
									.getSerializableMessage().isSendAsHtml() ? ".html"
									: ".txt");

					logger.info("Write mail body to " + mailFileName);

					File mailFile = new File(mailFileName);

					if (mailFile.exists() == false) {
						mailFile.createNewFile();
					}

					OutputStream out = new FileOutputStream(mailFile);

					out.write(((EmailMessage) aMessage)
							.getSerializableMessage().getBody().getBytes());

					out.flush();
					out.close();
				}

				if (this.callBack != null) {
					this.callBack.onSuccess((EmailMessage) aMessage);
				}
			} else {
				logger.warn("Mail Message Is Null");
			}

			logger.debug("email sender finished");

		} catch (Exception e) {
			logger.error("send mail fail", e);
			if (this.callBack != null) {
				this.callBack.onFail((EmailMessage) aMessage, e);
			}
		}

	}
}
