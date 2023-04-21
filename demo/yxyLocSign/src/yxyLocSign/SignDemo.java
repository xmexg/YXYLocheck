package yxyLocSign;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SignDemo {

	/**
	 * 优学院获取课程与签到演示
	 * 
	 * @version 0.1.0
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("优学院签到演示");
		System.out.println("经纬度查询网:https://api.map.baidu.com/lbsapi/getpoint/index.html");
		Scanner input = new Scanner(System.in);
		System.out.print("请输入手机号:");
		String phone = input.nextLine();
		System.out.print("请输入密码:");
		String passwd = input.nextLine();
		System.out.print("请设置经度:");
		String longitude = input.nextLine();
		System.out.print("请设置维度:");
		String latitude = input.nextLine();
		StartSignAll(phone, passwd, longitude, latitude);
		input.close();
	}
	
	public static String StartSignAll(String phone, String passwd, String longitude, String latitude) {
		String loginInfo[] = Login.getToken(phone, passwd);
		if(loginInfo == null) {
			System.out.println("登录失败");
			return null;
		}
		Course course = new Course(loginInfo[0], loginInfo[1]);
		Sign sign = new Sign(loginInfo[0], loginInfo[1]);
		ArrayList<JsonObject> CourseHomeActivities = course.getCourseHomeActivities_All();
		String printFcourse = "%-8s%-20s%-20s%-8s\n";
		System.out.printf("\n获取到如下正在进行的签到活动:\n"+printFcourse,"活动id","活动标题","班级", "是否签到成功");
		for(JsonObject jo_c_HA : CourseHomeActivities) {
			JsonArray ja_c_HA_otherActivityDTOList = jo_c_HA.getAsJsonArray("otherActivityDTOList");
			for(JsonElement je_c_HA_otherActivityDTOList : ja_c_HA_otherActivityDTOList) {
				JsonObject jo_c_c_HA_otherActivityDTOList = je_c_HA_otherActivityDTOList.getAsJsonObject();
				String relationId = jo_c_c_HA_otherActivityDTOList.get("relationId").getAsString();
				String classID = jo_c_c_HA_otherActivityDTOList.get("classID").getAsString();
				String location = longitude + "," + latitude;
				String sign_res = sign.LocSign(relationId, classID, location);
				boolean sign_ok = false;
				if(sign_res != null) {
					JsonObject jo_sign = JsonParser.parseString(sign_res).getAsJsonObject();
					if(jo_sign.get("status").getAsInt() == 200) {
						sign_ok = true;
					}
				}
				System.out.printf(printFcourse, jo_c_c_HA_otherActivityDTOList.get("relationId").getAsString(), jo_c_c_HA_otherActivityDTOList.get("title").getAsString(), jo_c_c_HA_otherActivityDTOList.get("classes").getAsString(), sign_ok?"是":"否");
			}
		}
		return null;
	}

}
