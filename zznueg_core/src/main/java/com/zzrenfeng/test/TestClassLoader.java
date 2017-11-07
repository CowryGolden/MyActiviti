package com.zzrenfeng.test;

public class TestClassLoader {
	
	public void getCurrClassPath() {
//		System.out.println(this.getClass().getName());
		System.out.println(
			Thread.currentThread().getStackTrace()[1].getClassName()
		);		
	}
	
	public void getCurrMethodName() {
		System.out.println(
			Thread.currentThread().getStackTrace()[1].getMethodName()
		);
	}

	public static void main(String[] args) {
		new TestClassLoader().getCurrClassPath();
		new TestClassLoader().getCurrMethodName();
		
		StringBuffer sb = new StringBuffer();
		sb.append("ClassName:");
		sb.append(Thread.currentThread().getStackTrace()[2].getClassName());
		sb.append("\n");
		sb.append("MethodName:");
		sb.append(Thread.currentThread().getStackTrace()[2].getMethodName());
		sb.append("\n");
		sb.append("LineNum:");
		sb.append(Thread.currentThread().getStackTrace()[2].getLineNumber());
		sb.append("\n");
		System.out.println(sb.toString());
	}

}
