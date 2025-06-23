<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ投稿</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/review.css">
</head>
<body>

	<div class="header-glass">
		<div class="container">
			<a href="/team_dev_yadorogu/HotelServlet?action=" class="logo">やどログ</a>

			<nav class="nav-wrapper">
				<ul class="nav-links">
					<c:choose>
						<c:when test="${not empty user}">
							<li class="greeting">こんにちは、<b>${user.name}</b>さん
							</li>
							<li><a href="/team_dev_yadorogu/LoginServlet?action=logout">ログアウト</a></li>
							<li><a
								href="/team_dev_yadorogu/CustomerServlet?action=update">情報変更</a></li>
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

	<label>口コミ投稿</label>
	<table>
		<tr>
			<th>ホテル名</th>
			<td>${hotel.name}</td>
		</tr>
		<tr>
			<th>料金</th>
			<td><fmt:formatNumber value="${hotel.price}" type="number"
					groupingUsed="true" />円</td>
		</tr>
		<tr>
			<th>チェックイン時間</th>
			<td>${hotel.checkin}</td>
		</tr>
		<tr>
			<th>チェックアウト時間</th>
			<td>${hotel.checkout}</td>
		</tr>
		<tr>
			<th>最大宿泊人数</th>
			<td>${hotel.maxperson}人</td>
		</tr>
		<tr>
			<th>カテゴリ</th>
			<td>${hotel.category_name}</td>
		</tr>
		<tr>
			<th>平均評価</th>
			<td>
				<c:choose>
					<c:when
						test="${hotel.avgevaluation == null || hotel.avgevaluation == 0}">
						<p class="stersize">未評価</p>
					</c:when>
					<c:otherwise>
						<p class="stersize">
							<span class="star5_rating" data-rate="${hotel.avgevaluation}"></span>
							<strong>星${hotel.avgevaluation}</strong>
						</p>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	<br>
	<div class="reviewcenter">
		<form action="/team_dev_yadorogu/ReviewServlet?action=addReview"
			method="post">
			<div class="reviewform">
				<input type="hidden" name="hotel_id" value="${hotel.id}">
				
				<div class="stars">
					<input id="star5" type="radio" name="evaluation" value="5">
					<label for="star5">★</label> <input id="star4" type="radio"
						name="evaluation" value="4"> <label for="star4">★</label>
					<input id="star3" type="radio" name="evaluation" value="3">
					<label for="star3">★</label> <input id="star2" type="radio"
						name="evaluation" value="2"> <label for="star2">★</label>
					<input id="star1" type="radio" name="evaluation" value="1" checked>
					<label for="star1">★</label>
					<span class="startext">評価：</span>
				</div>
				<textarea class="commentarea" name="comment" placeholder="感想を入力してください"></textarea>
			</div>
			<div class="reviewbutton">
				<button class="icon-button"> <i class="fas fa-pen"></i>投稿</button>
			</div>
		</form>
	</div>
</body>
</html>