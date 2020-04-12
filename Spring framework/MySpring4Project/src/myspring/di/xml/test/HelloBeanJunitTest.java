package myspring.di.xml.test;

//static import : static �޼ҵ� import
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
		//1.Spring Bean Container ����
		factory = new GenericXmlApplicationContext("config/spring_beans.xml");
		
	}
	/*
	 * TestCase �޼��� ������ �� ��Ģ
	 * 1. @Test ������̼��� �ݵ�� �����Ѵ�.
	 * 2.�׽�Ʈ �޼����� ���� �����ڴ� �ݵ�� public void�̿��� �Ѵ�.
	 */
	@Test
	public void hello() {
		//ResourceLocation - Spring Bean config xml ����
		//2.container���� bean��û
		Hello hello = (Hello)factory.getBean("hello");
		Hello hello2 = factory.getBean("hello",Hello.class);
		System.out.println(hello == hello2);
		//2.1 Assert.assertSame �޼��� ����ؼ� �ּ� ��
		assertSame(hello, hello2);
		//2.2 Assert.assertEquals() �޼ҵ� ����ؼ� ���� ��
		assertEquals("Hello ������", hello.sayHello());
		
		hello.print();
		//3. container���� stringPrinter Bean�� ��û
		Printer printer = factory.getBean("sPrinter",Printer.class);
		assertEquals("Hello ������", printer.toString());
	}

}
