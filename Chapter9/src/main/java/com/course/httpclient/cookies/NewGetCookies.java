package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class NewGetCookies {

    private String url;
    private String uri;
    private ResourceBundle application;
    private CookieStore cookieStore;

    @BeforeTest
    public void initUrl() {
        application = ResourceBundle.getBundle("application", Locale.CHINA);
        url = application.getString("test.url");
    }

    @Test
    public void receiveCookies() {

        uri = application.getString("getCookies.uri");
        String testUrl = url + uri;

        this.cookieStore = new BasicCookieStore();
        HttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();

        HttpGet httpGet = new HttpGet(testUrl);

        try {
            HttpResponse execute = httpClient.execute(httpGet);
            String s = EntityUtils.toString(execute.getEntity());
            System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取的cookie信息
        List<Cookie> cookies = this.cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("获取到的cookie值是：" + cookie.getName() + ";" + cookie.getValue());

        }
    }

    @Test(dependsOnMethods = {"receiveCookies"})
    public void requestWithCookies(){
        uri = application.getString("requestCookies.uri");
        String url_v = url + uri;
        HttpGet get = new HttpGet(url_v);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(this.cookieStore).build();
//        HttpClient client = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        try {
            HttpResponse execute = client.execute(get);
            int statusCode = execute.getStatusLine().getStatusCode();
            if(statusCode == 200){
                String s = EntityUtils.toString(execute.getEntity());
                System.out.println("携带cookies访问成功：" + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
