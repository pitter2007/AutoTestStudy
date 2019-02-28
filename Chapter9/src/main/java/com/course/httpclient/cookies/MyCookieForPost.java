package com.course.httpclient.cookies;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookieForPost {
    private  String url;
    private String getUri;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public String initUrl(){
        this.bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = this.bundle.getString("test.url");
        getUri = this.bundle.getString("getCookies.uri");
        String url_v1 = url + getUri;
        return url_v1;
    }

    @Test
    public void receiveCookies(){
//        HttpClientContext context = new HttpClientContext();
        this.store = new BasicCookieStore();
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(this.store).build();
        HttpGet httpGet = new HttpGet(initUrl());
        try {
            HttpResponse execute = client.execute(httpGet);
//            System.out.println("**************" + this.store);
        } catch (IOException e) {
            e.printStackTrace();
        }

/*        List<Cookie> cookies = this.store.getCookies();
        for (Cookie cookie:cookies){
            this.store.addCookie(cookie);
//            System.out.println("==================" + this.store);
            System.out.println("获取到的cookies是：" +cookie.getName() +";" + cookie.getValue());
        }*/
    }

    @Test(dependsOnMethods = {"receiveCookies"})
    public void testPostMethod() throws IOException {
        String postUrl = this.url + bundle.getString("test.post.with.cookies");
        //创建一个client对象        //设置cookie
//        System.out.println("-----------------" + this.store);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(this.store).build();
        //创建一个post方法对象
        HttpPost post = new HttpPost(postUrl);

        //添加参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","zhangsan");
        jsonObject.put("age","28");


        //设置请求头信息  设置Header
        post.setHeader("content-type","application/json");

        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        post.setEntity(entity);

        //声明一个对象进行响应结果的存储
        String result;

        //执行post方法
        HttpResponse execute = client.execute(post);

        //获取响应结果
        result = EntityUtils.toString(execute.getEntity());
//        System.out.println("-----------------" + result);
        //处理结果，判断返回结果是否符合预期

        JSONObject jsonObject1 = JSONObject.parseObject(result);
        String status = jsonObject1.getString("status");
//        System.out.println(status);

        int statusCode = execute.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(status,"1");

    }
}
