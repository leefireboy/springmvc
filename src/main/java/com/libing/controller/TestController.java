package com.libing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by libing on 2016/8/25.
 */
@Controller
public class TestController {

    /*http://localhost:8080/springmvc/baseType?age=10
    因为是基本类型，所以参数必须传，否则会报错*/
    @RequestMapping(value = "/baseType")
    @ResponseBody
    public String baseType(int age) {
        return "age: " + age;
    }

    @RequestMapping(value = "/baseType2")
    @ResponseBody
    public String baseType2(Integer age) {
        return "age: " + age;
    }

}
