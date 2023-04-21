package yxyLocSign;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 课程相关
 *
 */
public class Course {
	private String TOKEN;
	private String USERID;
	
	/**
	 * 通过令牌和用户id初始化
	 * @param token
	 * @param userId
	 */
	Course(String token, String userId){
		this.TOKEN = token;
		this.USERID = userId;
	}
	
	/**
	 * 传入登录后的令牌
	 * 传出该帐号的所有课程
	 * @param token
	 * @return JsonArray
	 */
	public JsonArray getCourseList() {
		Gson gson = new Gson();
		MyWebRequest mwr = new MyWebRequest();
		JsonObject jo_courseList_result = gson.fromJson(mwr.GetRes(this.TOKEN, MyWebRequest.GET_COURSE_LIST, null, null), JsonObject.class);
		if(jo_courseList_result == null)
			return null;
		JsonArray jo_courseList = jo_courseList_result.get("courseList").getAsJsonArray();// 获取课程列表
		if(jo_courseList == null) {
			System.out.println("没有课程");
			return null;
		}
		// 输出课程信息
		String printFcourse = "%-8s%-10s%-8s%-10s%-8s%-50s%-20s%-20s";
		System.out.printf("\n获取到如下课程:\n"+printFcourse+"\n","课程id","课程代码","班级id","学生id","教师","课程名称","学校","班级");
		for(JsonElement je_courseList : jo_courseList) {
			JsonObject jo_c = je_courseList.getAsJsonObject();
			System.out.printf(printFcourse+"\n", jo_c.get("id").getAsString(), jo_c.get("courseCode").getAsString(), jo_c.get("classId").getAsString(), jo_c.get("classUserId").getAsString(), jo_c.get("teacherName").getAsString(), jo_c.get("name").getAsString(), jo_c.get("creatorOrgName").getAsString(), jo_c.get("className").getAsString());
		}
		return jo_courseList;
	}
	
	/**
	 * 获取课程活动(appHomeActivity)
	 * 传入课程id
	 */
	public JsonObject getCourseHomeActivity(String courseId) {
		String jo_courseList_result = new MyWebRequest().GetRes(this.TOKEN, MyWebRequest.GET_COURSE_HomeActivity, courseId, this.USERID);
		if(jo_courseList_result == null)
			return null;
		JsonObject jsonobject = JsonParser.parseString(jo_courseList_result).getAsJsonObject();
		
		return jsonobject;
	}
	
	/**
	 * 获取所有课程活动(appHomeActivity)
	 */
	public ArrayList<JsonObject> getCourseHomeActivities_All() {
		JsonArray courseList = getCourseList();// 获取课程列表
		if(courseList == null) {
			return null;
		}
		ArrayList<JsonObject> courseHomeActivities = new ArrayList<JsonObject>();
		for(JsonElement je_courseList : courseList) {
			JsonObject jo_c = je_courseList.getAsJsonObject();
			JsonObject jo_c_HA = getCourseHomeActivity(jo_c.get("id").getAsString());
			if(jo_c_HA != null)
				jo_c_HA.addProperty("classID", jo_c.get("classId").getAsString());
				courseHomeActivities.add(jo_c_HA);
		}
		return courseHomeActivities;
	}

}
