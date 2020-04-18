4월17일(금)
: Servlet / JSP  기능 추가 
: Spring MVC 설치, 핵심 컴포넌트 구조 , 어노테이션 사용 

16일 리뷰
J2SE / J2EE (enterprise edition) 
: j2ee 가 제공하는 API에는 대부분이 인터페이스로 구성되어 있다. 
: 인터페이스들은 누가 구현하는가? was(web app server)  vendor
: web container + ejb container = was
: servlet/jsp, ejb, jpa(java persistence api), jta(java transaction api),
  jms(java messaging service, async)
: servlet 과 jsp 
 serlvet : java code 내에 html을 포함시킬 수 있다. 컴파일
 JSP : html 내에 java code를 포함시킬 수 있다. 스크립트 

  html -> servlet/jsp -> java
  MVC 패턴을 기반으로 web app architecture
  model2 architecture 
     View : JSP(jstl), Html
     Controller : Servlet 
     Model : java(dao, service, vo) 

OLTP(Online Transaction Processing)
Batch Program 

Spring MVC 설치
web.xml에 2가지 설정
1. Spring Beans Configuration XML 정보를 Tomcat에 알려줘야 함
2. FrontController 역할을 수행하는 DispatcherServlet 클래스를 설정

@Controller : 컨트롤러 클래스를 Bean으로 등록
@RequestMapping : request(요청) url을 controller에 정의하는 method 위에 선언
   : @RequestMapping("/userList.do")
     public ModelAndView userList() { ... }
   HandlerMapping : 요청 url과 매핑되는 controller의 method명을 알고 있음
@RequestParam
  : request.getParameter("userid");  //query string 형태로 보낸 문자열의 값을 추출

ModelAndView(viewName, modelName, modelObject)
Model
  : addAttribute(modelName, modelObject)