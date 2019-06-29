package com.example.baseball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BaseballApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(BaseballApplication.class);
    }

    //このmainメソッドがアプリのエントリーポイントになり、Springアプリを起動
	public static void main(String[] args) {
		SpringApplication.run(BaseballApplication.class, args);
	}

}
