package com.zzrenfeng.test;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @功能描述：使用Durid的ConfigFilter对数据库密码加密
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
public class TestDuridEnOrDecrype {
	/**
	 * @功能描述：使用Durid的ConfigFilter对明文加密
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param enWord 明文
	 * @throws Exception 
	 */
	public static String encrypeByDurid(String enWord) throws Exception {
		String deWord = ConfigTools.encrypt(enWord);
		System.out.println(enWord + "的密文为：" + deWord);
		return deWord;
	}
	/**
	 * @功能描述：使用Durid的ConfigFilter对密文解密
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param deWord 密文
	 * @throws Exception 
	 */
	public static String decrypeByDurid(String deWord) throws Exception {
		String enWord = ConfigTools.decrypt(deWord);
		System.out.println(deWord + "的明文为：" + enWord);
		return enWord;
	}

	public static void main(String[] args) throws Exception {
		String word = "r00t";
		encrypeByDurid(word);
		//decrypeByDurid(encrypeByDurid(word));
		//decrypeByDurid("bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA==");
	}

}
