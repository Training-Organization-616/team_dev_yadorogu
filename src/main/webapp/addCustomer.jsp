<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
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

<div class="center">
<label>新規登録</label>
</div>
<br>
<div class="mess">
	<strong class="mes">${message}</strong>
	</div>
	<form action="/team_dev_yadorogu/CustomerServlet?action=add"
		method="post">
		<table class="form-table">
			<tr>
				<th>名前</th>
				<td><input type="text" name="customerName" placeholder="名前"></td>
			</tr>
			<tr>
				<th>住所</th>
				<td><input type="text" name="address" placeholder="住所"></td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td><input type="email" name="email" placeholder="メールアドレス"></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><input type="tel" name="tel" placeholder="電話番号"></td>
			</tr>
			<tr>
				<th>生年月日</th>
				<td><input type="date" name="birthday" placeholder="生年月日"></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" name="password" placeholder="パスワード"></td>
			</tr>
			<tr>
				<th>確認用パスワード</th>
				<td><input type="password" name="passwordCheck"
					placeholder="確認用パスワード"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
				<button class="btn btn-border"><span>新規登録</span></button></td>
			</tr>
		</table>
	</form>

</body>
</html>