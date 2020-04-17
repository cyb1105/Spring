- DataSource 인터페이스를 구현한 BasicDataSource 클래스를 Bean으로 등록

```xml
spring_bean.xml파일
<!-- properties file 설정 -->
	<context:property-placeholder
		location="classpath:config/values.properties" />
<bean id=" dataSource "
		class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="driverClassName" value="${db.driverClass}" />
		<property name="url" value="${db.url}" />
		<property name="username" value="${db.username}" />
		<property name="password" value="${db.password}" />
</bean>
<!--properties file--> 
db.driverClass=oracle.jdbc.OracleDriver
db.url=jdbc:oracle:thin:@127.0.0.1:1521:xe
db.username=scott
db.password=tiger

```

- SqlSessionFactoryBean 클래스를 Bean으로 등록

```xml
	<bean id="sqlSessionFactory"
		class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref=" dataSource " />
        <!--mybatis conf xml설정-->
		<property name="configLocation"
			value="classpath:config/SqlMapConfig.xml" /> //VO객체정보 설정
        <!--mapper(sql) xml-->
		<property name="mapperLocations">
			<list>
				<value>classpath:config/*Mapper.xml</value>
			</list>
		</property>
	</bean>
```

- SqlSessionTemplate클래스를 bean으로 등록

```xml
	<!-- SqlSessionTemplate클래스를 bean으로 등록 -->
	<bean id="sqlSession"
		class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg ref="sqlSessionFactory" />
	</bean>
```



----

- Log4J :logging을 위한 open source 라이브러리
  - Trace, Debug, Info, Warn, Error

for( int i=0;i++) {
    //System.out.println("i값은 " + i);
}

Logger logger = LogManager.getLogger(); 
for( int i=0;i++) {
    logger.debug("i값은 " + i); //1)
    logger.error(" error msg " + e.getMessage()); //2)
}
현재 log level이 debug 이면 ? 1), 2)
현재 log level이 error 이면 ? 2)

src/log4j2.xml 설정 xml 이 필요함
: log level 설정, 
: appender(log 어디에 출력할지) 설정

콘솔, 파일

----

Controller: 화면과 service를 연결해주는 객체

Service: Business Logic(업무로직 ex) 대출이자계산, 신용등급계산)을 포함하는 객체

DAO: Date Access Logic을 포함하는 객체

VO : Value Object 값을 저장하는 객체

myspring.user.dao,

​						 .dao.mapper,

​						 .service,

​						 .VO,

​						 .controller

@Repository, @Service 어노테이션이 사용된 클래스들을 추가

```xml
<<context:component-scan base-package="myspring.user" />
```

----

- Mapper 인터페이스의 사용 : Type Safe하게 Query를 호출해보자

```xml
User는 myspring.user.vo.UserVO 클래스를 줄여쓴 alias문자열이다.
VO에 대한 aliasing 설정은 SqlMapConfig.xml(Mybatis Config xml)에 설정하였음
<mapper namespace="userNS">
	<select id="selectUserById" parameterType="string" resultType="User">
	<!-- setter : 컬럼명과 setter method명이 일치하므로  -->
		select * from users where userid=#{value}
	</select>
</mapper>

```



- SqlSession 인터페이스 - Mapper XML(SQL문 포함)에 있는 SQL을 실행(수행)

:selectOne(stmt, parameter), selectList(), insert(stmt, parameter), update, delete()

```java
//@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
    private SqlSession session;
}
```

//SQL id를  값이 단순 문자열이므로 Type safe하지 않아서, 잠재적인 runtime에러를 발생 시킬 수 있다. -> mapper 인터페이스를 만들어라

UserVO user = sqlSession.selectOne("userNS.selectUserById", "gildong");

흐름 : service-> DAO -> SqlSession-> Mapper.xml



- Mapper 인터페이스

흐름 :  service-> DAO -> Mapper -> SqlSession-> Mapper.xml

Mapper 인터페이스 내의 메서드 명은 Mapper.xml에 선언된 SQL id와 반드시 같아야한다.

```java
public interface UserMapper{
	UserVO selectUserById(String id);
}

//DAO에서
//@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
    private UserMapper userMapper;
}

```



Level 1)UserMapper가 SqlSession을 의존하는 설정
아래와 같이 설정하면 Mapper인터페이스가 추가될때마다 설정을 계속 추가해야한다.

```xml
<bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean"> 
	<property name="mapperInterface" value="myspring.user.dao.mapper.UserMapper"/> 
	<property name="sqlSessionTemplate" ref="sqlSession" />
</bean>
```



