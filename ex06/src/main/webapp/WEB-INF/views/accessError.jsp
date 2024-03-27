<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Access Denied Page</h1>

<!-- spring security에서 발생한 403Forbidden 에러의 메시지를 가져온다. -->
<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}" /></h2>

<!-- JSP파일의 모델에 추가된 msg 속성의 값을 가져온다 -->
<h2><c:out value="${msg}" /></h2>
</body>
</html>