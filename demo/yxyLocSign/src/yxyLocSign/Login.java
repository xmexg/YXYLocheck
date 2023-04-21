package yxyLocSign;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 登录相关
 *
 */
public class Login {
	

	/** 
	 *  传入手机号和密码,传出token或null
	 * @param phone
	 * @param passwd
	 * @return [token,userId] / null
	 */
	public static String[] getToken(String phone, String passwd) {
		String response = login(phone, passwd);
		Gson gson = new Gson();
		JsonObject joout = gson.fromJson(response, JsonObject.class);
		int code = joout.get("code").getAsInt();
		if(code != 200) {
			return null;
		}
		JsonObject joresult = gson.fromJson(joout.get("result").getAsJsonObject(), JsonObject.class);
		return new String[] {joresult.get("token").getAsString(), joresult.get("userID").getAsString()};
	}
	
	// 传入手机号和密码,传出解密后的响应包
	public static String login(String phone, String passwd) {
		return yxyLoginEncrypt.LoginDemo.yxyLoginDemo(phone, passwd);
	}

}
