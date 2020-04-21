4월20일(월)
Spring MVC 
: jsp 연동, 
: json, xml 데이터 포맷을 사용해서 REST 방식으로 웹서비스 작성하는 방법

Spring MVC
1. web.xml (web container 설정) 
: ContextLoaderListener 설정
  - Spring Beans Configuration 설정 파일의 위치
: FrontController 역할을 하는 DispatcherServlet 클래스 정보 설정
 <url-pattern>*.do</url-pattern>

2. Controller 클래스 작성
@Controller 
<context:component-scan base-package="myspring.user" />
@RequestMapping("/getUser.do")
: 요청과 Controller내에 선언된 method를 매핑 
@RequestParam
: request.getParameter("userid")

@ModelAttribute 
: form data의 값을 추출해서 VO객체에 자동으로 저장해주는 어노테이션
: <input type="text" name="userId" >
:  VO의 setter method가 일치해야 함

Encoding 
요청(request) 데이터 인코딩
 : request.setCharacterEncoding("utf-8");
 : Servlet Filter(공통적으로 사용되는 기능을 포함한 객체) 사용
    Spring 에서 CharacterEncodingFilter 클래스를 제공한다.
    web.xml에 Filter 설정
    <filter>
       <filter-name>CharacterEncodingFilter</filter-name>
       <filter-class>org.springframework.web.filterCharacterEncodingFilter</filter-class>
    </filter>
   <filter-mapping>
       <filter-name>CharacterEncodingFilter</filter-name>
       <url-pattern>*.do</url-pattern>
   </filter-mapping>

응답(response) 데이터 인코딩
: response.setContentType("text/html;charset=utf-8")
<%@ page contentType="text/html;charset=utf-8" %>

@PathVariable
 : userDetail.do?userid=gildong  @RequestParam 
 : userDatail.do/gildong  @PathVariable

web.xml의 DispatcherServlet의 url-pattern 변경
 *. do -> /  
: tomcat이 내부적으로 호출하는 DefaultServlet의 url-pattern 도 /
: spring 이 제공하는 DispatcherServlet의  url-pattern 도 /
: url 패턴 충돌의 문제가 발생

web tier 의 설정을 담당하는 Spring Beans Config xml 새로 작성 (spring_beans_web.xml)
: <mvc:default-servlet-handler />
: <mvc:annotation-driven />

@Controller Bean의 scanning을 기존에는 spring_beans.xml 에서 담당했지만 exclude 시킴
<context:component-scan base-package="myspring.user">
   <!-- @Controller 어노테이션을 선언한 컴포넌트는  제외하겠다. -->
   <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

@Controller Bean의 scanning을 spring_beans_web.xml 에서 include 시킴
<context:component-scan base-package="myspring.user">
   <!-- @Controller 어노테이션을 선언한 컴포넌트는  포함하겠다. -->
   <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

web.xml  :  DispatcherSerlvet 에게 web tier 설정을 담당하는 xml 정보를 설정
	<servlet>
		<servlet-name>springDispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:config/spring_beans_web.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

xml parsing
<name>홍길동</name>
<addr>서울</addr>

json은 xml 보다 lightweight(경량)하다.
"name":"홍길동",
"addr":"서울",
"phone":["01012345678","01023456789"]

UserVO - row 1
List<UserVO> - row 여러개
Java object -> json / json -> java object 변환
 : json processor 사용
 : jackson open source