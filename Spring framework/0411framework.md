1. Framework란?

- 비기능적인 요구사항들(인증,로깅,Tx처리,성능) 구현해서 제공함
- 업무 개발자는 기능적인 요구사항(biz logic) 개발에 전념할 수 있게 해준다.
- 프레임워크의 구성요소
  - IoC(Inversion of Control)제어의 역전
  - Design Pattern(GoF(gang of Four) Pattern) - 클래스 구조에 대한Guide

2. Framework와 Library의 차이점

- 제어권 - 객체생성 ,소멸(Life cycle) 특정 메소드 호출
- Library는 제어권을 개발자가 가진다.
- Framework는 프레임워크가 제공하는 컨테이너가 가진다.

3. Open Source

- Spring(Context, test), Maven(pom.xml), Junit, Tomcat(web container)
- Web Sever와 Web Container 다르다
  - Apache, Nginx, GWS(web server)
  - 웹서버에서 JSP,Servlet으로 작성 webapp을 실행할수 있을까? 실행할수 없음
  - jre(java runtime enviroment가 있어야함)
  - J2EE API에 있는 interface들은 누가 구현할까? WAS vendor
  - tamcat은 jsp와 servlet에 관련된 web과 관련된 인터페이스의 구현체를 제공한다.
  - WAS = web container + ejb container
    - tomcat, weblogic, web sphere, JEUS
  - WAS를 제공한는 vendor를 J2EE에 들어있는 모든 인터페이스들의 구현체를 개발해서 제공한다.
  - J2EE표준(인터페이스)과 J2EE구현을 왜 분리해서 제공할까?
  - JDBC(java.sql, javax.sql)에 있는 인터페이스는 누가 구현할까? DB vendor
- Web server와 was의 차이점
  - web server - static contents
  - was - dynamic contents



4. DI(Dependency Injection)

- 의존성 주입
- 의존하는 객체의 레퍼런스를 프레임워크가 주입해 주겠다.
- 개발자는 의존하는 클래스의 정보를 xml이나 javaconfig에 설정을 해야한다.
- DI종류 주입하는 방식
  - setter injection, constructor injection
    - setter injection - 기본생성자로 객체를 생성하고, setter method의 인자로 의존하는 객체를 1개씩 주입해주는 방식
    - constructor injection - overloading된 생성자로 객체를 생성하고, 이 생성자의 인자로 의존하는 객체를 여러개씩 주입해주는 방식
- 프레임워크가 대신 객체를 생성하면 특정 기능(singleton, tx처리)을 주입시켜준다.(byte code injection)

>용어정의
>
>Bean - 스프링이 IoC방식으로 관리하는 객체
>
>BeanFactory , ApplicationContext, - Spring Bean Container
>
>Configuration MetaData - Config xml, config 클래스

- DI구현하는 전략 3가지

  1. 전략 1 
   - configuration(설정)을 xml에 한다.
  
```xml
  <!-- setter injection -->

  <bean id="bean고유한이름" class="package.class이름" />

  <bean id="hello" class="xx.Hello">

  	<!-- setValue(integer val) -->

  	<property name = "value" value="100" />

  	<!-- setMyPrinter(Printer p) -->

  	<property name="myPrinter" ref="strPrinter" />

  <bean/>

  <bean id="strPrinter" class="xxx.StringPrinter"/> 객체생성후 setter 두번 호출

  

  <!-- Constructor injection -->

  <bean id ="helloC" class="xxx.Hello">

  	<constructor-arg name="val" value="100"/>

  	<constructor-arg name="name" value="스프링"/>
  
  	<constructor-arg name="pr" ref="strPrinter"/>
  
  <bean/>
  ```
  
  - 전략1의 장단점
  
  장점 : 전체 의존관계 구조를 파악하기 쉽다.
  
  단점:xml파일 Sharing(공유)의 문제점    

  2. 전략 2
  
  - annotion과 xml를 혼용해서 사용
  
    - @Component, @Controller, @Service, @Repository
  
      :<bean>태그와 동일, bean으로 등록하겠다.
  
    -  @Autowired(@Qualifier), @Resource, @Value,  
  
      :의존관계가 있는 bean을 찾아서 자동으로 주입시켜주는 기능
  
      :Autowired는 Type으로 찾음, Resource는 Name(id)으로 찾음
  
      :Autowired는 변수, 메서드, 생성자 위에 선언 가능
  
      :Resource는 변수, 메서드 위에 선언 가능
  
    <context:component-scan base-package=""/>
  
    :basepackage에서 지정한 패키지의 아래의 모든 클래스에 선언된 @Component...등을 찾아주는(Auto scannig)기능
  
  전략2의 장단점
  
  장점: xml설정이 좀 더 간단해져서 관리가 용이, 개발모드에서 편리
  
  단점: bean들간의 전체적인 의존관계를 파악하기 어렵다.
  
  
  
  3. 전략 3 (ver3.0) Spring Boot - Java Config, xml 사용하지 않는다.

     @Configuration - JavaConfig 클래스이다.

     @Bean - <bean>태그와 동일한 역할

     @ComponentScan -<context:component-scan base-package=""/> 태그와 동일한 역할



---

Junit, Spring Test - Test case 작성 support

@Test, @Before, @After, @Ignore

:Test method

@Runwith @ContextConfiguration

:@Runwith Junit 확장해서 테스트 케이스를 실행해주는 Runner를 지정할때

- Junit사용

    BeanFactory factory = 
          new GenericXmlApplicationContext("config/beas.xml");
    Hello hello = factory.getBean("hello",Hello.class);



- Spring Test 사용

    @Runwith(SpringJunit4ClassRunner.class) 
    @ContextConfiguraion(locations="classpath:config/beans.xml")
    @Autowired

---



start C:\Java\sql_oracle\hr.sql

start C:\Java\sql_oracle\ scott.sql

start C:\Java\sql_oracle\ user.sql

select from users;

start C:\Java\sql_oracle\ student.sql

start C:\Java\sql_oracle\person.sql

select * from tab;



oracle jdbc driver는 maven에서 내려받지 않고 직접 jar을 다운로드 받아서 클래스 패스를 설정한다.

jar를 javaproject에 옮기고 빌드패스에서 클래스패스에 복사한jar를 추가 









