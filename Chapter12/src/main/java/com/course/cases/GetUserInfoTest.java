package com.course.cases;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取userId为1的用户")
    public void getUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoTest = session.selectOne("getUserInfoTest",1);
        System.out.println(getUserInfoTest.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        JSONArray resultJson = getJsonResult(getUserInfoTest);
        User user = session.selectOne(getUserInfoTest.getExpected(),getUserInfoTest);
        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
        Assert.assertEquals(jsonArray,resultJson);

    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoTest) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",getUserInfoTest.getUserId());
        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.client = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        String result;
        HttpResponse response = TestConfig.client.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        List resultList = Arrays.asList(result);
        JSONArray array = new JSONArray(resultList);

        return array;
    }
}
