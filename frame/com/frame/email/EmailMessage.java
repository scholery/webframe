/**
 * Copyright (C) 2000-2011 Nanjing Fujitsu Nanda Software Tech. Co.Ltd.(FNST) All rights reserved.
 */
package com.frame.email;

import org.vaniglia.messagequeue.Message;

/**
 * EMail Message Object
 * 
 * @author kylin
 * @since 2008-2-21
 */
public class EmailMessage extends Message {

	private static final long serialVersionUID = 1;

	/**
	 * Serializable Message Bean
	 */
	private SerializableMessage serializableMessage;

	/**
	 * Mail State
	 */
	private String state;
	
	private boolean debug;

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public EmailMessage(SerializableMessage mailMessage,boolean aDebug) {
		super();
		this.serializableMessage = mailMessage;
		this.debug = aDebug;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public SerializableMessage getSerializableMessage() {
		return serializableMessage;
	}

	public void setSerializableMessage(SerializableMessage serializableMessage) {
		this.serializableMessage = serializableMessage;
	}

}
