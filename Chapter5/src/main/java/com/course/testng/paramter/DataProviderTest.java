package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name是：" + name + ",age是：" + age);
    }

    @DataProvider(name = "data")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"张三",20},
                {"李四",25},
                {"王五",33}
        };
        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test1111方法 name：" +name+";age" + age );

    }

    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("test2222方法 name：" +name+";age" + age );
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result = null;
        if(method.getName().equals("test1")){
            result= new Object[][]{
                    {"张三",25},
                    {"李四",26},
            };
        }else if(method.getName().equals("test2")){
            result= new Object[][]{
                    {"王五",28},
                    {"赵六",208}
            };
        }
        return result;
    }
}
