<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約確認</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/customer.css">
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
<br>
	<form action="/team_dev_yadorogu/ReserveServlet?action=add" method="post">
		<div class="center"><label>宿予約</label></div>
		<input type="hidden" name="hotel_id" value="${hotel.id}">
		<br>
<div class="mess">
	<strong class="mes">${message}</strong>
	</div>
		<table>
			<tr><th>ホテル名</th>
				<td>${hotel.name}</td>
			</tr>
			<tr><th>料金</th>
				<td><fmt:formatNumber value="${hotel.price}" type="number" groupingUsed="true" />円</td>
			</tr>
			<tr><th>チェックイン時間</th>
				<td>${hotel.checkin}</td>
			</tr>
			<tr><th>チェックアウト時間</th>
				<td>${hotel.checkout}</td>
			</tr>
			<tr>
			<th>人数</th>
				<td>
				<select name="persons">
						<c:forEach var="i" begin="1" end="${hotel.maxperson}" step="1">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr><th>チェックイン日時</th>
				<td><input type="date" min="${today}" name="date"></td>
			</tr>
		</table>
		<br>
		<div class="center"><label>お客様情報確認</label></div>
		<table>
			<tr>
				<th>お名前</th>
				<td>${user.name}</td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td>${user.tel}</td>
			</tr>
			<tr>
				<th>e-mail</th>
				<td>${user.email}</td>
			</tr>
		</table>
		<br>
		<div class="center">
		<button class="btn btn-border"><span>予約を確定する</span></button>
		</div>
	</form>
</body>
</html>