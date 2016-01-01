/**
 * Copyright (C) 2000-2010 Nanjing Fujitsu Nanda Software Tech. Co.Ltd.(FNST) All rights reserved.
 */
/**
 * 
 */
package com.frame.email;

import org.apache.log4j.Logger;

/**
 * @author kylin Mail Queue Tester
 */
public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			EmailSenderQueue.start(new EmailSenderCallBack() {

				@Override
				public void onFail(EmailMessage message, Exception aException) {
					logger.error("Fail", aException);
				}

				@Override
				public void onSuccess(EmailMessage message) {
					logger.debug("Sucess");

				}

			});

			MyQueuePublisher myQueuePublisher = new MyQueuePublisher();
			Thread publishingThread = new Thread(myQueuePublisher);
			publishingThread.setDaemon(true);
			logger.info("Starting the publisher.");
			publishingThread.run();
			
			myQueuePublisher.shutdown();

			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
			}

			logger.info("Stopping the publisher.");
			myQueuePublisher.shutdown();

			try {
				publishingThread.join();
			} catch (InterruptedException e) {
			}

			try {
				Thread.sleep(22000);
			} catch (InterruptedException e) {
			}

			EmailSenderQueue.stop();

			if (EmailSenderQueue.getQueueSize() != 0) {
				System.err.println("Queue should be empty but still contains "
						+ EmailSenderQueue.getQueueSize() + " messages.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
