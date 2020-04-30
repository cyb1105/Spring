package com.cyb.myspringboot.listener;

import java.util.Date;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyStartedEventListener implements ApplicationListener<ApplicationStartedEvent>{

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		
		System.out.println("spring Bean 컨테이너가 생성된 후에 호출됨" + new Date(event.getTimestamp()));
		
	}
	
}
