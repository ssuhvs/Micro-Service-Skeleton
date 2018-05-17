package com.microservice.skeleton.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUrlConnect {
    //get请求
    public static String get(String url){
        HttpURLConnection conn = null;
        BufferedReader rd = null ;
        StringBuilder sb = new StringBuilder ();
        String line = null ;
        String response = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setUseCaches(false);
            conn.connect();
            rd  = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((line = rd.readLine()) != null ) {
                sb.append(line);
            }
            response = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{      
            try {
                if(rd != null){
                    rd.close();
                }
                if(conn != null){
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
    //post表单请求
    public static String post(String url, Map<String, String> form){
        HttpURLConnection conn = null;
        PrintWriter pw = null ;
        BufferedReader rd = null ;
        StringBuilder out = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        String line = null ;
        String response = null;
        for (String key : form.keySet()) {
            if(out.length()!=0){
                out.append("&");
            }
            out.append(key).append("=").append(form.get(key));
        }
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setUseCaches(false);
            conn.connect();
            pw = new PrintWriter(conn.getOutputStream());
            pw.print(out.toString());
            pw.flush();
            rd  = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((line = rd.readLine()) != null ) {
                sb.append(line);
            }
            response = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{      
            try {
                if(pw != null){
                    pw.close();
                }
                if(rd != null){
                    rd.close();
                }
                if(conn != null){
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
    //post字符串请求
    public static String post(String url, String rawBody){
        HttpURLConnection conn = null;
        PrintWriter pw = null ;
        BufferedReader rd = null ;
        StringBuilder sb = new StringBuilder ();
        String line = null ;
        String response = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setUseCaches(false);
            conn.connect();
            pw = new PrintWriter(conn.getOutputStream());
            pw.print(rawBody);
            pw.flush();
            rd  = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((line = rd.readLine()) != null ) {
                sb.append(line);
            }
            response = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{      
            try {
                if(pw != null){
                    pw.close();
                }
                if(rd != null){
                    rd.close();
                }
                if(conn != null){
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
     
    
   public static String sendPostRequest(String url,String param){  
       HttpURLConnection httpURLConnection = null;  
       OutputStream out = null; //写  
       InputStream in = null;   //读  
       int responseCode = 0;    //远程主机响应的HTTP状态码  
       String result="";  
       try{  
           URL sendUrl = new URL(url);  
           httpURLConnection = (HttpURLConnection)sendUrl.openConnection();  
           //post方式请求  
           httpURLConnection.setRequestMethod("POST");  
           //设置头部信息  
           httpURLConnection.setRequestProperty("headerdata", "ceshiyongde");  
           //一定要设置 Content-Type 要不然服务端接收不到参数  
           httpURLConnection.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");  
           //指示应用程序要将数据写入URL连接,其值默认为false（是否传参）  
           httpURLConnection.setDoOutput(true);  
           //httpURLConnection.setDoInput(true);   
             
           httpURLConnection.setUseCaches(false);  
           httpURLConnection.setConnectTimeout(30000); //30秒连接超时  
           httpURLConnection.setReadTimeout(30000);    //30秒读取超时  
           //传入参数  
           out = httpURLConnection.getOutputStream();  
           out.write(param.getBytes());  
           out.flush(); //清空缓冲区,发送数据  
           out.close();  
           responseCode = httpURLConnection.getResponseCode();  
           //获取请求的资源  
           BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));  
           result =br.readLine();  
       }catch(Exception e) {  
           e.printStackTrace();  
           
     }  
   return result;  
       
 }  
   
    public static void main(String[] args) {
        HttpUrlConnect h = new HttpUrlConnect();
        System.out.println(h.get(""));
        Map<String, String> form = new HashMap<String, String>();
        form.put("test", "test");
        System.out.println(h.post("", form));
    }
     
}