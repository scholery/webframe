package com.frame.email;

import org.apache.log4j.Logger;
import org.vaniglia.messagequeue.MessageQueue;
import org.vaniglia.messagequeue.storage.MessageStorageParameters;
import org.vaniglia.messagequeue.storage.MessageStorageType;

import com.frame.util.SystemPath;


public class EmailSenderQueue {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(EmailSenderQueue.class);

	/**
	 * Email Queue
	 */
	private static MessageQueue emailQueue;
	
	private static boolean debug = false;

	/**
	 * Start Email Queue
	 * 
	 * @throws Exception
	 */
	public static void start(EmailSenderCallBack aCallBack) throws Exception {

		try {
			String path = SystemPath.getWebInfPath() +"/messageQueue";
			emailQueue = MessageQueue.getQueue("EmailQueue",
					MessageStorageType.FileSystemType, MessageStorageParameters
							.createFileSystemStorageParameters(
									"EmailQueueStorage", path));

			emailQueue.start();

			emailQueue.subscribe(new EmailMessageListener(aCallBack));

			logger.info("Email Queue Started Success");

		} catch (Exception e) {
			logger.error("Email Queue Started Fail");
			throw e;
		}

	}

	/**
	 * Publish Mail Message
	 * 
	 * @param aMessage
	 * @throws Exception
	 */
	public static void putMessageToQueue(EmailMessage aMessage)
			throws Exception {
		try {
			logger.debug("publish email message");
			emailQueue.publish(aMessage);
		} catch (Exception e) {
			logger.error("publish email message error", e);
			throw e;
		}

	}

	/**
	 * Stop Email Queue
	 * 
	 * @throws Exception
	 */
	public static void stop() throws Exception {
		try {
			logger.debug("stop email queue");
			emailQueue.stop();
		} catch (Exception e) {
			logger.error("stop email queue error", e);
			throw e;
		}

	}

	/**
	 * Get Queue Size
	 * 
	 * @return
	 */
	public static long getQueueSize() {
		return emailQueue.size();
	}

	public static boolean isDebug() {
		return debug;
	}

	public static void setDebug(boolean debug) {
		EmailSenderQueue.debug = debug;
	}

}
