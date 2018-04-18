package com.hx.acc.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间转换工具类
 * 
 */
public class DateUtils {
	
	public static final String DATETIME_FORMAT = "yyyyMMddHHmmss";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATETIME_FORMAT);
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String SHOW_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SHOW_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHOW_MONTH_FORMAT = "yyyy-MM";
    public static final String SMS_MONTH_FORMAT = "yyyy年MM月dd日";

	private static SimpleDateFormat df = null;

	static {
		df = new SimpleDateFormat();
	}
	
	 /** 
     * 返回预设Format的当前日期字符串 
     */  
    public static String getToday()  
    {  
        Date today = new Date();  
        return format(today);  
    }  
    
    /** 
     * 使用参数Format格式化Date成字符串 
     */  
    public static String formatString(Date date, String pattern)  
    {  
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);  
    }  
  
    /** 
     * 使用预设Format格式化Date成字符串 
     */  
    public static String format(Date date)  
    {  
        return date == null ? " " : formatString(date, SHOW_DATE_FORMAT);  
    }  
    
    /** 
     * 使用预设格式将字符串转为Date 
     */  
    public static Date parse(String strDate)  
    {  
        return StringUtils.isBlank(strDate) ? null : parse(strDate, SHOW_DATE_FORMAT);
    }  
  
    /** 
     * 使用参数Format将字符串转为Date 
     */  
    public static Date parse(String strDate, String pattern)  
    {  
        try {
			return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;  
    } 
	
    /**
	 * 获取当前时间串，格式为：yyyymmddHHMiSS
	 * 
	 * @return
	 */
    public static final String getCurrDatetime() {
        return formatString(new Date(), DATETIME_FORMAT);
    }
    
    /**
	 * 获取当前时间串，格式为：yyyy-mm-dd HH:mm:SS
	 * 
	 * @return
	 */
    public static final String getCurrDatetime_() {
        return formatString(new Date(), SHOW_DATETIME_FORMAT);
    }

    
    /**
	 * 获取当前日期串，格式为yyyymmdd
	 * 
	 * @return
	 */
    public static final String getCurrDate() {
        return formatString(new Date(), DATE_FORMAT);
    }

    /**
  	 * 获取当前月，格式为yyyy-mm
  	 * 
  	 * @return
  	 */
      public static final String getCurrentMonth() {
          return formatString(new Date(), SHOW_MONTH_FORMAT);
      }
      
    public static final Timestamp getSystemTime() {
		return new Timestamp(new Date().getTime());
	}

    /**
	 * @param date
	 *            时间
	 * @param formatStr
	 *            格式化串
	 * @return
	 */
    public static String format(Date date, String formatStr) {
    	if(date==null){
    		return null;
    	}
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    /**
	 * {@code time1}是否小于{@code time2},即类似于
	 * 
	 * <pre>
	 * time1 &lt; time2
	 * </pre>
	 * 
	 * 。 如果{@code time2}为<code>null</code>， 则视为最小。
	 * 
	 * @param time1
	 *            时间字符串，格式为 yyyyMMddHHmmss，不足14位后补0
	 * @param time2
	 *            时间字符串，格式为 yyyyMMddHHmmss，不足14位后补0
	 * @return
	 */
    public static boolean lessThan(String time1, String time2) {
        if (StringUtils.isEmpty(time1)) {
            if (StringUtils.isEmpty(time2)) {
                return false;
            } else {
                return true;
            }
        } else {
            return time1.compareTo(time2) < 0;
        }
    }


    /**
	 * {@code time1}是否大于{@code time2},即类似于
	 * 
	 * <pre>
	 * time1 &gt; time2
	 * </pre>
	 * 
	 * 。如果{@code time2}为<code>null</code>， 则视为最大。
	 * 
	 * @param time1
	 *            时间字符串，格式为 yyyyMMddHHmmss，不足14位后补9
	 * @param time2
	 *            时间字符串，格式为 yyyyMMddHHmmss，不足14位后补9
	 * @return
	 */
    public static boolean greaterThan(String time1, String time2) {
        if (StringUtils.isEmpty(time1)) {
            if (StringUtils.isEmpty(time2)) {
                return false;
            } else {
                return true;
            }
        } else {
            return time1.compareTo(time2) > 0;
        }
    }

    /**
	 * 将<code>datetime</code>字符串时间转换为毫秒数
	 * 
	 * @param datetime
	 *            长度必须大于等于8而小于等于14，格式为 yyyyMMddHHmmss，不足14位后补0
	 * @return
	 */
    public static long toMilliseconds(String datetime){
        return parseDate(datetime).getTime();
    }

    /**
	 * 将格式为{@link #DATETIME_FORMAT}的时间格式解析为Date对象,{@code datetime}的长度必须大于8小于14.
	 * 
	 * @param datetime
	 * @return
	 */
    public static Date parseDate(String datetime){
        Assert.notNull(datetime);
		Assert.isTrue(datetime.length() >= 4 && datetime.length() <= 14,
				"长度必须大于等于8而小于等于14");
        DateFormat dateFormat = SIMPLE_DATE_FORMAT;
        try {
            if(datetime.length() < 14){
                dateFormat = new SimpleDateFormat(DATETIME_FORMAT.substring(0, datetime.length()));
            }
            return dateFormat.parse(datetime);
        } catch (ParseException e) {
			throw new IllegalArgumentException("入参datetime：" + datetime
					+ "解析异常，请检查格式必须为：" + DATETIME_FORMAT);
        }
    }

    /**
	 * 将字符串时间解析为对象
	 * 
	 * @param datetime
	 * @param format
	 * @return
	 */
    public static Date parseDate(String datetime,String format){
        Assert.notNull(datetime);
        Assert.notNull(format);
		Assert.isTrue(datetime.length() == format.length(), "值和格式串的长度不一致");
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(datetime);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
            		MessageFormat.format(
					"入参datetime：{1}解析异常，请检查格式必须为：{2}", datetime, format));
        }
    }
    
    /**
     * 返回上月日期
     * @param format
     * @return
     */
   public static String getLastDate(String format) { 
     SimpleDateFormat sdf = new SimpleDateFormat(format); 
     Date date = new Date();  
    Calendar cal = Calendar.getInstance();  
    cal.setTime(date);  
    cal.add(Calendar.MONTH, -1); 
    return sdf.format(cal.getTime()); 
    } 

   /**
    * 上月第一天
    * @return
    */
   public static Date lastMonthBegin(){
	   Date nowdate = new Date();     
	   Calendar cal = Calendar.getInstance();    
	   cal.setTime(nowdate);
	   cal.add(Calendar.MONTH, -1);       
	  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH)); 
	  return cal.getTime();
   }

   /**
    * 上月最后一天
    * @return
    */
   public static Date lastMonthEnd(){
	   Date nowdate = new Date();     
	   Calendar cal = Calendar.getInstance();    
	   cal.setTime(nowdate);
	   cal.add(Calendar.MONTH, -1);       
	   cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	  return cal.getTime();
   }
   
   /**
    * 当前月第一天
    * @return
    */
   public static Date currentDateBegin(){
	   Date nowdate = new Date();     
	   Calendar cal = Calendar.getInstance();    
	   cal.setTime(nowdate);
	   cal.add(Calendar.MONTH,0);       
	   cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	   cal.set(Calendar.HOUR_OF_DAY, 0);
       cal.set(Calendar.SECOND,0);
       cal.set(Calendar.MINUTE,0);
       cal.set(Calendar.MILLISECOND,0);
	  return cal.getTime();
   }
   
   /**获取当月最后一天*/
   public static Date currentDateEnd(){
	   Date nowdate = new Date(); 
	   Calendar calendar = Calendar.getInstance();
	   calendar.setTime(nowdate);
	   calendar.add(Calendar.MONTH,0);   
	   calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
	   calendar.set(Calendar.HOUR_OF_DAY, 0);
	   calendar.set(Calendar.SECOND,0);
	   calendar.set(Calendar.MINUTE,0);
	   calendar.set(Calendar.MILLISECOND,0);
       return calendar.getTime();
   }
   
   /**
	 * 把字符串解析成日期对象
	 * 
	 * @param style
	 *            样式
	 * @param dateStr
	 *            要解析的字符串
	 * @return 解析完成后的对象
	 * @throws Exception
	 */
	public static Date strToDate(String style, String dateStr) throws Exception {
		Date date = null;
		df.applyPattern(style);
		if (!StringUtils.isEmpty(dateStr)) {
			date = df.parse(dateStr);
		}
		return date;
	}
   
   /**
	 * 计算两个日期相差多少天
	 * 
	 * @param startDateStr
	 *            起始日期字符串
	 * @param endDateStr
	 *            结束日期字符串
	 * @return 返回日期相差多少天
	 */
	public static long countTwoDateDifferDay(String startDateStr,
			String endDateStr){
		long differDay = 0;
		if (!StringUtils.isEmpty(endDateStr)
				&& !StringUtils.isEmpty(startDateStr)) {
			try {
				Date startDate = DateUtils.strToDate(SHOW_DATE_FORMAT,
						startDateStr);
				Date endDate = DateUtils
						.strToDate(SHOW_DATE_FORMAT, endDateStr);
				differDay = countTwoDateDifferDay(startDate, endDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return differDay;
	}

	/**
	 * 计算两个日期相差多少天
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long countTwoDateDifferDay(Date startDate, Date endDate){
		long differDay = 0;
		if (!DateUtils.isEmpty(startDate) && !DateUtils.isEmpty(endDate)) {
			long day = 24 * 60 * 60 * 1000;
			long longStartDate = startDate.getTime() / day;
			long longEndDate = endDate.getTime() / day;
			differDay = (longEndDate - longStartDate);
		}
		return differDay;
	}
	
	/**
	 * 判断传入的date是否为空
	 * 
	 * @param date
	 *            要判断的日期对象
	 * @return true:为空 false: 不为空
	 */
	public static boolean isEmpty(Date date) {
		boolean flag = false;
		if (null == date)
			flag = true;
		return flag;
	}
   
	 /**  
	    * 计算两个日期之间相差的天数  
	    * @param smdate 较小的时间 
	    * @param bdate  较大的时间 
	    * @return 相差天数 
	    * @throws ParseException  
	    */    
	   public static int daysBetween(Date smdate,Date bdate){    
	       
		   Date date1 = parse(format(smdate, "yyyy-MM-dd"), "yyyy-MM-dd");
		   Date date2 = parse(format(bdate, "yyyy-MM-dd"), "yyyy-MM-dd");
		   
	       long between_days=(date2.getTime()-date1.getTime())/(1000*3600*24);  
	           
	      return Integer.parseInt(String.valueOf(between_days));           
	   }
	
	/**
	 * 获取某年的开始时间</br></br>
	 * 如2015年的开始时间为</br>
	 * 2015-01-01 00:00:00.000
	 * 
	 * @param date
	 * @return 某年的开始时间
	 */
	public static Date getYearBegin(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取某月的开始时间</br></br>
	 * 如2015-04月的开始时间为</br>
	 * 2015-04-01 00:00:00.000
	 * 
	 * @param date
	 * @return 某月的开始时间
	 */
	public static Date getMonthBegin(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取某天的开始时间</br></br>
	 * 如2015-04-24 12:00:00的开始时间为</br>
	 * 2015-04-24 00:00:00.000
	 * 
	 * @param date
	 * @return 某天的开始时间
	 */
	public static Date getDayBegin(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取某年的结束时间</br></br>
	 * 如2015年的结束时间为</br>
	 * 2016-01-01 00:00:00.000
	 * 
	 * @param date
	 * @return 某年的结束时间
	 */
	public static Date getYearEnd(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getYearBegin(date));
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取某月的结束时间</br></br>
	 * 如2015-04月的结束时间为</br>
	 * 2015-05-01 00:00:00.000
	 * 
	 * @param date
	 * @return 某月的结束时间
	 */
	public static Date getMonthEnd(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getMonthBegin(date));
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取某天的结束时间</br></br>
	 * 如2015-04-24 12:00:00的结束时间为</br>
	 * 2015-04-25 00:00:00.000
	 * 
	 * @param date
	 * @return 某天的结束时间
	 */
	public static Date getDayEnd(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDayBegin(date));
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	/**
	 * 根据日期格式获取开始时间</br></br>
	 * 如2015-04-24 12:00:00对应的格式的开始时间为</br>
	 * yyyyMMdd => 2015-04-24 00:00:00.000</br>
	 * yyyyMM => 2015-04-01 00:00:00.000</br>
	 * yyyy => 2015-01-01 00:00:00.000
	 * 
	 * @param date
	 * @return 开始时间
	 */
	public static Date getBeginByFormat(String format, Date date) {
		if(format==null)
			return date;
		switch (format) {
		//获取年的起始、结束时间
		case "yyyy":
			return DateUtils.getYearBegin(date);
		//获取月的起始、结束时间
		case "yyyyMM":
			return DateUtils.getMonthBegin(date);
		//获取日的起始、结束时间
		case "yyyyMMdd":
			return DateUtils.getDayBegin(date);
		default:
			return date;
		}
	}
	
	/**
	 * 根据日期格式获取结束时间</br></br>
	 * 如2015-04-24 12:00:00对应的格式的开始时间为</br>
	 * yyyyMMdd => 2015-04-25 00:00:00.000</br>
	 * yyyyMM => 2015-05-01 00:00:00.000</br>
	 * yyyy => 2016-01-01 00:00:00.000
	 * 
	 * @param date
	 * @return 开始时间
	 */
	public static Date getEndByFormat(String format, Date date) {
		if(format==null)
			return date;
		switch (format) {
		//获取年的起始、结束时间
		case "yyyy":
			return DateUtils.getYearEnd(date);
		//获取月的起始、结束时间
		case "yyyyMM":
			return DateUtils.getMonthEnd(date);
		//获取日的起始、结束时间
		case "yyyyMMdd":
			return DateUtils.getDayEnd(date);
		default:
			return date;
		}
	}
	
	/**
	 * 获取Unix时间戳
	 * @param date
	 * 			日期时间
	 * @return
	 * 			Unix时间戳
	 */
	public static Long convertUnixTime(Date date){
		if(date==null){
			return null;
		}
		return (date.getTime()/1000);
	}
	
	/**
	 * 转换Unix时间戳为Date
	 * @param unixTime
	 * 			Unix时间戳
	 * @return
	 * 			日期时间
	 */
	public static Date convertUnixTime(Long unixTime){
		if(unixTime==null || unixTime==0) {
			return null;
		}
		return new Date(unixTime*1000);
	}
	
	/**
	 * 转换Unix时间戳为Date
	 * @param unixTime
	 * 			Unix时间戳
	 * @return
	 * 			日期时间
	 */
	public static Date convertUnixTime(Long unixTime,boolean onlyDate){
		if(unixTime==null || unixTime==0) {
			return null;
		}else{
			if(onlyDate){
				return new Date(((unixTime+3600*8)/86400*86400-3600*8)*1000);
			}else{
				return new Date(unixTime*1000);
			}
		}
	}
	

	/**
	 * Title: addmonth
	 * Description:根据时间，计算加上num个月后的时间
	 * @author lincong
	 * @param time
	 * @param num
	 * @return
	 */
	public static long addmonth(Long time,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(time*1000));
		calendar.add(Calendar.MONTH, num);
		return calendar.getTime().getTime();
	}
	
	public static void main(String[] args) {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println("本日开始时间："+sdf.format(getDayBegin(new Date())));
		System.out.println("本月开始时间："+sdf.format(getMonthBegin(new Date())));
		System.out.println("本年开始时间："+sdf.format(getYearBegin(new Date())));
		System.out.println("本日结束时间："+sdf.format(getDayEnd(new Date())));
		System.out.println("本月结束时间："+sdf.format(getMonthEnd(new Date())));
		System.out.println("本年结束时间："+sdf.format(getYearEnd(new Date())));*/
		System.out.println(currentDateEnd());
	}
}
