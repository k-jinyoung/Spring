<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Custom Login Page</h1>

<!-- 로그인 과정에서 오류가 발생했다면, 해당 오류 메시지를 표시한다. -->
<!-- ${error}는 컨트롤러에서 전달된 오류 메시지를 출력한다. -->
<h2><c:out value="${error}" /></h2>

<!-- 로그아웃이 성공했다면, 해당 메시지를 표시한다. -->
<!-- ${logout}는 컨트롤러에서 전달된 로그아웃 메시지를 출력한다. -->
<h2><c:out value="${logout}" /></h2>

<!-- 사용자의 로그인 정보를 입력할 폼(POST 방식) -->
<form method='post' action="/login">

<div>
	<!-- 사용자 이름 입력할 입력란(기본값 admin) -->
	<input type='text' name='username' value='admin'>
</div>
<div>
	<!-- 비밀번호를 입력할 입력란(기본값 admin) -->
	<input type='password' name='password' value='admin'>
</div>
<div>
	<input type='submit'>
</div>
<!-- spring-security의 csrf(Cross-Site Request Forgery) 토큰 추가 -->
<!-- 이걸로 보안을 강화할 수 있다. -->
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</form>

</body>
</html>