package com.zebra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
 public class SmpApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmpApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ 启动成功   ლ(´ڡ`ლ)ﾞ  \n" + " .-------.       ____     __        \n"
				+ " |  _ _   \\      \\   \\   /  /    \n" + " | ( ' )  |       \\  _. /  '       \n"
				+ " |(_ o _) /        _( )_ .'         \n" + " | (_,_).' __  ___(_ o _)'          \n"
				+ " |  |\\ \\  |  ||   |(_,_)'         \n" + " |  | \\ `'   /|   `-'  /           \n"
				+ " |  |  \\    /  \\      /           \n" + " ''-'   `'-'    `-..-'              ");
	}
}