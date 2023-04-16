package yxyLoginEncrypt;

import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) throws Exception {

		// 开始
		System.out.println("优学院登录加解密演示程序");
		Scanner scanner = new Scanner(System.in);
		System.out.print("请选择演示(1:加密,2解密):");
		String input = scanner.next();
		switch (input) {
			case "1":
				yxyEncryptDemo();
				return;
			case "2":
				yxyUnencryptDemo();
				return;
			default:
				System.out.println("输入错误");
				return;
		}
	}

	// 加密演示
	public static void yxyEncryptDemo() throws Exception {
		System.out.println("=======================");
		String str, str2;// 用户登录时输入的帐号和密码
		String device="android",registrationId="13065ffa4f1842c0297",appVersion,webEnv;
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入手机号:");
		str = scanner.nextLine();
		System.out.print("请输入密码:");
		str2 = scanner.nextLine();
		str2 = EncryptUtils.md5Encrypt(str2);
		System.out.print("请输入软件版本号(一串数字,默认20230315):");
		appVersion = scanner.nextLine();
		if(appVersion.length()==0)
			appVersion = "20230315";
		System.out.print("请输入网络类型(0-4,默认1):");
		webEnv = scanner.nextLine();
		if(webEnv.length()==0)
			webEnv = "1";
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
		hashMap.put("device", device);
		hashMap.put("appVersion", appVersion);
		hashMap.put("webEnv", webEnv);
		hashMap.put("registrationId", registrationId);
		HashMap hashMap2 = new HashMap();
		String y = StringUtil.getCStr(new Gson().toJson(hashMap));
		hashMap2.put("y", y);
		System.out.println("\n y加密");
		System.out.println(" - 第一轮y:\t"+y);
		String postbody = new Gson().toJson(hashMap2);
		System.out.println(" > 请求体:\t"+postbody);
	}

	// 解密演示
	private static void yxyUnencryptDemo() {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入登录请求包里的result密文:");
		String requesty = input.nextLine();
		input.close();
		String requesty_after = requesty.replace("\\n", "");
		System.out.println("替换后:\t"+requesty_after);
		String yHashmap = StringUtil.getRStr(requesty_after);
		System.out.println("\n"+yHashmap);
	}
}
