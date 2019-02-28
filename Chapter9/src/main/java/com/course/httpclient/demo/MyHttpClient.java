package com.course.httpclient.demo;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {



    @Test
    public void test1(){
        //存放结果
        String result;
        //创建一个Request对象
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
//        HttpClient client = new DefaultHttpClient();
        //创建一个HttpClient对象
        HttpClient client = HttpClientBuilder.create().build();
//        CloseableHttpClient client1 = HttpClients.custom().setDefaultCookieStore().build();
        try {
            //执行Request请求
            HttpResponse execute = client.execute(httpGet);
            //所有的头部信息
            Header[] allHeaders = execute.getAllHeaders();
            for(Header header:allHeaders){
                System.out.println(header);
            }

            System.out.println("============================");
            //获得状态码信息
            StatusLine statusLine = execute.getStatusLine();
            System.out.println("返回的状态码信息:" + statusLine);

            System.out.println("============================");
            Header lastHeader = execute.getLastHeader("Transfer-Encoding");
            System.out.println("最后一个头部：" + lastHeader);

            Header connection = execute.getFirstHeader("Connection");
            System.out.println("连接状态：" + connection);

            Header[] headers = execute.getHeaders("Content-Type");
            for (Header h1:headers){
                System.out.println("内容类型：" + h1);
            }

            System.out.println("============================");
            //得到响应内容
            result = EntityUtils.toString(execute.getEntity(),"utf-8");
            System.out.println("返回内容：" + result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
