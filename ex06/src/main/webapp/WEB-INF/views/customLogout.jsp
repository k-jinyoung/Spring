<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> Logout Page </h1>

<!-- action속성은 로그아웃을 처리할 URL을 지정한다.(여기서는 /customLogout으로 지정되어 있다) -->
<form action="/customLogout" method='post'>

<!-- CSRF공격 방지를 위한 CSRF 토큰을 폼에 포함 시킨다 -->
<!-- spring security가 자동으로 생성한 CSRF 토큰을 사용하여 폼을 보호한다. -->
<!-- name 속성에는 CSRF 토큰의 파라미터 이름을 설정하고, value 속성에는 실제 토큰 값을 설정 -->
<!-- input 타입이 hidden으로 설정되어 있어 화면에는 보이지 않는다. -->
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<button>로그아웃</button>
</form>

</body>
</html>