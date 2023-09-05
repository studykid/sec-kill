package com.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wy
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mall.mapper")
public class MallApplication {


	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}

}
