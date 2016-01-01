package com.frame.exception;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.frame.util.AssertUtil;

public final class ExceptionHandler {

	public static final int INNER_ERROR = 1;

	public static final int BUSS_ERROR = 0;

	private static final Log logger = LogFactory.getLog(ExceptionHandler.class);

	private static long appExceptionCount = 0;

	public static synchronized long getAppExceptionCount() {

		return appExceptionCount;
	}

	public static synchronized void increaseAppExceptionCount() {
		appExceptionCount++;
	}

	private ExceptionHandler() {
	}

	/**
	 * 
	 * @param errorCode
	 *            String 错误码
	 * @param t
	 *            Throwable
	 * @return BaseAppException
	 * @throws BaseAppException
	 */
	public static BaseAppException publish(String errorCode, Throwable t)
			throws BaseAppException {
		return publish(errorCode, null, INNER_ERROR, t, null, null, null);
	}

	/**
	 * 
	 * @param errorCode
	 *            String 错误码
	 * @param t
	 *            Throwable
	 * @return BaseAppException
	 * @throws BaseAppException
	 */
	public static BaseAppException publish(String errorCode, int errorType,
			Throwable t) throws BaseAppException {
		return publish(errorCode, null, errorType, t, null, null, null);
	}

	/**
	 * @param errorCode
	 *            String
	 * 
	 * 
	 * @return BaseAppException
	 * @throws BaseAppException
	 */
	public static BaseAppException publish(String errorCode)
			throws BaseAppException {
		return publish(errorCode, null, INNER_ERROR, null, null, null, null);
	}

	public static BaseAppException publish(String errorCode, int errorType)
			throws BaseAppException {
		return publish(errorCode, null, errorType, null, null, null, null);
	}

	/**
	 * 
	 * @param errorCode
	 *            String 错误码	 * 
	 * @param msg
	 *            String
	 * @return BaseAppException
	 * @throws BaseAppException
	 */
	public static BaseAppException publish(String errorCode, String msg)
			throws BaseAppException {
		return publish(errorCode, msg, INNER_ERROR, null, null, null, null);
	}

	public static BaseAppException publish(String errorCode, String msg,int errorType)
			throws BaseAppException {
		return publish(errorCode, msg, errorType, null, null, null, null);
	}

	/**
	 * 
	 * @param errorCode
	 *            String  
	 * 
	 * 
	 * 
	 * @param msg
	 *            String  
	 * @param t
	 *            Throwable 异常
	 * @return BaseAppException
	 * @throws BaseAppException
	 */
	public static BaseAppException publish(String errorCode, String msg,
			Throwable t) throws BaseAppException {
		return publish(errorCode, msg, INNER_ERROR, t, null, null, null);
	}
	
	public static BaseAppException publish(String errorCode, String msg,
			int errorType,Throwable t) throws BaseAppException {
		return publish(errorCode, msg, errorType, t, null, null, null);
	}
	
	public static BaseAppException publish(String errorCode, String msg,
			Throwable t, String param) throws BaseAppException {
		return publish(errorCode, msg, INNER_ERROR, t, param, null, null);
	}
	public static BaseAppException publish(String errorCode, String msg,
			int errorType,Throwable t, String param) throws BaseAppException {
		return publish(errorCode, msg, errorType, t, param, null, null);
	}
	
	/**
	 * 实现变量可替换，增加了三个构造函数
	 * 
	 * @param errorCode
	 * @param msg
	 * @param param1
	 * @return
	 * @throws BaseAppException
	 */
	public static BaseAppException publish(String errorCode, String msg,
			String param1) throws BaseAppException {
		return publish(errorCode, msg, INNER_ERROR, null, param1, null, null);
	}
	public static BaseAppException publish(String errorCode, String msg,
			int errorType,String param1) throws BaseAppException {
		return publish(errorCode, msg, errorType, null, param1, null, null);
	}
	
	public static BaseAppException publish(String errorCode, String msg,
			String param1, String param2) throws BaseAppException {
		return publish(errorCode, msg, INNER_ERROR, null, param1, param2, null);
	}
	public static BaseAppException publish(String errorCode, String msg,
			int errorType,String param1, String param2) throws BaseAppException {
		return publish(errorCode, msg, errorType, null, param1, param2, null);
	}
	
