package com.frame.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.frame.web.MessageSourceHelper;


public final class BaseAppException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final int INNER_ERROR = 1;

	public static final int BUSS_ERROR = 0;

	private String code;

	private String desc;

	private String localeMessage;

	private Date time;

	private int type;

	public BaseAppException() {
		super();
	}

	public BaseAppException(String code) {
		this(code, null, INNER_ERROR, null, null, null, null);
	}

	public BaseAppException(String code, String msg) {
		this(code, msg, INNER_ERROR, null, null, null, null);
	}

	public BaseAppException(String code, String msg, String arg0) {
		this(code, msg, INNER_ERROR, null, arg0, null, null);
	}

	public BaseAppException(String code, Throwable cause) {
		this(code, null, INNER_ERROR, cause, null, null, null);
	}

	public BaseAppException(String code, int errorType, Throwable cause) {
		this(code, null, errorType, cause, null, null, null);
	}

	public BaseAppException(String code, String msg, int errorType) {
		this(code, msg, errorType, null, null, null, null);
	}

	public BaseAppException(String code, String param1, Throwable cause) {
		this(code, null, INNER_ERROR, cause, param1, null, null);
	}

	public BaseAppException(String code, String param1, String param2,
			Throwable cause) {
		this(code, null, INNER_ERROR, cause, param1, param2, null);
	}

	/**
	 * 为了可对异常信息进行参数替换，扩展了String arg0,String arg1,String arg2 三个参数
	 * 
	 * @param errorCode
	 * @param message
	 * @param errorType
	 * @param cause
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public BaseAppException(String errorCode, String message, int errorType,
			Throwable cause, String arg0, String arg1, String arg2) {
		/** @todo* */
		super(message, cause);

		List<String> list = new ArrayList<String>();
		if (arg0 != null) {
			list.add(arg0);
		}
		if (arg1 != null) {
			list.add(arg1);
		}
		if (arg2 != null) {
			list.add(arg2);
		}
		String[] args = null;
		if (list.size() > 0) {
			args = (String[]) list.toArray(new String[] {});
		}

		this.code = errorCode;
		this.desc = message;
		this.type = errorType;

		try {
			this.localeMessage = (code == null ? "" : MessageSourceHelper
					.getText(code));
			
			if (args != null && args.length > 0) {
				this.localeMessage = this.replaceArgs(localeMessage, args);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param s
	 * @param args
	 * @return
	 */
	private String replaceArgs(String s, String args[]) {
		int i = 0;
		if (s != null && args != null && args.length > 0) {
			StringBuffer sb = new StringBuffer();
			Pattern p = Pattern.compile("\\{(.*?)\\}");
			Matcher m = p.matcher(s);
			while (m.find()) {
				s = s.replaceFirst("\\{(.*?)\\}", args[i++]);
			}
			sb.append(s);
			return sb.toString();
		}

		return "";
	}

	/**
	 * 异常code
	 * 
	 * @param code
	 *            String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 异常描述
	 * 
	 * @param desc
	 *            String
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param time
	 *            Date
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @param type
	 *            int
	 */
	public void setType(int type) {
		this.type = type;
	}


	/**
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return String
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return Date
	 */
	public Date getTime() {
		if (time == null) {
			time = new Date();
		}
		return time;
	}

	/**
	 * @return int
	 */
	public int getType() {
		return type;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (code != null) {
			sb.append("errorCode = [");
			sb.append(code);
			sb.append("] errorDesc = [");

			if (localeMessage != null) {
				sb.append(localeMessage);
			}
			sb.append("]");

			if (desc != null) {
				sb.append("  Describing= [");
				sb.append(desc);
				sb.append("]");
			}
		}
		return sb.toString();
	}

	public String toStringNonTrace() {
		StringBuffer sb = new StringBuffer();
		sb.append("errorCode = [");
		if (getCode()!=null) {
			sb.append(getCode());
		}
		sb.append("] errorDesc = [");

		if (getLocaleMessage() != null) {
			sb.append(getLocaleMessage());
		}
		sb.append("]");
		if (getDesc() != null) {
			sb.append("  describing= [");
			sb.append(getDesc());
			sb.append("]");
		}
		Throwable cause = getCause();
		if (cause != null) {
			while (true) {
				if (cause.getCause() != null) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
		}
		if (cause != null) {
			sb.append(" cause = [");
			sb.append(cause.getClass().getName());
			sb.append(":");
			sb.append(cause.getMessage());
			sb.append("]");
		}
		return sb.toString();
	}

	public String getLocaleMessage() {
		return localeMessage;
	}

	public void setLocaleMessage(String localeMessage) {
		this.localeMessage = localeMessage;
	}
}
