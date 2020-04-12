package myspring.di.xml.test;

//static import : static 메소드 import
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import myspring.di.xml.Hello;
import myspring.di.xml.Printer;

public class HelloBeanJunitTest {
	BeanFactory factory;
	@Before
	public void init() {
		//1.Spring Bean Container 생성
		factory = new GenericXmlApplicationContext("config/spring_beans.xml");
		
	}
	/*
	 * TestCase 메서드 선언할 때 규칙
	 * 1. @Test 어노테이션을 반드시 선언한다.
	 * 2.테스트 메서드의 접근 제한자는 반드시 public void이여야 한다.
	 */
	@Test
	public void hello() {
		//ResourceLocation - Spring Bean config xml 파일
		//2.container에게 bean요청
		Hello hello = (Hello)factory.getBean("hello");
		Hello hello2 = factory.getBean("hello",Hello.class);
		System.out.println(hello == hello2);
		//2.1 Assert.assertSame 메서드 사용해서 주소 비교
		assertSame(hello, hello2);
		//2.2 Assert.assertEquals() 메소드 사용해서 값을 비교
		assertEquals("Hello 스프링", hello.sayHello());
		
		hello.print();
		//3. container에게 stringPrinter Bean을 요청
		Printer printer = factory.getBean("sPrinter",Printer.class);
		assertEquals("Hello 스프링", printer.toString());
	}

}
