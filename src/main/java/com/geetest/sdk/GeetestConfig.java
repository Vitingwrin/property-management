package com.geetest.sdk;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeetestConfig {

	// 填入自己的captcha_id和private_key
	private static final String GEETEST_ID = "d314ce0f137bc8ebe8e9b8cce081d258";
	private static final String geetest_key = "205b30a34b4efd706f8c97ce0f2de6ba";
	private static final boolean newfailback = true;

	public static final String getGeetest_id() {
		return GEETEST_ID;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}
	
	public static final boolean isnewfailback() {
		return newfailback;
	}

}
