<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
  <div class="container">
    <section id="content">
      <h1>ログイン</h1>
      <strong>${message}</strong>

      <form action="/team_dev_yadorogu/LoginServlet?action=login" method="post">
        <div>
          <input type="email" name="email" id="email"placeholder="メールアドレス" />
        </div>
        <div>
          <input type="password" name="password" id="password" placeholder="パスワード" />
        </div>
        <div>
          <input type="submit" value="ログイン">
         
          <a href=/team_dev_yadorogu/CustomerServlet?action=>新規登録</a>
        </div>
      </form>
    </section>
  </div>
  <br>
  <br>
  <div class="center">
  <a href="/team_dev_yadorogu/HotelServlet?action=">topページに戻る</a>
  </div>
</body>

</html>