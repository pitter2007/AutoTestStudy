package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
    public void updateUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoTest = session.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoTest.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

/*        int result = getResult(updateUserInfoTest);
        User user = session.selectOne(updateUserInfoTest.getExpected(),updateUserInfoTest);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);*/
    }


    @Test(dependsOnGroups = "loginTrue",description = "删除用户信息")
    public void deleteUser() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoTest = session.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoTest.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

/*        int result = getResult(updateUserInfoTest);
        User user = session.selectOne(updateUserInfoTest.getExpected(),updateUserInfoTest);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);*/
    }

/*
    private int getResult(UpdateUserInfoCase updateUserInfoTest) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",updateUserInfoTest.getId());
        param.put("userName",updateUserInfoTest.getUserName());
        param.put("sex",updateUserInfoTest.getSex());
        param.put("age",updateUserInfoTest.getAge());
        param.put("permission",updateUserInfoTest.getPermission());
        param.put("isDelete",updateUserInfoTest.getIsDelete());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        TestConfig.client = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        String result;
        HttpResponse response = TestConfig.client.execute(post);
        result  = EntityUtils.toString(response.getEntity(),"utf-8");
        return Integer.parseInt(result);
    }*/

}
