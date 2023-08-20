package com.group13.nsrs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Oreki
 */
@SpringBootApplication
@MapperScan("com.group13.nsrs.mapper")
public class NsrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NsrsApplication.class, args);
	}

}
