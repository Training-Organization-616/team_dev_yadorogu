<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
<header >
		
			<c:choose>
				<c:when test="${not empty user}">
				
					<h3 class=">
						こんにちは、<b>${user.name}</b>さん
					</h3>


					<a href="/team_dev_yadorogu/LoginServlet?action=logout" >ログアウト</a>
<a href="/team_dev_yadorogu/">情報変更</a>
				</c:when>
				<c:otherwise>


					<a href="/team_dev_yadorogu/LoginServlet?action=login">ログイン</a>

				</c:otherwise>

			</c:choose>
			
	</header>
</header>
</body>
</html>