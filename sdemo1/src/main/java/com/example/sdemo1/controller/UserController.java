package com.example.sdemo1.controller;
import com.example.sdemo1.pojo.Result;
import com.example.sdemo1.pojo.User;
import com.example.sdemo1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.UUID;

@Controller
@ResponseBody
@RestController
public class UserController {
    @Resource
    private UserService userService;

    //
    @RequestMapping("/user/sign")
    public Result<?> add(User user)
    {
        System.out.println("aaaa"+user.phone);
       userService.addU(user);
       System.out.println(user.name);
        return Result.success("添加成功！");
    }
    @RequestMapping("/user/delete")
    public Result<?> delete(User user)
    {
        userService.deleteU(user);
        return Result.success("删除成功！");
    }
    @RequestMapping("/user/update")
    public Result<?> update(User user)
    {
        userService.updateU(user);
        return Result.success("更新成功！");
    }
    @RequestMapping("/user/login")
    public Result<?> find1(User user)
    {
        Result<String> result=new Result<>();
        User user1=userService.findByPhone(user.phone);
       if(user1==null)
            result.error(2,"该用户不存在！");
       else if(!user1.password.equals(user.password))
           result.error(3,"密码错误！");
       else {
           result.success("登录成功！", "登陆成功！");

       } return Result.success(result);
    }
    @RequestMapping("/user/findByName")//根据姓名查找这个人的所有信息
    public Result<?> find2(String name)
    {
        User user=userService.findByName(name);
        return Result.success(user);
    }
    @RequestMapping("/user/findByPhone")//根据姓名查找这个人的所有信息
    public Result<?> find3(String phone)
    {
        User user=userService.findByPhone(phone);
        return Result.success(user);
    }

}
