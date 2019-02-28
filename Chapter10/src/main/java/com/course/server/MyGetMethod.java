package com.course.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {


    //返回cookie信息的接口
    @RequestMapping(value = "/getCookiesRequest",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取cookie值",httpMethod = "GET")
    //httpMethod的值需要大写
    public String getCookies(HttpServletResponse response){
        /*
        HttpServeletRequest   装请求信息
        HttpServeletResponse  装响应信息
        */
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }

    //携带cookie信息的接口
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "这个方法需要携带cookie信息方法",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "必须携带cookie信息来";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") & cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息才能访问的get请求";
            }
        }
        return "必须携带cookie信息来";
    }

    /*
    开发一个需要携带参数才能访问的get请求
    第一种实现方式url:key=value&key=value;
    模拟获取商品列表
    */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "模拟获取商品列表方法一",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",100);
        myList.put("衬衫",300);
        return myList;
        }

    /*
    开发一个需要携带参数才能访问的get请求
    第二种实现方式url:ip:port/get/with/param/10/20
    模拟获取商品列表
    */

    @RequestMapping(value = "/get/with/parm/{start}/{end}")
    @ApiOperation(value = "模拟获取商品列表方法二",httpMethod = "GET")
    public Map<String,Integer> myGetList(@PathVariable Integer start,
                                         @PathVariable Integer end){
        Map<String,Integer>  myList = new HashMap<>();
        myList.put("荣耀10",2888);
        myList.put("HUAWEI P20",5000);
        myList.put("IPhone 8 ",3888);
        return myList;
    }
}
