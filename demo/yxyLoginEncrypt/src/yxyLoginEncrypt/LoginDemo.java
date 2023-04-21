package yxyLoginEncrypt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * 优学院登录演示
 * 
 * @version 0.1.2
 *
 */
public class LoginDemo {
	public static String device="android",
			registrationId="13065ffa4f1842c0297",
			appVersion="20230315",
			webEnv="1",
			User_Agent="App ulearning Android",
			Version="1.9.31",
			Uversion="2",
			Platform="android";

	public static void main(String[] args) {

		// 开始
		System.out.println("优学院登录加解密演示程序");
		Scanner scanner = new Scanner(System.in);
		System.out.print("请选择演示(1:加密,2:解密,3:完整登录演示):");
		String input = scanner.next();
		switch (input) {
			case "1":
				yxyEncryptDemo();
				return;
			case "2":
				yxyUnencryptDemo();
				return;
			case "3":
				yxyLoginDemo();
				return;
			default:
				System.out.println("输入错误");
				return;
		}
	}

	// 加密演示
	private static String yxyEncryptDemo() {
		System.out.println("=======================");
		String str, str2;// 用户登录时输入的帐号和密码
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入手机号:");
		str = scanner.nextLine();
		System.out.print("请输入密码:");
		str2 = scanner.nextLine();
		scanner.close();
		System.out.println("=======================");
		return yxyEncryptDemo(str, str2);
	}
	public static String yxyEncryptDemo(String str, String str2){
		try {
			str2 = EncryptUtils.md5Encrypt(str2);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		return postbody;
	}

	// 解密演示
	private static void yxyUnencryptDemo() {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入登录请求包里的result密文:");
		String requesty = input.nextLine();
		input.close();
		yxyUnencryptDemo(requesty);
	}
	public static String yxyUnencryptDemo(String requesty){
		String requesty_after = requesty.replace("\\n", "");
		String yHashmap = StringUtil.getRStr(requesty_after);
		System.out.println("\n解密响应包:\n"+yHashmap);
		return yHashmap;
	}
	
	// 完整登录演示
	private static String yxyLoginDemo() {
		return yxyLoginDemo(null,null);
	}
	public static String yxyLoginDemo(String phone, String pwd) {
		String postBody;
		if(phone==null||pwd==null)
			postBody = yxyEncryptDemo();
		else
			postBody = yxyEncryptDemo(phone, pwd);
		String getRes = null;
		try {
			getRes = posty(postBody);
		} catch (SocketTimeoutException e) {
			System.out.println("登录超时");
			JsonObject jo = new JsonObject();
			jo.addProperty("code", 0);
			jo.addProperty("result", "登录超时");
			return new Gson().toJson(jo);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(getRes == null) {
			System.out.println("无法获取响应包,可能密码错误");
			JsonObject jo = new JsonObject();
			jo.addProperty("code", 0);
			jo.addProperty("result", "无法获取响应包,可能密码错误");
			return new Gson().toJson(jo);
		}
		System.out.println("\n原始响应包:\n"+getRes);
		Gson gson = new Gson();
		JsonObject ResObject = gson.fromJson(getRes, JsonObject.class);
		int code = ResObject.get("code").getAsInt();
		if(code == 200) {
			String getresult = ResObject.get("result").getAsString();
			String newresult = yxyUnencryptDemo(getresult);// 把加密包解开
			JsonElement jsonElement = gson.fromJson(newresult, JsonElement.class);// 把解开的加密包转换成JsonElement类型
			ResObject.add("result", jsonElement);// 把解密后的json拼接到result里,切勿使用addProperty,他会转义
			getRes = new Gson().toJson(ResObject);
		}
		return getRes;
	}
	private static String posty(String postBody) throws Exception {
		URL url = new URL("https://apps.ulearning.cn/login/v2");
		URLConnection con = url.openConnection();
		con.setDoOutput(true);
		con.setConnectTimeout(6000);
		con.setRequestProperty("Accept-Language", "zh-cn");
		con.setRequestProperty("User_Agent",User_Agent);
		con.setRequestProperty("Uversion", Uversion);
		con.setRequestProperty("Version", Version);
		con.setRequestProperty("Platform", Platform);
		con.setRequestProperty("Content-Type","application/json;charset=utf-8");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		con.setRequestProperty("Connection", "close");
		con.connect();
		con.getOutputStream().write(postBody.getBytes("UTF-8"));
		BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream()),"UTF-8"));
		String line;
		StringBuffer response = new StringBuffer();
		while((line = in.readLine()) != null) {
			response.append(line);
		}
		in.close();
		return response.toString();
	}
}
