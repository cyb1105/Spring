package com.cyb.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cyb.myspringboot.listener.MyStartingEventListener;

@SpringBootApplication
public class MyspringbootApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MyspringbootApplication.class, args);
		//webApplication Type 변경
		SpringApplication application = new SpringApplication(MyspringbootApplication.class);
		//Default webapplication Type의 SERVLET
		application.setWebApplicationType(WebApplicationType.SERVLET);
		//application.setWebApplicationType(WebApplicationType.NONE);
		
		//Listener등록
		application.addListeners(new MyStartingEventListener());
		application.run(args);
	}

}
