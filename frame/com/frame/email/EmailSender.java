package com.frame.email;

import java.util.List;
import java.util.Properties;

import javax.mail.internet.AddressException;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.frame.util.ConfigHelper;

/**
 * @author Administrator
 */
public class EmailSender {
	private static Log log = LogFactory.getLog(EmailSender.class);
	
	Properties mailServer;
	
	static EmailSender sender = new EmailSender();
	
	private EmailSender(){
		mailServer = new Properties();
		mailServer.setProperty("mail.smtp.host", ConfigHelper.getProperty("mail.smtp.host"));
		mailServer.setProperty("mail.smtp.port",  ConfigHelper.getProperty("mail.smtp.port"));
		mailServer.setProperty("SMTPValidate",  ConfigHelper.getProperty("SMTPValidate"));
		mailServer.setProperty("SMTPUser",  ConfigHelper.getProperty("SMTPUser"));
		mailServer.setProperty("SMTPPassword",  ConfigHelper.getProperty("SMTPPassword"));
		mailServer.setProperty("mail.charset",  ConfigHelper.getProperty("mail.charset"));
		mailServer.setProperty("mail.type",  ConfigHelper.getProperty("mail.type"));
		mailServer.setProperty("mail.debug",  ConfigHelper.getProperty("mail.debug"));
		mailServer.setProperty("mail.from",  ConfigHelper.getProperty("mail.from"));
	}
	
	public static EmailSender getInstance(){
		return sender;
	}

	public boolean sendHTMLMail(String subject, String from, String to,
			String cc, String bcc, String body,List files) {
		boolean returnValue = true;
		try {
			SerializableMessage sMsg = new SerializableMessage();
			sMsg.setProperties(this.mailServer);
			sMsg.setSMTPValidate(this.mailServer.getProperty("SMTPValidate", "false"));
			sMsg.setUserName(this.mailServer.getProperty("SMTPUser"));
			sMsg.setPwd(this.mailServer.getProperty("SMTPPassword"));
			String code = this.mailServer.getProperty("mail.charset","utf-8");
			if ("".equals(from) || null == from) {
				sMsg.setFrom(this.mailServer.getProperty("mail.from"));
			} else {
				sMsg.setFrom(from);
			}
			sMsg.setTo(to);
			sMsg.setCc(cc);
			sMsg.setBcc(bcc);
			sMsg.setFilelist(files);
			sMsg.setSubject(MimeUtility.encodeText(subject,code,null));
			sMsg.setSendAsHtml(true);
			sMsg.setBody(body);
			sMsg.setCode("text/html;charset=" + code);
			log.debug(body);
			EmailSenderQueue.putMessageToQueue(new EmailMessage(sMsg,
					BooleanUtils.toBoolean(this.mailServer.getProperty("mail.debug","false"))));
		} catch (AddressException e) {
			log.info(e.getMessage());
			returnValue = false;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			returnValue = false;
		}
		return returnValue;
	}

	/**
	 * SPIF系统测试邮件发送，主要供MailTest这个servlet调用（管理员后台的邮件发送测试功能）
	 * 
	 * @param subject
	 * @param from
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param body
	 * @return 
	 */
	public boolean sendMailTest(String subject, String from, String to,
			String cc, String bcc, String body) {

		boolean returnValue = true;
		try {
			this.sendHTMLMail(subject, from, to, cc, bcc, body,null);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = false;
		}
		return returnValue;
	}
}
