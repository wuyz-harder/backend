package com.example.myblog.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.myblog.dto.User;
import com.example.myblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public Object getUser(String name){
        if (userMapper.getUserByName(name)==null){
            return null;
        }else{
            return userMapper.getUserByName(name);
        }
    }
    public String getToken(User user){
        String token = "";
        token = JWT.create().withAudience(user.getName())
                .sign(Algorithm.HMAC256(user.getName()));
        return token;
    }
}
