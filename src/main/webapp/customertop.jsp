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
		
			<c:choose>
				<c:when test="${not empty user}">
				
					<h3>
						こんにちは、<b>${user.name}</b>さん
					</h3>


					<a href="/team_dev_yadorogu/loginServlet?action=logout" >ログアウト</a>
                    <a href="/team_dev_yadorogu/">情報変更</a>
				</c:when>
				<c:otherwise>


					<a href="/team_dev_yadorogu/loginServlet?action=login">ログイン</a>

				</c:otherwise>

			</c:choose>
	</header>
<h1>やどログ</h1>

<!--名前は？-->
 <form action="team_dev_yadorogu/ReserveServlet?action=add" method="post">
    <c:forEach items="${hotels}" var="hotels">
    <input type="hidden" name="hotel_id" value="${hotels.id}">
        ${hotels.name}
         1泊：${hotels.price}
               
                    <c:choose>
                        <c:when test="${hotels.category_id==0}">シティホテル</c:when>
                        <c:when test="${hotels.category_id==1}">リゾートホテル</c:when>
                        <c:when test="${hotels.category_id==2}">ビジネスホテル</c:when>
                        <c:when test="${hotels.category_id==3}">旅館</c:when>
                        <c:when test="${hotels.category_id==4}">民宿</c:when>
                        <c:when test="${hotels.category_id==5}">ペンション</c:when>
                    </c:choose>
             
          チェックイン時間：${hotels.checkin}
        チェックアウト時間：${hotels.checkout}
        <button>予約</button>
    </c:forEach>
</form>

</body>
</html>