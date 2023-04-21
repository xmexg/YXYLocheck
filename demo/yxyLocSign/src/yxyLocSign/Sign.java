package yxyLocSign;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 开始定位签到
 *
 */
public class Sign {
	String TOKEN;
	int USERID;
	
	Sign(String token, String userId){
		this.TOKEN = token;
		this.USERID = Integer.parseInt(userId);
	}	

	/**
	 * 
	 * @param token 用户令牌
	 * @param relationId 活动id
	 * @param classID 班级id
	 * @param userID 用户id
	 * @param location 经度,纬度
	 * @return String 服务器响应
	 */
	public String LocSign(String relationId, String classID, String location) {
		return new MyWebRequest().PostRes(this.TOKEN, MyWebRequest.FUN_ACTIVITY_SIGN, MakeSignReqBody(relationId, classID, this.USERID, location));
	}
	
	public ArrayList<String> LocSignAll(String location) {
		return null;
	}
	
	/**
	 * 生成签到请求体
	 */
	private String MakeSignReqBody(String relationId, String classID, int userID, String location, String address,int enterWay, String attendanceCode) {
		JsonObject reqObject = new JsonObject();
		reqObject.addProperty("attendanceID", relationId);
		reqObject.addProperty("classID", classID);
		reqObject.addProperty("userID", userID);
		reqObject.addProperty("location", location);
		reqObject.addProperty("address", address==null?"":address);
		reqObject.addProperty("enterWay", enterWay);
		reqObject.addProperty("attendanceCode", attendanceCode==null?"":attendanceCode);
		return new Gson().toJson(reqObject);
	}
	/**
	 * 生成签到请求体
	 */
	private String MakeSignReqBody(String relationId, String classID, int userID, String location) {
		return MakeSignReqBody(relationId, classID, userID, location, null, 1, null);
	}
}
