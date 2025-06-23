<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿詳細情報</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
</head>
<body>

<div class="header-glass">
  <div class="container">
    <a href="/team_dev_yadorogu/HotelServlet?action=" class="logo">やどログ</a>

    <nav class="nav-wrapper">
      <ul class="nav-links">
        <c:choose>
          <c:when test="${not empty user}">
            <li class="greeting">こんにちは、<b>${user.name}</b>さん</li>
            <li><a href="/team_dev_yadorogu/LoginServlet?action=logout">ログアウト</a></li>
            <li><a href="/team_dev_yadorogu/CustomerServlet?action=update">情報変更</a></li>
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

<label>宿詳細情報</label>
<form action="/team_dev_yadorogu/ReviewServlet?action=add" method="post">
	<input type="hidden" name="hotel_id" value="${hotel.id}">
	<button>口コミを投稿</button>
</form>
<table>
	<tr><th>ホテル名</th><td>${hotel.name}</td></tr>
	<tr><th>料金</th><td><fmt:formatNumber value="${hotel.price}" type="number" groupingUsed="true" />円</td></tr>
	<tr><th>チェックイン時間</th><td>${hotel.checkin}</td></tr>
	<tr><th>チェックアウト時間</th><td>${hotel.checkout}</td></tr>
	<tr><th>最大宿泊人数</th><td>${hotel.maxperson}</td></tr>
	<tr><th>カテゴリ</th><td>${hotel.category_name}</td></tr>
	<tr><th>平均評価</th><td><p class="stersize">
    <span class="star5_rating" data-rate="${hotel.avgevaluation}"></span>
    <strong>星${hotel.avgevaluation}</strong>
</p></td></tr>
</table>

<div class="review-cards">
  <c:forEach items="${reviews}" var="review">
    <div class="review-card">
      <p class="name"><strong>${review.customer_name}</strong></p>
      <p class="stersize">
      <strong>星${review.evaluation}</strong>
        <span class="star5_rating" data-rate="${review.evaluation}"></span>
        
      </p>

      <div class="balloon5">
        <div class="faceicon">
          <img src="/team_dev_yadorogu/css/icon2.png" alt="アイコン">
        </div>
        <div class="chatting">
          <div class="says">
            <p>${review.comment}</p>
          </div>
        </div>
      </div>
    </div>
  </c:forEach>
</div>

</body>
</html>