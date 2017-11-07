package com.zzrenfeng.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * @功能描述：日期操作工具类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月16日 下午3:48:22
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class DateUtil {
	
	public static void main(String[] args) {
		
		System.out.println(Calendar2Long(Calendar.getInstance(), 14));
	}

	/**
	 * @功能描述：
	 * calendar 转为指定长度的long 型
	 * 格式：	20170816155656(len=14)
	 * 		201708161555(len=10)
	 * 		20170816(len=8) 
	 * 
	 * @author zjc		
	 * @param calendar
	 * @param length
	 * @return
	 */
	public static Long Calendar2Long(Calendar calendar,int length){
		String strYear = String.valueOf(calendar.get(Calendar.YEAR));
		String strMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String strDate = String.valueOf(calendar.get(Calendar.DATE));
		String srtHours = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String strMinute = String.valueOf(calendar.get(Calendar.MINUTE));
		String second = String.valueOf(calendar.get(Calendar.SECOND));
		// 整理格式
		strMonth = strMonth.length() < 2 ? "0" + strMonth : strMonth;
		strDate = strDate.length() < 2 ? "0" + strDate : strDate;
		srtHours = srtHours.length() < 2 ? "0" + srtHours : srtHours;
		strMinute = strMinute.length() < 2 ? "0" + strMinute : strMinute;
		second = second.length() < 2 ? "0" + second : second;
		
		if (length == 8) {
			return Long.valueOf(strYear + strMonth + strDate);
		}
		if (length == 14) {
			return Long.valueOf(strYear + strMonth + strDate+ srtHours + strMinute + second);
		}
		
		return Long.valueOf(strYear + strMonth + strDate+ srtHours + strMinute);
	}
	/**
	 * 获取当前时间和日期 
	 * 返回格式：2016-11-25 09:57:24
	 * 作者：zjc
	 * 时间：2016-11-25 
	 */
	
	public static String getCurrentDateTimeView() {
		// 获得当前日期
		Calendar cldCurrent = Calendar.getInstance();
		// 获得年月日
		String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
		String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
		String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
		String srtHours = String.valueOf(cldCurrent.get(Calendar.HOUR_OF_DAY));
		String strMinute = String.valueOf(cldCurrent.get(Calendar.MINUTE));
		String second = String.valueOf(cldCurrent.get(Calendar.SECOND));
		// 整理格式
		strMonth = strMonth.length() < 2 ? "0" + strMonth : strMonth;
		strDate = strDate.length() < 2 ? "0" + strDate : strDate;
		srtHours = srtHours.length() < 2 ? "0" + srtHours : srtHours;
		strMinute = strMinute.length() < 2 ? "0" + strMinute : strMinute;
		second = second.length() < 2 ? "0" + second : second;
		// 得出当天日期时间的字符串
		String StrCurrentCalendar = strYear + "-" + strMonth + "-" + strDate + " " + srtHours + ":" + strMinute + ":" + second;
		return StrCurrentCalendar;
	}

	
	
	/**
	 * 功能描述：取得12位当前时间<BR>
	 * @return 格式：201611250959
	 * 作者：zjc
	 * 时间：2016-11-25 
	 */
	public static Long getCurrnetDate12(){
		Calendar cldCurrent = Calendar.getInstance();
		return DateUtil.Calendar2Long(cldCurrent, 12);
	}
	
	/**
	 * 功能描述：取得8位当前时间<BR>
	 * @return 格式 20161125
	 * 作者：zjc
	 * 时间：2016-11-25 
	 */
	public static Long getCurrentDate8(){
		Calendar cldCurrent = Calendar.getInstance();
		System.out.println(cldCurrent);
		return DateUtil.Calendar2Long(cldCurrent, 8);
	}
	
	/**
	* 日期转换成字符串
	* @author zhoujincheng
	* @param date 要转换的日期 
	* @param formatstr  字符串格式：例如转换成 yyyy-dd-mm格式；为空时默认为yyyy-MM-dd HH:mm:ss格式
	* @return str
	* 
	* @修改人：zjc
	* @修改日期：20170927 14:50
	* @修改描述：当formatstr为空时，默认为yyyy-MM-dd HH:mm:ss格式
	* 		       当date为空时默认为当前日期
	* 
	*/
	public static String DateToStr(Date date, String formatstr) {
		String str = "";
		if(StringUtil.isEmpty(formatstr)) {
			formatstr = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatstr);
		if(null != date) {
			str = format.format(date);
		} else {
			str = format.format(new Date());
		}
		return str;
	}

	/**
	* 字符串转换成日期
	* @author zhoujincheng
	* @param  formatstr 字符串格式 ，比如yyyy-MM-dd HH:mm:ss 或者yyyy-MM-dd，默认为yyyy-MM-dd HH:mm:ss
	* @param  str待转换字符串
	* @return date 
	*/
	public static Date StrToDate(String str, String formatstr) {
		Date date = null;
		if(!StringUtil.isEmpty(str)) {
			if(StringUtil.isEmpty(formatstr)) {
				formatstr = "yyyy-MM-dd HH:mm:ss";
			}
			
			SimpleDateFormat format = new SimpleDateFormat(formatstr);
			
			try {
				date = format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return date;
	}
	
	public final static String DATETYPE_DAY = "DAY";
	public final static String DATETYPE_MONTH = "MONTH";
	public final static String DATETYPE_YEAR = "YEAR";
	public final static String DATETYPE_WEEK = "WEEK";
	/**
	 * @功能描述：  获取当前日期的格式化字符串，具体格式化类型自定义，默认为"yyyy-MM-dd"格式；eg：获取“yyyyMMdd”类型当前日期字符串
	 * @version v1.0.0
	 * @author  zjc
	 * @创建日期  2016年12月16日 下午5:37:52
	 * @备        注：  创建初始版本
	 * 
	 * @版本更新列表
	 *  修改版本：
	 *  修改日期：
	 *  修  改  人：
	 *  修改说明：
	 * 
	 * @param formatString 格式化日期类型字符串，用户自定义
	 * @return
	 */
	public static String getCurrentDateFmtStr(String formatString) {
		SimpleDateFormat format = null;
		String curtDateFmtStr = "";
		try {
			if(null != formatString && !"".equals(formatString)) {
				format = new SimpleDateFormat(formatString);
			} else {
				format = new SimpleDateFormat("yyyy-MM-dd");
			}
			
			curtDateFmtStr = format.format(new Date());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return curtDateFmtStr;
	}
	/**
	* 获取某日期指定时间间隔的之前的时间，或者获取获取当前时间，例如获取上个月的今天，传入时间推移类型month，推移次数我1，就获得2016-12-14的上个月的今天就是2016-11-14；
	* 再比如获取前两个月前的今天，传入时间推移类型为month，推移次数我2，就获得2016-12-14的上个月的今天就是2016-10-14
	* @param timetype 时间推移类型，如果为空或者null表示类型为年，比如说一次推移一周（week）、一月（month）、一天（day）、一年（year）
	* @param timestep 推移次数，默认值为1，例如获取前两个月前的今天推移次数就是2
	* @param date 推移基点,如果为空，则默认为当前时间
	* @author zhangch
	* @return Date 获取的指定时间
	*/
    public static Date getBackTime(String timetype,int timestep,Date date) {
    	Calendar curr = Calendar.getInstance();
    	
    	if (date==null) {
    		date = new Date();
		}
    	if("null".equals(String.valueOf(timestep))) {  
    		timestep = 1;
    	}
    	if (timetype!=null&&!timetype.trim().equals("")) {
    		if (timetype.equalsIgnoreCase(DateUtil.DATETYPE_DAY)) {
        		//推迟n天示例：
            	curr.set(Calendar.DAY_OF_MONTH,curr.get(Calendar.DAY_OF_MONTH)-1*timestep);
            	date=curr.getTime();
    		}else if(timetype.equalsIgnoreCase(DateUtil.DATETYPE_WEEK)){
    			//推迟n周示例：
    	    	curr.set(Calendar.DAY_OF_MONTH,curr.get(Calendar.DAY_OF_MONTH)-7*timestep);
    	    	date=curr.getTime();	
    		}else if(timetype.equalsIgnoreCase(DateUtil.DATETYPE_MONTH)){
    			//推迟n个月示例：
    	    	curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)-1*timestep);
    	    	date=curr.getTime();	
    		}else if(timetype.equalsIgnoreCase(DateUtil.DATETYPE_YEAR)){
    			//推迟n年示例：
    	    	curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)-1*timestep);
    	    	date=curr.getTime();	
    		}
		}
    	return date;
    }
    
    /**
     * @功能描述：  获取最近某个年月字符串（0-为当前月份，1-为往后推一个月，-1-为往前推一个月（eg：当前为2016-12，-1作用后为2016-11））
     * @version v1.0.0
     * @author  zjc
     * @创建日期  2016年12月24日 下午3:40:40
     * @备        注：  创建初始版本
     * 
     * @版本更新列表
     *  修改版本：
     *  修改日期：
     *  修  改  人：
     *  修改说明：
     * 
     * @param monthStep 推移次数
     * @param fmtStr    日期格式化字符串（yyyy/MM，yyyy-MM）
     * @return
     */
    public static String getRecentNMonthStr(int monthStep, String fmtStr) {
    	Calendar curr = Calendar.getInstance();
    	Date date = new Date();
    	
    	curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + 1*monthStep);
    	date = curr.getTime();
    	
    	return DateToStr(date, fmtStr);  	
    }
    
    /**
     * @功能描述：获取默认短格式化的日期字符串，格式为："yyyy-MM-dd"
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月16日 下午4:05:22
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param date
     * @return
     */
    public static String getDefaultShortDateStr(Date date) {
    	return DateToStr(date, "yyyy-MM-dd");
    }
    /**
     * @功能描述：获取默认短格式化的日期字符串，格式为："yyyy-MM-dd HH:mm:ss"
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月16日 下午4:05:22
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param date
     * @return
     */
    public static String getDefaultLongDateStr(Date date) {
    	return DateToStr(date, "yyyy-MM-dd HH:mm:ss");
    }
    
}
