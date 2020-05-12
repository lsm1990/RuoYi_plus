package com.ruoyi.spider.test;

import com.ruoyi.common.utils.StringUtils;

public class TestTest {
    public static void main(String[] args) {
        String s="background:url(/uploads/201811/28/181128072509176.jpg) no-repeat center; background-size:cover;";
s=StringUtils.trim_end_exclu(s,"(");
s=StringUtils.trim_before_exclu(s,")");
        System.out.println(s);
    }
}
