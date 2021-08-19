package com.example.myblog.mapper;

import com.example.myblog.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface UserMapper {
    public User getUserByName(String name);
    public void insertUser(User user);
}
