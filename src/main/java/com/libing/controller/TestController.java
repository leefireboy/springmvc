package com.libing.controller;

import com.libing.entity.Admin;
import com.libing.entity.User;
import com.libing.entity.UserListForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by libing on 2016/8/25.
 */
@Controller
public class TestController {

    /**************************************基本类型**************************************/

    /*http://localhost:8080/springmvc/baseType?xage=10
    因为是基本类型，所以参数必须传，否则会报错*/
    @RequestMapping(value = "/baseType")
    @ResponseBody
    public String baseType(int xage) {
        return "xage: " + xage;
    }

    /**************************************基本类型包装类**************************************/

    /*http://localhost:8080/springmvc/baseType?age=10
    因为是基本类型，所以参数必须传，否则会报错*/
    @RequestMapping(value = "/baseType2")
    @ResponseBody
    public String baseType2(Integer age) {
        return "age: " + age;
    }

    /**************************************数组**************************************/

    /*http://localhost:8080/springmvc/array?name=Tom&name=Lucy&name=Jim*/
    @RequestMapping(value = "/array")
    @ResponseBody
    public String array(String[] name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : name) {
            stringBuilder.append(item).append(" ");
        }
        return stringBuilder.toString();
    }

    /**************************************单个对象**************************************/

    /*http://localhost:8080/springmvc/object?name=Tom&age=10
    http://localhost:8080/springmvc/object?name=Tom&age=10&contactInfo.phone=10086
    springmvc 会自动的把传递的参数绑定到对象的属性中
    如果接受对象包含下级对象，绑定参数只要采用 contactInfo.phone=10086 的方式即可*/
    @RequestMapping(value = "/object")
    @ResponseBody
    public String object(User user) {
        return user.toString();
    }

    /**************************************同属性的多对象**************************************/

    /*http://localhost:8080/springmvc/twoObject?name=Tom&age=10
    这样请求会同时给 User 和 Admin 两个对象里的 name 和 age 字段赋相同的值
    http://localhost:8080/springmvc/twoObject?user.name=Tom&admin.name=Lucy&age=10
    在使用了 @InitBinder 参数绑定之后就可以进行对应对象的参数绑定
    如果不使用 @InitBinder 参数绑定，传递 user.name=Tom&admin.name=Lucy 参数时后台都接收不到*/
    @RequestMapping(value = "/twoObject")
    @ResponseBody
    public String twoObject(User user, Admin admin) {
        return user.toString() + " " + admin.toString();
    }

    @InitBinder("user")
    public void initUser(WebDataBinder webDataBinder) {
        webDataBinder.setFieldDefaultPrefix("user.");
    }

    @InitBinder("admin")
    public void initAdmin(WebDataBinder webDataBinder) {
        webDataBinder.setFieldDefaultPrefix("admin.");
    }

    /**************************************同属性的多对象**************************************/

    /*http://localhost:8080/springmvc/list?users[0].name=Tom&users[1].name=Lucy
    list 绑定要注意连续性，如果不连续，例如：users[0].name=Tom&users[4].name=Lucy
    这种情况下就会导致产生5个对象，中间三个为空对象*/
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(UserListForm userListForm) {
        return userListForm.toString();
    }

}
