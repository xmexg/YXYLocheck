package yxyLocSign;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 用于发送和接收数据
 * @param <REQUEST_TYPE_GET>
 *
 */
public class MyWebRequest {

	HashMap<String, String> HEAD = new HashMap<String, String>();
	static final int
			GET_COURSE_LIST=20,// 获取指定课程活动,
			GET_COURSE_HomeActivity=21,// 获取指定课程信息
			GET_COURSE_ACTIVITY=22,// 获取指定课程活动
			FUN_ACTIVITY_SIGN=30;// 位置签到
	private static String 
			GET_COURSE_LIST_URL="https://courseapi.ulearning.cn/courses/students?publishStatus=1&pn=1&ps=20&type=1",// 获取课程列表
			GET_COURSE_HomeActivity_URL="https://courseapi.ulearning.cn/appHomeActivity/v3/",// 获取指定课程信息,要后续拼接
			GET_COURSE_ACTIVITY_URL="https://apps.ulearning.cn/newAttendance/getAttendanceForStu/",// 获取指定课程活动,要后续拼接
			FUN_ACTIVITY_SIGN_URL="https://apps.ulearning.cn/newAttendance/signByStu";// 位置签到
	
	/**
	 * 使用默认请求头
	 */
	MyWebRequest(){
		HEAD.put("Accept-Language", "zh-cn");
		HEAD.put("User-Agent", "App ulearning Android");
		HEAD.put("Uversion", "2");
		HEAD.put("Version", "1.9.31");
		HEAD.put("appVersion", "20230315");
		HEAD.put("registrationId", "13065ffa4f1842c0297");
		HEAD.put("Accept-Encoding", "deflate");// 在apk中使用gzip进行了压缩,但没必要,这里使用原始值
		HEAD.put("Platform", "android");
		HEAD.put("webEnv", "1");
		HEAD.put("device", "android");
	}
	
	/**
	 * 自定义请求头
	 * @param head
	 */
	MyWebRequest(HashMap<String, String> head){
		this.HEAD = head;
	}
	
	
	/**
	 * 使用GET获取信息
	 * 传入:token令牌(必填)
	 * type类型(必填)
	 * courseId获取课程相关时填(选填)
	 * userId获取课程活动时填,随便填也可以获取到面向全班的活动(选填)
	 * 传出:响应
	 */
	public String GetRes(String token, int type, String courseId, String userId) {
		if(token != null)// 没有token就不设置token
			HEAD.put("Authorization", token);
		String res = null;// 获取到的响应
		switch(type) {
			case GET_COURSE_LIST: 
				res = Get(GET_COURSE_LIST_URL, HEAD, null);
				break;
			case GET_COURSE_HomeActivity:
				res = Get(GET_COURSE_HomeActivity_URL+courseId, HEAD, null);
				break;
			case GET_COURSE_ACTIVITY:
				res = Get(GET_COURSE_ACTIVITY_URL+courseId+"/"+userId, HEAD, null);
				break;
			default:
				break;
		}
		return res;
	}
	
	/**
	 * 使用POST获取信息
	 * @param token 用户令牌
	 * @param type 目的地
	 * @param RequestBody 请求体
	 * @return 响应
	 */
	public String PostRes(String token, int type, String RequestBody) {
		if(token != null)// 没有token就不设置token
			HEAD.put("Authorization", token);
		String res = null;// 获取到的响应
		switch (type) {
			case FUN_ACTIVITY_SIGN:
				res = Post(FUN_ACTIVITY_SIGN_URL, HEAD, RequestBody);
				break;
			default:
				break;
		}
		return res;
	}
	
	/**
	 * 发送get请求
	 * 传入:请求类型,网站链接,请求头,请求体
	 * 传出:响应
	 */
	public String Get(String Url, HashMap<String, String> head, String body) {
		StringBuffer response = null;
		try {
			URL url = new URL(Url);
			URLConnection con =  url.openConnection();
			con.setConnectTimeout(6000);
			con.setDoInput(true);// 读取数据
			Iterator<Entry<String, String>> iterator = head.entrySet().iterator();
			while (iterator.hasNext()) {// 设置请求头
				Entry<String, String> entry = iterator.next();
				con.setRequestProperty(entry.getKey(), entry.getValue());
	   }
			if(body != null) {// 有请求体
				con.setDoOutput(true);// 写入数据
				con.getOutputStream().write(body.getBytes("UTF-8"));
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			String line;
			response = new StringBuffer();
			while((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response.toString();
	}
	
	public String Post(String Url, HashMap<String, String> head, String body) {
		StringBuffer response;
		try {
			URL url = new URL(Url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			int statusCode = con.getResponseCode();
			if(statusCode != 200) {
				return null;
			}
			con.setConnectTimeout(6000);
			con.setRequestMethod("POST");
			Iterator<Entry<String, String>> iterator = head.entrySet().iterator();
			while (iterator.hasNext()) {// 设置请求头
				Entry<String, String> entry = iterator.next();
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
			if(body != null) {// 有请求体
				con.setDoOutput(true);// 写入数据
				con.getOutputStream().write(body.getBytes("UTF-8"));
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			String line;
			response = new StringBuffer();
			while((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
	  } catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response.toString();
	}
	
}
