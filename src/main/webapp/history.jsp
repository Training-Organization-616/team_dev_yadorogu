<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約一覧</title>
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

<div class="mess"><strong class="mes">予約の変更、キャンセルはチェックイン日の1日前までです</strong></div>
	
	<br><table>
		<tr>
			<th>NO</th>
			<th>宿名</th>
			<th>価格</th>
			<th>人数</th>
			<th>チェックイン日</th>
			<th>変更</th>
			<th>キャンセル</th>
		</tr>
		
		
		
		<c:forEach items="${reservation}" var="reserve">
			<tr>
				<td>${reserve.id}</td>
				<td>${reserve.hotel_name}</td>
				<td><fmt:formatNumber value="${reserve.price}" type="number" groupingUsed="true" />円</td>
				<td>${reserve.persons}人</td>
				<td>${reserve.date}</td>
				
		
				
<!--				日にちの判断-->
				<td>
				<c:if test="${reserve.checkday}">
				<form action="/team_dev_yadorogu/ReserveServlet?action=updatescreen" method="post">
				<input type="hidden" name="res_id" value="${reserve.id}">
				<button>変更</button>
				</form></c:if>
				</td>
				<td>
				<c:if test="${reserve.checkday}">
				<form action="/team_dev_yadorogu/ReserveServlet?action=deletescreen" method="post">
				<input type="hidden" name="res_id" value="${reserve.id}">
				<button>キャンセル</button>
				</form></c:if></td>
				
			</tr>
		</c:forEach>
	</table>
	
	
</body>
</html>