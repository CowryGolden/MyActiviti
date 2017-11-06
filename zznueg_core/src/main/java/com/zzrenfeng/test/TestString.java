package com.zzrenfeng.test;

import java.io.File;

public class TestString {

	public static void main(String[] args) {
		String filePath = "upload\\2017\\08\\10\\616316b4c0d5a34f52cd895c41ba0e77_zzsf_final.zip";
		//String filePathTemp = filePath.replace("\\", "/");
		String fileName = filePath.substring(filePath.indexOf("_")+1, filePath.length());
		
		System.out.println(fileName);
		
		String str = new String();
		//System.out.println("++++++++++++++(0>null)====>>>>" + (0 > Integer.parseInt(null)));
		//System.out.println("++++++++++++++(''->float)====>>>>" + (Float.parseFloat(str)));
		System.out.println("++++++++++++++(str.equals(\"\"))====>>>>" + (str.equals("")));
		System.out.println("++++++++++++++(str.length() == 0)====>>>>" + (str.length() == 0));
		System.out.println("++++++++++++++(str == null)====>>>>" + (str == null));
		System.out.println("++++++++++++++(str == \"\")====>>>>" + (str == ""));
		
		System.out.println("===================================>>>>" + Double.parseDouble("0"));
		
		String s = "F:\\ade\\upload\\2017\\10\\10\\FDAFAFAFDAFADFADFAF_test.pdf";
		String ss = s.substring(s.indexOf("upload") + "upload".length() + 1);
		System.out.println("##############====upload_indexof====>>>>" + s.substring(s.indexOf("upload") + "upload".length() + 1));
		System.out.println("##############====upload_indexof====>>>>" + filePath.substring(filePath.indexOf("upload") + "upload".length() + 1 ));
		System.out.println("##############====file_separator_indexof====>>>>" + s.indexOf(File.separator));
		System.out.println("##############====subString_path====>>>>" + s.substring(0, s.lastIndexOf(File.separator)));
		System.out.println("##############====subString_path====>>>>" + ss.substring(0, ss.lastIndexOf(File.separator)));
		System.out.println("##############====filename====>>>>" + s.substring(s.lastIndexOf(File.separator) + 1));
	}

}
