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


					<a href="/team_dev_yadorogu/LoginServlet?action=logout" >ログアウト</a>
                    <a href="/team_dev_yadorogu/">情報変更</a>
				</c:when>
				<c:otherwise>


					<a href="/team_dev_yadorogu/LoginServlet?action=">ログイン</a>

				</c:otherwise>

			</c:choose>
			
	</header>
<h1>やどログ</h1>

<a href="/team_dev_yadorogu/ReserveServlet?action=history">予約履歴</a>

<c:forEach items="${hotels}" var="hotels">
 <form action="/team_dev_yadorogu/ReserveServlet" method="post">
    <input type="hidden" name="hotel_id" value="${hotels.id}">
    	${hotels.id}::
        ${hotels.name}
         1泊：${hotels.price}
               
                    <c:choose>
                        <c:when test="${hotels.category_id==1}">シティホテル</c:when>
                        <c:when test="${hotels.category_id==2}">リゾートホテル</c:when>
                        <c:when test="${hotels.category_id==3}">ビジネスホテル</c:when>
                        <c:when test="${hotels.category_id==4}">旅館</c:when>
                        <c:when test="${hotels.category_id==5}">民宿</c:when>
                        <c:when test="${hotels.category_id==6}">ペンション</c:when>
                    </c:choose>
             
          チェックイン時間：${hotels.checkin}
        チェックアウト時間：${hotels.checkout}
        <button>予約</button>
		</form>
	</c:forEach>

</body>
</html>