package com.microservice.skeleton.auth;

import java.util.HashMap;
import java.util.Map;

//模拟客户端请求  
public class TestPasswordMode {  
  
    public static void main(String[] args) {  
      String url = "http://localhost:9030/uaa/oauth/token";
      String data = null ;
      data = getToken(url);
      System.out.println("<=========data:"+data);  
    
// {"access_token":"0dc0ffc2-5c7b-43db-8ef6-0a50e7dadf26","token_type":"bearer","refresh_token":"de0f15ea-fa03-49b2-ab4b-8c5df6c5caf0","expires_in":42944,"scope":"app"}
// {"access_token":"0dc0ffc2-5c7b-43db-8ef6-0a50e7dadf26","token_type":"bearer","refresh_token":"de0f15ea-fa03-49b2-ab4b-8c5df6c5caf0","expires_in":42818,"scope":"app"}

//      Map mapRefresh = new HashMap<>();
//      mapRefresh.put("grant_type", "refresh_token");
//      mapRefresh.put("username", "admin");
//      mapRefresh.put("password", "123456");
//      mapRefresh.put("client_id", "webApp");
//      mapRefresh.put("client_secret", "webApp");
//      mapRefresh.put("scope", "app");
//      data = HttpUrlConnect.post(url, mapRefresh);
//      System.out.println("<=========refresh:"+data);  
    }

	private static String getToken(String url) {
		String data;
		Map map = new HashMap<>();
		  map.put("username", "admin");
		  map.put("password", "123456");
		  map.put("grant_type", "password");//密码模式
		  map.put("client_id", "webApp");//数据库配置
		  map.put("client_secret", "webApp");//数据库配置
		  map.put("scope", "app");//数据库配置
		  
		  data = HttpUrlConnect.post(url, map);
		return data;
	}  
 
}  
//服务端的一个方法（客户端请求到此服务端方法）