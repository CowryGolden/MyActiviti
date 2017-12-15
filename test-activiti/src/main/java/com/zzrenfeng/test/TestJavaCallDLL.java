package com.zzrenfeng.test;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @功能描述：使用java调用dll；使用jna.jar包，就可以使用JNA的强大功能方便地调用动态链接库中的C函数。
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月5日 上午10:41:06
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class TestJavaCallDLL {
	
	public interface CLibrary extends Library {
		CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
		
		void printf(String fromat, Object... args);
	}
	
	public static void main(String[] args) {
		CLibrary.INSTANCE.printf("Hello, World\n");
		for (int i = 0; i < args.length; i++) {
			CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
		}
	}

}
