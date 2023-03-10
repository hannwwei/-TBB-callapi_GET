package com.example.callapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// 設定 Spring Boot 應用程式的配置
@RestController
@SpringBootApplication
public class CallapiApplication {

 // 使用 @GetMapping 註釋指定 API 端點的路徑
 // 這裡指定的路徑是 "/opendata"

 // 主方法用於啟動 Spring Boot 應用程式
 public static void main(String[] args) {
  SpringApplication.run(CallapiApplication.class, args);
  OkHttpClient client = new OkHttpClient();

  Request request = new Request.Builder()
    .url("https://od.moi.gov.tw/api/v1/rest/datastore/301000000A-002073-001")
    .build();

  try (Response response = client.newCall(request).execute()) {
   if (response.isSuccessful()) {
    String jsonData = response.body().string();
    System.out.println(jsonData);
   } else {
    System.out.println("Failed to retrieve data: " + response.code());
   }
  } catch (Exception e) {
   System.out.println("Exception occurred: " + e.getMessage());
  }
 }

 @GetMapping("/api")
 public String callApi() {
  OkHttpClient client = new OkHttpClient();

  Request request = new Request.Builder()
    .url("https://od.moi.gov.tw/api/v1/rest/datastore/301000000A-002073-001")
    .build();

  try (Response response = client.newCall(request).execute()) {
   if (response.isSuccessful()) {
    String jsonData = response.body().string();
    return jsonData;
   } else {
    return "Failed to retrieve data: " + response.code();
   }
  } catch (Exception e) {
   return "Exception occurred: " + e.getMessage();
  }
 }

 @GetMapping("/opendata")
 public String getOpenData() {
  // 創建一個新的 RestTemplate 對象
  RestTemplate restTemplate = new RestTemplate();
  // 呼叫 OpenData URL 並取得回應資料
  String url = "https://ws.yunlin.gov.tw/001/Upload/539/opendata/0/1544/ba347b47-afc1-4bf7-bedb-78f8bff33da7.json";
  // 將回應資料返回給客戶端
  String response = restTemplate.getForObject(url, String.class);
  return response;
 }
}