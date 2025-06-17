<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約一覧</title>
</head>
<body>
	<table>
		<tr>
			<th>NO</th>
			<th>宿名</th>
			<th>価格</th>
			<th>人数</th>
			<th>日時</th>
		</tr>
		<c:forEach items="${reservation}" var="reserve">
			<tr>
				<td>${reserve.id}</td>
				<td>${reserve.hotel_name}</td>
				<td>${reserve.price}</td>
				<td>${reserve.persons}</td>
				<td>${reserve.date}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>