Level2)

사용자 정의 어노테이션 선언

```java
public @interface MyMapper{

}
@MyMapper //나는 Mapper인터페이스 역할을 합니다.
public interface UserMapper{
	UserVO selectUserById(String id);
}
@MyMapper // 새로운 Mapper 등록가능
public interface USer2Mapper {
    
}
```

- Mapper 추가 선언

```xml
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<property name="basePackage" value="myspring.user.dao.mapper" />
	<property name="annotationClass"			value="myspring.user.dao.mapper.MyMapper" />
</bean>

```

---

- 리뷰 : 
MyBatis 에서 테이블간의 의존관계가 있는 경우, 컬럼명과 VO의 getter/setter 이름이
일치하지 않는 경우
- J2EE (Enterprise Edition) 기술
 J2SE, J2EE 
 : Servlet, JSP(Java Server page), JSTL(Java Standard Tag Library)
Spring MVC  구조와 핵심컴포넌트
----------------------------------------------------------------------
CREATE TABLE STUDENT 
(
	STU_ID         NUMBER (6) NOT NULL PRIMARY KEY,
	STU_NAME       VARCHAR2 (20) NOT NULL,
	STU_AGE        NUMBER (3) NOT NULL,
	STU_GRADE      VARCHAR2 (20),
	STU_DAYNIGHT   VARCHAR2 (20),
	DEPT_ID        NUMBER (4) NOT NULL,
	FOREIGN KEY (DEPT_ID) REFERENCES DEPT (DEPT_ID)
);
CREATE TABLE DEPT 
(
	DEPT_ID     NUMBER (4) NOT NULL PRIMARY KEY,
	DEPT_NAME   VARCHAR2 (30) CONSTRAINT DEPT_NAME_NN NOT NULL
);

public class StudentVO {
	private Integer id;
	private String name;
	private Integer age;
	private String grade;
	private String daynight;
	//private Integer deptid; (X)
	private DeptVO dept; //1:1관계 (O)
}
public class DeptVO {
	private Integer deptid;
	private String deptname;
}
-- StudentMapper.xml
resultType=>"User" : 컬럼명과 VO의 getter/setter의 이름이 같으면
resultMap=>컬럼명과 VO의 getter/setter의 이름이 같지 않은 경우에 사용하고
                개발자과 수동으로 매핑을 해주어야 한다
	<select id="selectStudentDeptById" resultMap="studentDeptResultMap">
		select s.stu_id,
		s.stu_name,
		s.stu_age,
		s.stu_grade,
		s.stu_daynight,
		d.dept_id,
		d.dept_name
		from student s, dept d
		where s.dept_id = d.dept_id
	</select>
​             //Student - myspring.user.vo.StudentVO
​	<resultMap id="studentDeptResultMap" type="Student">
​                          <!--setId()
​		<id property="id" column="stu_id" javaType="Integer" jdbcType="NUMERIC" />
​		<result property="name" column="stu_name" javaType="String"
​			jdbcType="VARCHAR" />
​		<result property="age" column="stu_age" javaType="Integer"
​			jdbcType="NUMERIC" />
​		<result property="grade" column="stu_grade" javaType="String"
​			jdbcType="VARCHAR" />
​		<result property="daynight" column="stu_daynight" javaType="String"
​			jdbcType="VARCHAR" />
​		<!-- setDept(DeptVO dept) 
​                               Dept : myspring.user.vo.DeptVO 
​                            -->	
​		<association property="dept" column="dept_id" javaType="Dept"
​			resultMap="deptResultMap" />
​	</resultMap>
​	<resultMap id="deptResultMap" type="Dept">
​                          <!--setDeptid() -->
​		<id property="deptid" column="dept_id" javaType="Integer" jdbcType="NUMERIC" />
​		<result property="deptname" column="dept_name" javaType="String"
​			jdbcType="VARCHAR" />
​	</resultMap>



>테이블의 컬럼명과 VO객체의(변수,getter,setter)이름을 동일하게 설정하지 못하는 상황이 생길 수 있다.
>
>UserMapper.xml처럼 resultType을 사용할때는 테이블의 컬럼명과 VO객체의 (변수,getter,setter)이름을 동일하게 설정해야하는데
>
>StudentMapper.xml은 resultMap을 사용하여 <resultMap id="studentDeptResultMap" type="Student"> 이렇게 설정하고 테이블의 컬럼명과 VO객체의(변수,getter,setter)이름을 동일하게 설정하지않고 resultMap에서 다르게 설정해도 된다.



















