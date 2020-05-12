package com.ruoyi.framework.shiro.web.filter;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.interceptor.path.impl.AntPathMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * IP黑名单过滤器
 */
public class IPFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(IPFilter.class);
    /**
     * 排除链接
     */
    public List<String> excludes = new ArrayList<>();

    private boolean enabled=false;

    private static Cache<String,Integer> cache= CacheUtil.newLRUCache(1000,1000*10);

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public List<String> ipList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String tempExcludes = filterConfig.getInitParameter("excludes");
        String enabledIp = filterConfig.getInitParameter("enabled");
        String list = filterConfig.getInitParameter("list");

        if (StringUtils.isNotEmpty(tempExcludes))
        {
            String[] url = tempExcludes.split(",");
            for (int i = 0; url != null && i < url.length; i++)
            {
                excludes.add(url[i]);
            }
        }

        if (StringUtils.isNotEmpty(list))
        {
            String[] arr = list.split(",");
            for (int i = 0; arr != null && i < arr.length; i++)
            {
                ipList.add(arr[i]);
            }
        }
        if (StringUtils.isNotEmpty(enabledIp))
        {
            enabled=Boolean.valueOf(enabledIp);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if(!enabled){
            filterChain.doFilter(req, resp);
            return;
        }
        String ip = IpUtils.getIpAddr(req);
        String uri=req.getRequestURI();
        if(ipList.contains(ip)){
            System.out.println("成功拦截====>ip:"+ip+"====uri:"+uri);
            return;
        }
        if (urlMatch(uri, excludes)||isStaticResources(uri))
        {
            filterChain.doFilter(req, resp);
            return;
        }


        String key=ip+"="+uri;
        //System.out.println("=====>"+key+"=======>");
        Integer count=cache.get(key);
        if(count==null){
            count=0;
        }
        count++;
        cache.put(key,count);
        System.out.println("=====>"+key+"=======>"+count);
        if(count>=7){//10秒之内请求7次
            ipList.add(ip);
            logger.info("被放入黑名单====>ip:"+ip+"====uri:"+uri);
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

    private boolean urlMatch(String uri,List<String> paths){
        for (String path:paths) {
            String uriPattern = path.trim();
            // 支持ANT表达式
            if (antPathMatcher.match(uriPattern, uri)) {
                return true;
            }
        }
        return false;
    }
    //静态资源
    public static boolean isStaticResources(String uri){
        if(uri.endsWith(".ico")||uri.endsWith(".js")||uri.endsWith(".css")||uri.endsWith(".jpg")||uri.endsWith(".jpeg")||uri.endsWith(".png")||uri.endsWith(".bmp")||uri.endsWith(".gif")||uri.endsWith(".eot")||uri.endsWith(".svg")||uri.endsWith(".ttf")||uri.endsWith(".woff")){
            return true;
        }else{
            return false;
        }
    }

}
