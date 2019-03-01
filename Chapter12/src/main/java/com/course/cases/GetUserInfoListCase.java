package com.course.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
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
//import org.json.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetUserInfoListCase {

    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男性的用户信息")
    public void getUserListInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase", 1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
        // 发送请求获取结果
        JSONObject resultJson = getJsonResult(getUserListCase);
        // 验证结果
        List<User> userList = session.selectList(getUserListCase.getExpected(), getUserListCase);

        for (User user : userList) {
            System.out.println("获取的User是：" + user.toString());
        }

        JSONArray userListJson = (JSONArray)JSONArray.toJSON(userList);
//        System.out.println("userList***************" + userListJson);
//        System.out.println("**************" + resultJson.size());
        Assert.assertEquals(userListJson.size(), resultJson.size());

        for (int i = 0; i < resultJson.size(); i++) {
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(), actual.toString());
        }
    }
    private JSONObject getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName", getUserListCase.getUserName());
        param.put("sex", getUserListCase.getSex());
        param.put("age", getUserListCase.getAge());
        param.put("expected", getUserListCase.getExpected());
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.client = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();

        String result;
        HttpResponse response = TestConfig.client.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(result);
        return jsonObject;
    }
}
