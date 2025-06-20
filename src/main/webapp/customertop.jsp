<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿一覧</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
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


<form action="/team_dev_yadorogu/HotelServlet?action=sort" method="get">

	<select name="sortHotels">
		<option value="evaluationDesc">評価高い順</option>
		<option value="evaluationAsc">評価低い順</option>
		<option value="feeDesc">料金高い順</option>
		<option value="feeAsc">料金低い順</option>
		<option value="checkinDesc">チェックイン遅い順</option>
		<option value="checkinAsc">チェックイン早い順</option>
	</select>
	
	<button>並び替える</button>
</form>


<div class="card-container">
<c:forEach items="${hotels}" var="hotel">
  <form action="/team_dev_yadorogu/ReserveServlet" method="post" class="hotel-card">
    <input type="hidden" name="hotel_id" value="${hotel.id}">
    <h3 class="hotel-name">${hotel.name}</h3>



<p><strong>1泊：</strong>
  <fmt:formatNumber value="${hotel.price}" type="number" groupingUsed="true" />円
</p>


    <p><strong>カテゴリ：</strong>${hotel.category_name}</p>

    <p><strong>チェックイン：</strong>${hotel.checkin}</p>
    <p><strong>チェックアウト：</strong>${hotel.checkout}</p>

    <button type="submit">予約</button>
    
    </form>
    
    <form action="/team_dev_yadorogu/ReviewServlet" method="get">
      <input type="hidden" name="hotel_id" value="${hotel.id}">
      <button type="submit">詳細</button>
    </form>
    
    
  
</c:forEach>
</div>

</body>
</html>