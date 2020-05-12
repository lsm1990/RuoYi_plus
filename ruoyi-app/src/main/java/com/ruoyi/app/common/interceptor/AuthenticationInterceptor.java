package com.ruoyi.app.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruoyi.app.common.annotation.PassToken;
import com.ruoyi.app.common.exception.UnauthorizedException;
import com.ruoyi.app.common.util.JwtUtil;
import com.ruoyi.app.controller.sys.entity.AppUser;
import com.ruoyi.app.controller.sys.service.impl.UserService;

public class AuthenticationInterceptor implements HandlerInterceptor
{
    public static final String USER_KEY = "userId";

    @Autowired
    UserService                userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object object) throws Exception
    {
        String token = request.getHeader("token");// 从 http 请求头中取出
                                                             // token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod))
        {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class))
        {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required())
            {
                return true;
            }
        }
        // 执行认证
        if (token == null)
        {
            throw new RuntimeException("无token，请重新登录");
        }
        String username = JwtUtil.getUsername(token);
        if (username == null)
        {
            throw new RuntimeException("token invalid");
        }
        AppUser appUser = userService.findByUsername(username);
        if (appUser == null)
        {
            throw new RuntimeException("用户不存在，请重新登录");
        }
        // 验证 token
        if (!JwtUtil.verify(token, appUser.getUsername(), appUser.getPassword()))
        {
            throw new UnauthorizedException();
        }
        // 设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, appUser.getId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception
    {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, Exception e) throws Exception
    {
    }
}