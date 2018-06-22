package com.aek.ebey.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {
	
	/**
	 * 
	 * 得到会议编码
	 */
	public static String getMeetingNum() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "HY" + str + rannum;
		
	}
	
	/**
	 * 
	 * 得到培训编码
	 */
	public static String getTrainNum() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "PX" + str + rannum;
		
	}

}
