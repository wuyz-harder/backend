package com.example.myblog.controller;

import com.example.myblog.annotation.NotNeedLogin;
import com.example.myblog.dto.RespBean;
import com.example.myblog.dto.User;
import com.example.myblog.mapper.UserMapper;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
@RestController
public class userController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @NotNeedLogin
    @ResponseBody
    @GetMapping("/login")
    public Object login(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Map<String,String> map = new HashMap<>();
        if(name.equals("")){
            response.setStatus(500);
            map.put("msg","用户名为空");
            return map;
        }
        response.setStatus(200);
        if(userService.getUser(name)==null){
            response.setStatus(403);
            map.put("msg","没有此用户");
            return map;
        }else{
            User user = (User) userService.getUser(name);
            if(user.getPassword().equals(password)){
                map.put("token",userService.getToken(user));
            }else {
                response.setStatus(500);
                map.put("msg","密码错误");
            }
            return map;
        }
    };
    @NotNeedLogin
    @PostMapping(value = "/register")
    public Object register(@RequestBody User user){
        userMapper.insertUser(user);
        System.out.println(user.getName());
        RespBean respBean = new RespBean();
        respBean.setStatus(200);
        respBean.setMsg("注册成功");
        return  respBean;
    }
}
