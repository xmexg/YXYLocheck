package yxyLoginEncrypt;

import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {

		// 开始
		System.out.println("优学院登录加密演示程序");
		System.out.println("=======================");
		String str, str2;// 用户登录时输入的帐号和密码
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入手机号:");
		str = scanner.nextLine();
		System.out.print("请输入密码:");
		str2 = scanner.nextLine();
		scanner.close();
		System.out.println("=======================");

		// jadx - package cn.ulearning.yxy.api;
		String ut = StringUtil.getLoginString(str, str2);
		System.out.println(" > ut:\t" + ut);

		// jadx - package cn.ulearning.yxy.api;
		HashMap hashMap = new HashMap();
		hashMap.put("loginName", str);
		hashMap.put("password", str2);
		hashMap.put("ut", ut);
		hashMap.put("device", "android");
		hashMap.put("appVersion", 1931);
		hashMap.put("webEnv", 2);
		hashMap.put("registrationId", "13065ffa4f1842c0297");
		HashMap hashMap2 = new HashMap();
		String y = StringUtil.getCStr(new Gson().toJson(hashMap));
		hashMap2.put("y", y);
		System.out.println(" - 第一轮y:\t"+y);
		String postbody = new Gson().toJson(hashMap2);
		System.out.println(" > 请求体\t:"+postbody);
//		ApiUtils.getInstance().post(format, new Gson().toJson(hashMap2), apiCallback);
	}

}
