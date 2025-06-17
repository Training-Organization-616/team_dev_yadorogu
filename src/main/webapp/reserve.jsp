<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約確認</title>
</head>
<body>
	<form action="/team_dev_yadorogu/ReserveServlet?action=add" method="post">
		宿予約${hotel.id}
		<input type="hidden" name="hotel_id" value="${hotel.id}">
		<table>
			<tr>
				<td>${hotel.name}</td>
			</tr>
			<tr>
				<td>料金：${hotel.price}</td>
			</tr>
			<tr>
				<td>チェックイン時間：${hotel.checkin}</td>
			</tr>
			<tr>
				<td>チェックアウト時間：${hotel.checkout}</td>
			</tr>
			<tr>
				<td>
					人数：<select name="persons">
						<c:forEach var="i" begin="1" end="${hotel.maxperson}" step="1">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>チェックイン日時：<input type="date" name="date"></td>
			</tr>
		</table>
		お客様情報確認
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
		<button>この内容で予約</button>
	</form>
</body>
</html>