package com.zzrenfeng.test;

import java.util.Date;

import com.zzrenfeng.base.utils.DateUtil;

public class TestDate {

	public static void main(String[] args) {
		Date now = new Date();
		String endDateStr = "2017-09-01 00:00:00";
		Date endDate = DateUtil.StrToDate(endDateStr, null);
		String flag = now.getTime() > endDate.getTime() ? "1" : "0";
		
		System.out.println("-------------(now>endDate)>>>>" + flag);	
	}

}