	public static BaseAppException publish(String errorCode, String msg,
			String param1, String param2, String param3)
			throws BaseAppException {
		return publish(errorCode, msg, INNER_ERROR, null, param1, param2,
				param3);
	}
	public static BaseAppException publish(String errorCode, String msg,
			int errorType,String param1, String param2, String param3)
			throws BaseAppException {
		return publish(errorCode, msg, errorType, null, param1, param2,
				param3);
	}
	
	public static BaseAppException publish(String errorCode, int errorType, 
			String param1) throws BaseAppException {
		return publish(errorCode, null, errorType, null, param1, null, null);
	}
	
	public static BaseAppException publish(String errorCode, int errorType, 
			String param1, String param2) throws BaseAppException {
		return publish(errorCode, null, errorType, null, param1, param2, null);
	}
	
	public static BaseAppException publish(String errorCode, int errorType, 
			String param1, String param2, String param3) throws BaseAppException {
		return publish(errorCode, null, errorType, null, param1, param2, param3);
	}
	
	
	/**
	 * @param errorCode
	 * 
	 * @param msg
	 * @param errorType
	 *            int
	 * @param t
	 *            Throwable
	 * @return BaseAppException
	 * @throws BaseAppException
	 */
	public static BaseAppException publish(String errorCode, String msg,
			int errorType, Throwable t, String param1, String param2,
			String param3) throws BaseAppException {

		BaseAppException baseAppException;
		if ((t != null) && (t instanceof BaseAppException))
			baseAppException = (BaseAppException) t;
		else
			baseAppException = new BaseAppException(errorCode, msg, errorType,
					t, param1, param2, param3);

		// 记录日志,统一由callService记录日志
		 logErrorInfo(baseAppException);

		throw baseAppException;
	}


	/**
	 * 获得最底层异常信息
	 * 
	 * 
	 * @param ex
	 */
	public static BaseAppExceptionDto getMostExactException(Throwable ex) {
		AssertUtil.isNotNull(ex, "Exception");

		BaseAppExceptionDto bae = new BaseAppExceptionDto();

		Throwable cause = ex;

		while (cause.getCause() != null) {
			cause = cause.getCause();
		}

		if (cause instanceof BaseAppException) {
			bae.setErrorCode(((BaseAppException) cause).getCode());
			bae.setErrorMessage(((BaseAppException) cause).getLocaleMessage());
		} else if (cause instanceof SQLException) {
			// 数据访问异常
			bae.setErrorCode("S-COM-00000");
//			bae.setErrorMessage(MessageSource.getText("S-COM-00000") + " :"
//					+ cause.getMessage() == null ? "" : cause.getMessage());

		} else {
			bae.setErrorCode("S-COM-00000");
//			bae.setErrorMessage(MessageSource.getText("S-COM-00000") + " :"
//					+ cause.getMessage() == null ? "" : cause.getMessage());
		}

		return bae;
	}
	
	public static void logErrorInfo(String m, Throwable ex) {
		increaseAppExceptionCount();
		logger.error(m, ex);
	}
	
	public static void logErrorInfo(BaseAppException baseAppException) {

		increaseAppExceptionCount();
		StringBuffer sb = new StringBuffer();

		sb.append(baseAppException.toString());
		// add by chen.weizheng task CCB-3246 (ID:6368)
		if (baseAppException.getDesc() != null) {
			sb.append("\r\n").append(baseAppException.getDesc());
		}

		Throwable cause = baseAppException.getCause();

		while (cause != null) {
			sb.append("\r\nCause by: ");

			sb.append(cause.toString());

			for (int i = 0; i < cause.getStackTrace().length; i++) {
				sb.append("\r\n\tat ");
				sb.append(cause.getStackTrace()[i]);
			}

			cause = cause.getCause();

			if (cause != null) {
				sb.append("\r\nCaused by: ");
			}
		}
		
		logger.error(sb.toString());
	}
}
