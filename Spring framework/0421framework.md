Jackson : json parser
 java object -> json
 json -> java object


ORM(Object Relational Mapping)  : MyBatis, JPA
OXM(Object XML Mapping) : JAXB, Jackson xml  

JAXB(Java API for XML Binding) : jdk1.8에는 포함되어 있었지만, jdk10부터는 포함되지 않음
 java object -> xml 
 xml -> java object

<users>
   <status>success</status>
   <user>
       <userId>gildong</userId>
       <name>홍길동</name>
       <gender>남</gender>
       <city>서울</city>
   </user>
   <user>
   </user>
</users>


FrontEnd Framework/Library
: jQuery
: React, Vuejs, Angular
: server side 연동할 때 ajax 

기존 서버코드는 jsp를 사용해서 구현 
클라이언트 js 를 vuejs나 react 를 사용하고 싶어요 가능할까요?
: Server 는 클라이언트에게 data( json, xml)만 전달해야 한다. 

third-party library/framework
: tomcat - web container
: log4j - logging, log level
: mybatis - ORM , db연동
: jackson - json parser
: junit - 단위테스트 지원
: dbcp(database connection pooling) - 컨넥션 풀링

java 
: jdbc, Servlet/JSP,  jaxb

Spring boot Project 작성시 유의사항
1. src/main/java 아래에 있는 base package 와 다른 별도의 package 를 작성하면 않된다.
이유 : base package 가 componentScan을 시작하는 package 이므로
2. src/test/java 하위에 테스트 클래스를 만들어야 한다. 
이유 : boot test Dependency 설정에서 scope이 test로 범위가 정해져 있기 때문
   <scope>test</scope> 
3. src/main/resources 아래에 application.properties 파일이 위치한다.
 1) static 폴더 : html, css , js
 2) templates 폴더 : jsp, html(thymeleaf)
 3) resouces 하위에 sub 폴더를 생성 한 후에는 반드시 Config 클래스에 설정해주어야 함

base package 아래 Application 클래스 
@SpringBootApplication
: @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan   
: boot-autoconfigure.jar -> Meta-INF/spring.factories 파일안에 Configuration 클래스 목록
: Spring Bean Container 생성