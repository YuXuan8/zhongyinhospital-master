package com.pj.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class IPUtil {
    public static void main(String args[]) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = "https://ip.useragentinfo.com/json";
        HttpGet httpGet=new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(httpGet);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
                System.out.println(jsonObject);
                String ip = jsonObject.getString("ip");
                System.out.println(ip);
            }
        } catch (Exception e) {
            System.out.println("服务间接口调用出错！");
            e.printStackTrace();
        }
    }
}
