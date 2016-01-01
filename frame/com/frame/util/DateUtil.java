package com.frame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class DateUtil {
	public static SimpleDateFormat df = new SimpleDateFormat();

	/**
	 * 根据当前时间返回yyyyMMdd字符串
	 * @return
	 */
	public static String getLocalDayString() {
		df.applyPattern("yyyyMMdd");
		return df.format(new Date());
	}
	
	/**
	 * 根据当前时间返回HHmmss字符串
	 * @return
	 */
	public static String getCurrentTimeString() {
		df.applyPattern("HHmmss");
		return df.format(new Date());
	}
	
	/**
	 * 根据时间获取字符串
	 * @return
	 */
	public static String getLocalTimeString() {
		df.applyPattern("yyyyMMddHHmmss");
		return df.format(new Date());
	}
	
	 /**
     * 比较两个日期的大小关系, 注意,这里输入的字符串必需是带有日斯和时间的完整格式. 如有需求再添加其他格式.
     * @param date1 日期1；
     * @param date2 日期2；
     * @return 比较结果：-1-日期1小于日期2，0-日期1等于日期2，1-日期1大于日期2
     * @throws Exception
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static int compareDate(String date1, String date2) throws Exception {
        int result = 0;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DEFAULT_DATETIME_FORMAT);
        Date realDate1 = null;
        Date realDate2 = null;
        try {
            realDate1 = formatter.parse(date1);
            realDate2 = formatter.parse(date2);
            result = realDate1.compareTo(realDate2);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        return result;
    }

    /**
     * 比较两个日期的大小关系。目前没有实现只有接口。
     * @param date1 日期1；
     * @param date2 日期2；
     * @return 比较结果：-1-日期1小于日期2，0-日期1等于日期2，1-日期1大于日期2
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static int compareDate(Date date1, Date date2) {
        int result = 0;
        result = date1.compareTo(date2);
        return result;
    }

    /**
     * 取得用户定制的系统时间格式。目前没有实现只有接口。
     * @return 时间格式字符串
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static String getSystemDateFormat() {
        return Constants.DEFAULT_DATETIME_FORMAT;
    }

    /**
     * 取得用户定制的系统日期格式，不包括时间部分。目前没有实现只有接口。
     * @return
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static String getSystemJustDateFormat() {
        return Constants.DEFAULT_DATE_FORMAT;
    }

    /**
     * 取得用户定制的系统日期格式，不包括日期部分。目前没有实现只有接口。
     * @return
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static String getSystemJustTimeFormat() {
        return Constants.DEFAULT_TIME_FORMAT;
    }

    /**
     * 得到当前时间,包括年月日和具体时间,如：2005-01-01 12:00:00。
     * @return
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static Date getCurrentTime() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    /**
     * 取得当前日期,这个日期的时间部分全都为0,如2005-01-01
     * @return
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static Date getCurrentDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 取得当前时间,这个日期的年月日部分全都为0,如12:34:56
     * @return
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static Date getCurrentJustTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 0);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_YEAR, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 将一个String转换为Date。
     * @param date
     * @return 转换后的时间。
     * @throws Exception
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static Date stringToDate(String date, String format) {
        if(date == null || date.trim().equals("")) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        Date realDate = null;
        try {
            realDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return realDate;
    }

    public final static Date StringToDate(String date) throws Exception {
        if(date == null || date.trim().equals("")) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DEFAULT_DATETIME_FORMAT);
        formatter.setLenient(false);
        Date realDate = null;
        try {
            realDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return realDate;
    }

    /**
     * 将一个String转换为Date。
     * @param date
     * @return 转换后的时间。
     * @throws Exception
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static Date StringToJustDate(String date) {
        if(date == null || date.trim().equals("")) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
        formatter.setLenient(false);
        Date realDate = null;
        try {
            realDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return realDate;
    }

    /**
     * 将一个String转换为Date。
     * @param date
     * @return 转换后的时间。
     * @throws Exception
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static Date StringToJustTime(String date) throws Exception {
        if(date == null || date.trim().equals("")) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DEFAULT_TIME_FORMAT);
        formatter.setLenient(false);
        Date realDate = null;
        try {
            realDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return realDate;
    }



    public final static String dateToString(Date date) throws Exception {
        if(date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DEFAULT_DATETIME_FORMAT);
        String result = null;
        result = formatter.format(date);
        return result;
    }

    public final static String dateToStringByFormat(Date date, String format) throws Exception {
        if(date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        String result = null;
        result = formatter.format(date);
        return result;
    }
    
    /**
     * @param date
     * @return
     * @throws Exception
     * @author fnst123
     * @version 1.0
     * 
     * </pre>
     * 
     * Created on :2012-12-5 LastModified: History:
     * 
     * </pre>
     */
    public final static String justDateToString(Date date) throws Exception {
        if(date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
        String result = null;
        result = formatter.format(date);
        return result;
    }
    
    /**
     * 将日期以HH:mm:ss格式显示
     */
    public final static String justTimeToString(Date date) throws Exception {
        if(date == null) return null;
        df.applyPattern(Constants.DEFAULT_TIME_FORMAT);
        String result = df.format(date);
        return result;
    }
    
    /**
     * 将日期字符串（yyyy-MM-dd）转换为当天的开始时间（yyyy-MM-dd 00:00:00）
     * @param date
     * @return
     */
    public final static Date stringToStartTime(String date) {
    	Date startTime = null;
		try {
			startTime = StringToDate(date + " " + Constants.DEFAULT_START_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return startTime;
    }
    
    /**
     * 将日期字符串（yyyy-MM-dd）转换为当天的结束时间（yyyy-MM-dd 23:59:59）
     * @param date
     * @return
     */
    public final static Date stringToEndTime(String date) {
    	Date endTime = null;
    	try {
			endTime = StringToDate(date + " " + Constants.DEFAULT_END_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return endTime;
    }

    /**
     *  获得系统缺省设置的日期格式值
     *  @param dateFormatType
     *  @return
     *  @author fnst123
     *  @created 2012-12-5
     *  @lastModified       
     *  @history
     */
    
    /**
     * 
     *  获得指定的某一天，如getSpecifiedDate(Calendar.DATE, -1)获得当然日期的前一天
     *  @param field 表示年月日等
     *  @param amount 表示加减的数量
     *  @return 转换后的时间
     *  @author fnst123
     *  @created 2012-12-5
     *  @lastModified       
     *  @history
     */
    public final static Date getSpecifiedDate(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
    
    /**
     * 获取星期偏移量
     * @return
     */
	public static int getDateOfWeek(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getDateOfMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取当天时间的偏移量
	 * @param offset
	 * @return
	 */
	public static Date getDateString(int offset){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+offset);
		return cal.getTime();
	}
	
	public static Date getDateString(int offset,Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+offset);
		return cal.getTime();
	}
	
	
	public static Date getSomeDate(int year,int month,int day){
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 0, 1);
		return cal.getTime();
	}
	
	public static String[] getDateRegionString(Date start,Date end){
		if(start.getTime()>end.getTime()){
			return new String[]{};
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
		List<String> l = new ArrayList<String>();
		String endStirng = formatter.format(end);
		while(!formatter.format(start).equals(endStirng)){
			l.add(formatter.format(start));
			cal.setTime(start);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
			start = cal.getTime();
		}
		l.add(endStirng);
		return l.toArray(new String[l.size()]);
	}
	
	public static String[] getJustDateRegionString(Date someDay){
		Calendar cal = Calendar.getInstance();
		cal.setTime(someDay);
		
		int num = cal.get(Calendar.DAY_OF_WEEK);
		if(num==1){
			num = 8;
		}
		
		Date start = getDateString(-num+2,someDay);
		Date end = getDateString(6,start);
		
		return getDateRegionString(start,end);
	}
	
	
	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		
		System.out.println(cal.getTime());
		
		String [] d = getJustDateRegionString(cal.getTime());
		for(String s :d){
			System.out.print(s+"  ");
		}
	}
}
