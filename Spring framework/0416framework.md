- J2EE (Enterprise Edition) 기술
  J2SE, J2EE 
   : Servlet, JSP(Java Server page), JSTL(Java Standard Tag Library)
  Spring MVC  구조와 핵심컴포넌트

----

J2EE기술중에서 Servlet과 JSP(java server page), JSTL(java standard tag library)

- tomcat(web container)서버에서 동작한다.
- Servlet는 클래스, JSP는 스크립트
- JSP와 비슷한 종류는 PHP
- html, css, javascript : 정적인(static) 컨텐츠 생산
- html에서 UserDAO(DB연동객체) 객체의 method를 호출할 수 있을까? 없음
- servlet과 jsp는 : 동적인(dynamic)컨텐츠 생산

html -> servlet/jsp -> dao객체

- MVC패턴 (model view controller)

  - Seperation of Concerns(=Responsibility 책임, 역할) - 역할분리 유지보수성 향상

- MVC패턴을 기반으로 하는 web architecture

  - model1 아키텍쳐

    model - java(DAO, Service, VO)

    view - jsp, html

    controller - jsp

  - model2 아키텍쳐

    model - java(DAO, Service, VO)

    view - jsp, html

    controller - Servlet

  spring mvc는 model2아키텍쳐이며, Front Controller 역할을 하는 DispatcherServle클래스를 제공한다.

- GET/POST 방식이 무엇이고, 차이점이 무엇인지?

  get/post는 클라이언트가 서버에게 데이터를 보내는 방식

  

- HelloServlet 클래스의 객체 생성은 누가 할까? web container

    JSTL(java standard tag library) 
    : scriptless <% %> jsp를 작성할때 java code 를 사용하지 말자
   <c:forEach>
   </c:forEach>























































