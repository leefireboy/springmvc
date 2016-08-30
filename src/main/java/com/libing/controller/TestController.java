package com.libing.controller;

import com.libing.entity.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * @InitBinder 绑定的数据,只在此 Controller 中有用
     * 其中 @InitBinder("user") 中的 "user" 对应参数为 "user" 的参数传递
     */
    @InitBinder("user")
    public void initUser(WebDataBinder webDataBinder) {
        webDataBinder.setFieldDefaultPrefix("user.");
    }

    /**
     * @InitBinder 绑定的数据,只在此 Controller 中有用
     * 其中 @InitBinder("admin") 中的 "admin" 对应参数为 "admin" 的参数传递
     */

    @InitBinder("admin")
    public void initAdmin(WebDataBinder webDataBinder) {
        webDataBinder.setFieldDefaultPrefix("admin.");
    }

    /**************************************List 绑定**************************************/

    /*http://localhost:8080/springmvc/list?users[0].name=Tom&users[1].name=Lucy
    list 绑定要注意连续性，如果不连续，例如：users[0].name=Tom&users[4].name=Lucy
    这种情况下就会导致产生5个对象，中间三个为空对象*/
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(UserListForm userListForm) {
        return "listSize:" + userListForm.getUsers().size() + " " + userListForm.toString();
    }

    /**************************************Set 绑定**************************************/

    /*http://localhost:8080/springmvc/set?users[0].name=Tom&users[1].name=Tom
    使用 set 进行数据绑定时要对其进行初始化，否则会报获取不到对应下标数据的异常
    由于 springmvc 对 set 数据绑定支持的不是很友好，建议实际应用中采用 list 的方式*/
    @RequestMapping(value = "/set")
    @ResponseBody
    public String set(UserSetForm userSetForm) {
        return "setSize:" + userSetForm.getUsers().size() + " " + userSetForm.toString();
    }

    /**************************************Map 绑定**************************************/

    /*http://localhost:8080/springmvc/map?users['X'].name=Tom&users['X'].age=10&users['Y'].name=Lucy
    */
    @RequestMapping(value = "/map")
    @ResponseBody
    public String map(UserMapForm userMapForm) {
        return "mapSize:" + userMapForm.getUsers().size() + " " + userMapForm.toString();
    }

    /**************************************Json 绑定**************************************/

    /*http://localhost:8080/springmvc/json
    {"name":"Tom","age":16,"contactInfo":{"address":"beijing","phone":"10010"}}*/
    @RequestMapping(value = "/json",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String json(@RequestBody User user) {
        return user.toString();
    }

    /**************************************Xml 绑定**************************************/

    /*http://localhost:8080/springmvc/xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <admin>
        <name>Jim</name>
        <age>19</age>
    </admin>
    */
    @RequestMapping(value = "/xml",
            method = RequestMethod.POST,
            produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public String xml(@RequestBody Admin admin) {
        return admin.toString();
    }

    /**************************************Date 绑定**************************************/
    /*http://localhost:8080/springmvc/date1?date1=2015-08-08
    如果采用了全局的日期类型字符串格式化，那么 @InitBinder("***") 可以省略掉*/
    @RequestMapping(value = "/date1",
            method = RequestMethod.GET)
    @ResponseBody
    public String date1(Date date1) {
        return date1.toString();
    }

    /**
     * @InitBinder 绑定的数据,只在此 Controller 中有用
     * 其中 @InitBinder("date1") 中的 "date1" 对应参数为 "date1" 的参数传递
     */
    /*@InitBinder("date1")
    public void initDate1(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }*/

    /**************************************DateFormatter 转换参数**************************************/

    /*http://localhost:8080/springmvc/date2?date2=2015-08-08
    如果采用了全局的日期类型字符串格式化，那么 @InitBinder("***") 可以省略掉*/
    @RequestMapping(value = "/date2",
            method = RequestMethod.GET)
    @ResponseBody
    public String date2(Date date2) {
        return date2.toString();
    }

}
