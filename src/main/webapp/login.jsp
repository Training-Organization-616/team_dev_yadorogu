<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--ログイン画面-->

<h1>ログイン</h1>
<strong>${message}</strong>
<form action="team_dev_yadorogu/LoginServlet?action=login" method="post">
<input type="email" name="email"placeholder="メールアドレス"><br>
<input type="password" name="password" placeholder="パスワード"><br>
<button>ログイン</button>
</form>

</body>
</html>