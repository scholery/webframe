package com.frame.email;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class SerializableMessage implements Serializable {

	private Properties properties;
	private String subject;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String body;
	private String code;

	private boolean sendAsHtml = false;

	private String SMTPValidate;
	private String userName;
	private String pwd;
	private String mailSrv;
	private List filelist = new ArrayList();

	public String getSMTPValidate() {
		return SMTPValidate;
	}

	public void setSMTPValidate(String validate) {
		SMTPValidate = validate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMailSrv() {
		return mailSrv;
	}

	public void setMailSrv(String mailSrv) {
		this.mailSrv = mailSrv;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Message toMialMessage() throws Exception {
		Session session;
		if (null != SMTPValidate && SMTPValidate.equals("1")) {

			properties.put("mail.smtp.auth", "true");

			session = Session.getInstance(properties, new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, pwd);
				}
			});
		} else {

			properties.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(properties, null);
		}

		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(from));
		// Specify the From Address
		InternetAddress[] tos = InternetAddress.parse(to);
		// Specify the To Address
		msg.setRecipients(Message.RecipientType.TO, tos);

		if (cc != null && cc.trim().length() > 0) {
			InternetAddress[] addressCC = InternetAddress.parse(cc);
			msg.setRecipients(Message.RecipientType.CC, addressCC);
		}

		if (bcc != null && bcc.trim().length() > 0) {
			InternetAddress[] addressBcc = InternetAddress.parse(bcc);
			msg.setRecipients(Message.RecipientType.BCC, addressBcc);
		}

		msg.setSubject(subject);

		if (this.isSendAsHtml() == false) {
			msg.setContent(body.toString(), code);
		} else {
			MimeMultipart mm = new MimeMultipart();
			MimeBodyPart mdp1 = new MimeBodyPart();
			
            // content
			mdp1.setContent(body.toString(), code);
			mm.addBodyPart(mdp1);
			
			// attachment file
			if(filelist != null){
				for (int i = 0; i < filelist.size(); i++) {
					String fileLocal = (String)filelist.get(i);
					if (!"".equals(fileLocal)) {
						File file = new File(fileLocal);
						if (file.exists()) {
							MimeBodyPart mdp2 = new MimeBodyPart();
							DataSource fds=new FileDataSource(fileLocal);
							mdp2.setDataHandler(new DataHandler(fds));
							String fileName = fds.getName();
							fileName = FileJMCoder.decrypt(fileName);
							mdp2.setFileName(MimeUtility.encodeText(fileName));
							mm.addBodyPart(mdp2);
						}
					}
				}
			}
			msg.setContent(mm);
			msg.saveChanges();
		}

		return msg;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSendAsHtml() {
		return sendAsHtml;
	}

	public void setSendAsHtml(boolean sendAsHtml) {
		this.sendAsHtml = sendAsHtml;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * @return the filelist
	 */
	public List getFilelist() {
		return filelist;
	}

	/**
	 * @param filelist the filelist to set
	 */
	public void setFilelist(List filelist) {
		this.filelist = filelist;
	}
}
