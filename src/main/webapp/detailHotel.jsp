<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿詳細情報</title>
</head>
<body>
宿詳細情報
<form action="/team_dev_yadorogu/ReviewServlet?action=add" method="post">
	<input type="hidden" name="hotel_id" value="${hotel.id}">
	<button>口コミを投稿</button>
</form>
<table>
	<tr><td>${hotel.name}</td></tr>
	<tr><td>料金：${hotel.price}</td></tr>
	<tr><td>チェックイン時間：${hotel.checkin}</td></tr>
	<tr><td>チェックアウト時間：${hotel.checkout}</td></tr>
	<tr><td>最大宿泊人数：${hotel.maxperson}</td></tr>
	<tr><td>カテゴリ：${hotel.category_name}</td></tr>
	<tr><td>平均評価：${hotel.avgevaluation}</td></tr>
</table>

<c:forEach items="${reviews}" var="review">
	${review.customer_name}<br>
	${review.evaluation}<br>
	${review.comment}<br>
</c:forEach>
</body>
</html>