<!-- contentType에서 설정한 charset은 응답(response) 데이터의 인코딩
	pageEncoding : jsp에 포함된 인코딩-->

<%@page import="jdbc.user.vo.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자 리스트</h2>
	<%-- EL(Expression Language --%>
	${userList}
	<table border="1">
		<tr>
		<th>아이디</th>
		<th>이름</th>
		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.userid}</td>
				<td><a href="user.do?id=${user.userid}&cmd=userDetail">${user.name}</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<%--
		//1. resquest객체에 저장된 List 객체를 가져오기
		List<UserVO> list = (List<UserVO>)request.getAttribute("userList");
	out.println(list);
	
	--%>
</body>
</html>