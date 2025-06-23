<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<div class="center"><label>予約変更ページ</label></div>

<div class="mess"><strong class="mes">人数のみ変更可能です</strong></div>
	<form action="/team_dev_yadorogu/ReserveServlet?action=updateRes"method="post">
		<table >
			<input type="hidden" name="res_id" value="${rbean.id}">
			<tr>
				<th>ホテル名</th>
				<td>${hbean.name}</td>
			</tr>
			<tr>
				<th>料金</th>
				<td><fmt:formatNumber value="${hbean.price}" type="number" groupingUsed="true" />円</td>
			</tr>
			<tr>
				<th>チェックイン時間</th>
				<td>${hbean.checkin}</td>
			</tr>
			<tr>
				<th>チェックアウト時間</th>
				<td>${hbean.checkout}</td>
			</tr>
			<tr>
				<th>人数</th>
				<td>
				<select name="persons">
						<c:forEach var="i" begin="1" end="${hbean.maxperson}" step="1">
						
							<option value="${i}" <c:if test="${i==rbean.persons}">selected</c:if>>${i}</option>
						</c:forEach>
				</select>人
				</td>
			<tr>
				<th>チェックイン日</th>
				<td>${rbean.date}</td>
			</tr>
		</table>
		<br>
		<div class=center>
		<button onclick="MoveCheck(event);" onclick="MoveCheck(event);" class="btn btn-border"><span>この内容で変更<span></button>
		</div>
	</form>

	<script type="text/javascript">
		//ダイアログでの処理
		function MoveCheck(event) {
			if (confirm("変更していいですか")) {
				// OKが押された場合は、何もせずにフォーム送信
			} else {
				// キャンセルが押された場合、フォーム送信を防ぐ
				event.preventDefault();
			}
		}
	</script>
</body>
</html>