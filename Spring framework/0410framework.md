1. Framework란?
   - 비기능적인 기능을 제공할테니, 개발자는 biz로직에 집중해라
2. Framework와 Library의 차이점
   - 제어권을 누가 주도하느냐?
   - Library는 개발자가 제어권을 가짐
   - Framework는 프레임워크가 제어권을 가짐
     - 개발자가 작성한 클래스를 프레임워크가 컨테이너가 객체를 생성하고 setter method를 호출한다
3. IoC(Inversion of Control) : 제어권의 역전
   - IoC컨테이너
   - IoC구현 : DL DI (의존성 주입)
4. DI의 종류
   - Setter Injection, Constructor injection, Method injection
5. Junit
   - 자바기반 단위테스트를 지원하는 프레임워크
   - @Test, @Before
6. Spring Test Framework를 사용

----

Constructor injection

Spring Test Framework

@Autowired, @Component



alt + shift + L : 왼쪽에 타입 자동생성



@Component -> <bean>

@Value -> <property value =""/>

@Autowired -> <property ref=""/>

---

@Autowired 

: 타입(type)으로 해당외는 Bean을 찾아서 주입해주는 어노테이션

@Qualifier는 동일한 타입이 여러개가 있을때 특정 Bean을 지정하는 어노테이션(Autowired와 같이 쓰인다)

:Bean의 id를 properties에서 가져와서 사용하는 것이 지원되지 않는다.

${myprinter}

myprinter=stringPrinter



@Resource

:Bean의 이름(id)으로 해당되는 Bean을 찾아서 주입하는 어노테이션

:Bean의 id를 properties에서 가져와서 사용하는 것이 지원된다.

----

DI 구현하는 방식

1.XML Based

:bean등록<bean id="" class=""/>

:setter injection

​	<property value=""/>

​	<property ref=""/>

:constructor injection

​	<constructor-arg value=""/>

​	<constructor-arg ref =""/>



2.Annotation Based(component-scan설정을 위해 xml 부분적으로 사용)

:bean 등록 @Component

:setter injection - 어노테이션을 변수,  setter메서드 위에 선언

​	@Value

​	@Autowired, @Qualifier

:constructor injection - 어노테이션을 변수 옆에, 생성자 위에 선언

​	@Value, @Qualifier는 생성자 변수 옆에

​	@Autowired는 생성자의 위에 



3.Annotation Based(xml을 전혀 사용하지 않음)

:Java로 Configuration클래스를 작성한다.

@Configuration

:@Bean : 어노테이션으로 선언하지 않은 클래스를 Bean으로 등록

:@ComponentScan : @Component어노테이션으로 선언된 Bean을 찾을때



----

현재까지 사용된 어노테이션

@Test : test method 

@before : test method 전에 호출

@Runwith : Test Runner를 확장할때



@ContextConfiguration : Spring Bean xml 파일의 정보를 설정할 때



@Component, @Repository, @Service, @Controller

:Spring Bean 등록(생성)

@Value

:Spring Bean의 의존성 주입, 값을 주입

@Autowired/ @Resource(javax.)

:Spring Bean의 의존성주입,  Container가 의존하는 Bean을 찾아서 주입해준다. reference를 주입

@Qualifier

:@Autowired와 같이 사용되며, 특정 Bean을 지정한다.



Spring Bean Configuration xml 를 사용하는 경우 
: GenericXmlApplicationContext - Spring Bean 컨테이너
Spring Bean Configuration 클래스(no xml) 를 사용하는  경우
: AnnotationConfigApplicationContext - Spring Bean 컨테이너