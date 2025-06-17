<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>


<div class="header-glass">
  <div class="container">
    <div class="logo">やどログ</div> 

    <nav class="nav-wrapper">
      <ul class="nav-links">
        <c:choose>
          <c:when test="${not empty user}">
            <li class="greeting">こんにちは、<b>${user.name}</b>さん</li>
            <li><a href="/team_dev_yadorogu/LoginServlet?action=logout">ログアウト</a></li>
            <li><a href="/team_dev_yadorogu/">情報変更</a></li>
          </c:when>
          <c:otherwise>
            <li><a href="/team_dev_yadorogu/LoginServlet?action=">ログイン</a></li>
          </c:otherwise>
        </c:choose>
        <li><a href="/team_dev_yadorogu/ReserveServlet?action=history">予約履歴</a></li>
      </ul>
    </nav>
  </div>
</div>






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