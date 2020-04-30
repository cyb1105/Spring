package com.cyb.myspringboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cyb.myspringboot.property.CybProperties;

@Component
@Order(1)
//Runner클래스 중에서 우선 순위가 가장 옾다
//java -jar -Dfoo myspringboot-0.0.1-SNAPSHOT.jar --bar
//java -jar myspringboot-0.0.1-SNAPSHOT.jar --cyb.name=자바
//java -jar myspringboot-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
public class MyRunner implements ApplicationRunner{
	@Value("${cyb.name}")
	private String name;
	
	@Value("${cyb.age}")
	private int age;
	
	@Autowired
	CybProperties properties;
	
	@Autowired
	String hello;
	
	private Logger logger = LoggerFactory.getLogger(MyRunner.class);
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		logger.debug(">>Hello Bean"+hello);
		logger.debug(">>Property Name"+ name);
		logger.debug(">>Property age"+ age);
		logger.debug(">>PropertyClass name"+properties.getName());
		logger.debug(">>PropertyClass fullname"+properties.getFullName());
		
		
		logger.info("SourceArgs " + args.getOptionNames());
		logger.info("Program Argument:" + args.containsOption("bar"));
		logger.info("VM Argument:"+args.containsOption("foo"));
		
	}
	

}
