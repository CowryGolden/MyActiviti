package com.zzrenfeng.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestBigDecimal {

	public static void main(String[] args) {
		double data = 0.051111122111111;
		Object dataObj = "0.2222";
        
//		System.out.println("===============double2Percent1>>>>" + double2Percent1(data));
//		System.out.println("===============double2Percent2>>>>" + double2Percent2(data));
//		System.out.println("===============object2Percent>>>>" + object2Percent(dataObj));
		System.out.println("===============fixedDigitNum>>>>" + fixedDigitNum(5));
		System.out.println("+++++++++++++++" + (Double)dataObj);
	}
	
	public static String fixedDigitNum(int data) {
		DecimalFormat df = new DecimalFormat("0000");
		String fixedStr = df.format(data);
        return fixedStr;
	}
	
	public static String double2Percent1(double data) {
		DecimalFormat df = new DecimalFormat("#0.00%");
        String perStr = df.format(data);
        return perStr;
	}
	
	public static String double2Percent2(double data) {
		BigDecimal bd = (new BigDecimal(data)).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		String perStr = bd + "%";
		return perStr;
	}
	
	public static String object2Percent(Object data) {
		DecimalFormat df = new DecimalFormat("#0.00%");
        String perStr = df.format(data);
        return perStr;
	}

}
