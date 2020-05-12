package com.ruoyi.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 程序启动后通过ApplicationRunner处理一些事务
 *
 * @author wujiyue
 * @date 2018/6/6 16:07
 */

@Component
public class MyApplicationRunner implements ApplicationRunner {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments applicationArguments) {

            System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                    " .-------.       ____     __        \n" +
                    " |  _ _   \\      \\   \\   /  /    \n" +
                    " | ( ' )  |       \\  _. /  '       \n" +
                    " |(_ o _) /        _( )_ .'         \n" +
                    " | (_,_).' __  ___(_ o _)'          \n" +
                    " |  |\\ \\  |  ||   |(_,_)'         \n" +
                    " |  | \\ `'   /|   `-'  /           \n" +
                    " |  |  \\    /  \\      /           \n" +
                    " ''-'   `'-'    `-..-'              ");

    }
}
