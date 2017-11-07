package com.zzrenfeng.test;

import java.io.File;

public class TestFile {
	
	public static void isExist() {
		//String filePath = "E:\\workspace\\ZZNUEvalGraduation\\WebContent\\upload\\2017\\09\\30\\0CF86EB1279BF85B783C00B9077093C9_微格_playable.mp4";
		
		String filePath = "/ZZNUEvalGraduation/WebContent/upload/2017/09/30/0CF86EB1279BF85B783C00B9077093C9_微格_playable.mp4";
		File file = new File(filePath);
		boolean i = file.exists();
		System.out.println("--------isExist()>>>>" + i);
	}
	
	public static void main(String[] args) {
		isExist();
	}

}
