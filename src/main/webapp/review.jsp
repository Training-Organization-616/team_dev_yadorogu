<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ投稿</title>
</head>
<body>
宿詳細情報
<table>
	<tr><td>${hotel.name}</td></tr>
	<tr><td>料金：${hotel.price}</td></tr>
	<tr><td>チェックイン時間：${hotel.checkin}</td></tr>
	<tr><td>チェックアウト時間：${hotel.checkout}</td></tr>
	<tr><td>最大宿泊人数：${hotel.maxperson}</td></tr>
	<tr><td>カテゴリ：${hotel.category_name}</td></tr>
	<tr><td>平均評価：${hotel.avgevaluation}</td></tr>
</table>
<form action="/team_dev_yadorogu/ReviewServlet?action=addReview" method="post">
<input type="hidden" name="hotel_id" value="${hotel.id}">
<input type="number" name="evaluation">
<textarea name="comment"></textarea>
<button>投稿</button>
</form>
</body>
</html>