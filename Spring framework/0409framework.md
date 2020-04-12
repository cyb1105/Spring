- 용어

DI(Dependency Injection)

TestContext Framework

DAO(Data Access Object)

JDBC(java database connectivity)

ORM(Object Relational Mapping)

MyBatis, JPA(java persistence architecture)

Spring MVC(Model view controller)

loC(Inversion of Control)

AOP(Aspect Oriented Programming)

JSP(java server page)

json.xml

Ajax

React 

JPA

REST

OXM

EJB

WAS

JEUS, web sphere, web logic



라이브러리 - 객체 생성을 개발자가 한다 - 주도권 개발자

프레임워크 - 객체생성을 프레임워크가 제공하는 컨테이너가 한다. 프레임워크를 사용할때 개발자는 xml에 설정을 반드시 해야한다 - 

IoC - 주도권 역전



di는 setter와 constructor로 나눈다



bean - 스프링이 관리해주는 객체

xml 설정 -프레임워크와 개발자의 소통수단 클래스가 어디에있는지 알려주기 위해

코드 포멧 단축기 = ctrl + shift + f  

ctrl shift o = auto import

ctrl shift c = xml 주석

xsd - 스키마



- 환경설정 순서

1. dynamic web project 생성 
2. 1번생성할 때 web.xml설정 체크
3. configure -> convert to maven project pom.xml생성 완료
4. <dependencies>태그 추가
5. dependency추가
6. 생성한 프로젝트에 maven dependencies 생겼는지 확인
7. 생성이 안되면 오른쪽에 메뉴에서 update maven project
8. config폴더 생성하고 config아래에 spring_beans.xml 생성



BeanFactory는 spring bean컨테이너 역할을 한다

beanfactory의 getbean() 메서드 요청을 한다.

ApplicationContext도 springbean 컨테이너 역할을 한다

config/spring_bean.xml



spring bean container 







