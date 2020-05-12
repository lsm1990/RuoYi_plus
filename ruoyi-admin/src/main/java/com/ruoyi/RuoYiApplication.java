package com.ruoyi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public final static Logger log = LoggerFactory.getLogger(RuoYiApplication.class);
    public static void main(String[] args) throws UnknownHostException
    {
        ConfigurableApplicationContext application = SpringApplication.run(RuoYiApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");

        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "\n\t" +
                "----------------------------------------------------------");
    }
}