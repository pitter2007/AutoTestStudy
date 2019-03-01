package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.model.InterfaceName;
import com.course.config.TestConfig;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTest {


    @BeforeTest(groups = "loginTrue", description = "测试准备工作,获取HttpClient对象")
    public void beforeTest() {

        TestConfig.store = new BasicCookieStore();
        TestConfig.client = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
    }


    @Test(groups = "loginTrue", description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发送请求
        String result = getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }


    @Test(groups = "loginFalse",description = "用户登陆失败接口")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //发送请求
        String result = getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject parm = new JSONObject();
        parm.put("userName", loginCase.getUserName());
        parm.put("password", loginCase.getPassword());

        System.out.println("param" + parm);
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(parm.toString(), "utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.client.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");

        TestConfig.store.getCookies();
        System.out.println("testcofig.cookie" + TestConfig.store);
        return result;

    }
}
