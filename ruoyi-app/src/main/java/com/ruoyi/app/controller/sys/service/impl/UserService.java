package com.ruoyi.app.controller.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.app.controller.sys.entity.AppUser;
import com.ruoyi.app.controller.sys.mapper.UserMapper;

@Service("userService")
public class UserService
{
    @Autowired
    UserMapper userMapper;

    public AppUser findByUsername(String username)
    {
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        // return userMapper.selectOne(user);
        // 这里演示就直接返回了
        appUser.setPassword("123456");
        appUser.setId(1l);
        return appUser;
    }

    public AppUser findUserById(long userId)
    {
        //return userMapper.selectByPrimaryKey(userId);
        AppUser appUser = new AppUser();
        appUser.setUsername("test");
        // return userMapper.selectOne(user);
        // 这里演示就直接返回了
        appUser.setPassword("123456");
        appUser.setId(userId);
        return appUser;
    }
}