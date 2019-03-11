package com.course;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetParamTest {

    public static void main(String[] args) throws URISyntaxException {

        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置URIBuilder
        URIBuilder uriBuilder = new URIBuilder("https://yun.itheima.com/search");
        //设置参数
        uriBuilder.addParameter("key","java");//设置单个参数
//        uriBuilder.addParameter("ie","utf-8").setParameter("wd","selenium");//设置多个参数

        //创建HttpGet对象，设置URL访问地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        System.out.println("发起请求的URL是：" + httpGet);
        CloseableHttpResponse response = null;
        //使用HttpClient发起请求，获取response
        try {
            response = httpClient.execute(httpGet);

            //解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭reponse
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //关闭httpClient
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
