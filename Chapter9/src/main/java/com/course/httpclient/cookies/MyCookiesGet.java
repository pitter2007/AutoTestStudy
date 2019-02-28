package com.course.httpclient.cookies;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class MyCookiesGet {

    private String url;
    private ResourceBundle bundle;

    @BeforeTest
    public void beforeTest(){
        //读取application配置文件
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");

    }

    @Test
    public void testGetCookie(){
        //拼接测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        BasicCookieStore cookieStore  = new BasicCookieStore();
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpClient client = new DefaultHttpClient();
//          HttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();


        HttpGet httpGet = new HttpGet(testUrl);
        try {
            HttpResponse execute = client.execute(httpGet);
            String s = EntityUtils.toString(execute.getEntity());
            System.out.println(s);
            System.out.println("=======================");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取cookie信息
//        CookieStore cookieStore = ((DefaultHttpClient) client).getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for(Cookie cookie:cookies){
            System.out.println("cookie信息是---------" + cookie.getName()+";" + cookie.getValue());
//            System.out.println(cookie.getValue());
        }


/*        List<Cookie> cookies = cookieStore.getCookies();
        System.out.println(cookies.size());
        for(Cookie cookie:cookies){
//            System.out.println("cookie信息是---------" + cookie.getName()+";" + cookie.getValue());
            System.out.println(cookie.getName());
        }*/
    }
}
