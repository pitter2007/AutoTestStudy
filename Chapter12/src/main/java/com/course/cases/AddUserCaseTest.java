package com.course.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.AddUserCase;
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

public class AddUserCaseTest {
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException{
        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);

        //发请求
        String result = getResult(addUserCase);

        //验证返回结果
        User user = session.selectOne("addUser",addUserCase);
        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);

    }

    private String getResult(AddUserCase addUserCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject parm = new JSONObject();
        parm.put("userName",addUserCase.getUserName());
        parm.put("password",addUserCase.getPassword());
        parm.put("age",addUserCase.getAge());
        parm.put("sex",addUserCase.getSex());
        parm.put("permission",addUserCase.getPermission());
        parm.put("isDelete",addUserCase.getIsDelete());

        //设置头信息
        post.addHeader("content-type","application/json");
        StringEntity entity = new StringEntity(parm.toString(),"utf-8");
        post.setEntity(entity);

        //设置cookies
//        System.out.println("adduserCookies" + TestConfig.store);
        TestConfig.client = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();

        String result;//存放 返回结果

        HttpResponse response = TestConfig.client.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return result;
    }


}
