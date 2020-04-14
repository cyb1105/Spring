javase 포함된 JDBC api는 인터페이스가 대부분을 차지한다.

java.sql, javax.sql 패키지

인터페이스 구현은 DB vendor가 한다.

DB vendor가 제공하는 JDBC Driver jar(zip)을 사용해야 한다.



- JDBC App작성 절차

  1. Driver Class Loading

     - Class.forName("jdbc.oracle.OracleDriver");

  2. DB와의 연결을 담당하는 connection객체 생성

     - Connection conn = DriverManager.getConnection(url,user,pass); 

       String url : “jdbc:oracle:thin:@192.168.0.200:1521:VCC”

       String user : "scott"

       String pass : "tiger"

  3. SQL전송해주는 역할을 담당하는 Statement 객체생성

     - Statement stmt = conn.createStatement();

  4. SQL 실행 해주는 executeQuery(), excuteUpdate() 메서드 사용

     - 조회(select)

       ResultSet rs = stmt.executeQuery("select * from users");

       while ( rset.next() ) {

       ​	String id = re.getString("userid");

       ​	integer value =rs.getInteger(2);

       }

     - 등록,갱신,삭제

       int cnt = stmt.executeUpdate("Insert int users values()");

  5. 사용했던 자원들(Resource)을 반납

     - rset.close(); stmt.close(); conn.close();

----

ORM(Object Relational Mapping)

:MyBatis, JPA

- 매핑 Rule

  | java      | db          |
  | --------- | ----------- |
  | class(VO) | Table       |
  | Object    | Row(Record) |
  | Variable  | Column      |

  

----

maven repository에서  mybatis, mybatis-spring, spring-jdbc, oracle jdbc driver, apache DBCP dependency를 pom.xml에 복사