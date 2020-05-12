package com.ruoyi.app.controller.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.app.common.annotation.PassToken;
import com.ruoyi.app.common.base.BaseAppController;
import com.ruoyi.app.common.exception.UnauthorizedException;
import com.ruoyi.app.common.page.ResultData;
import com.ruoyi.app.common.util.JwtUtil;
import com.ruoyi.app.controller.sys.entity.AppUser;
import com.ruoyi.app.controller.sys.service.impl.UserService;

@RestController
public class LoginController extends BaseAppController
{
    @Autowired
    UserService userService;

    @PassToken
    @PostMapping("/login")
    public ResultData login(@RequestBody AppUser loginUser)
    {
        AppUser appUser = userService.findByUsername(loginUser.getUsername());
        // 这里是演示，正式使用要加密
        if (appUser.getPassword().equals(loginUser.getPassword()))
        {
            return ResultData.success(JwtUtil.sign(loginUser.getUsername(), loginUser.getPassword()));
        }
        else
        {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/getUserId")
    public ResultData getUserId()
    {

        return ResultData.success(getCurrentUserId());
    }
}