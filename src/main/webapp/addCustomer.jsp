<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
新規登録<br>
${message}
<form action="/team_dev_yadorogu/CustomerServlet?action=add" method="post">
	<input type="text" name="customerName" placeholder="名前"><br>
	<input type="text" name="address" placeholder="住所"><br>
	<input type="email" name="email" placeholder="メールアドレス"><br>
	<input type="tel" name="tel" placeholder="電話番号"><br>
	<input type="date" name="birthday" placeholder="生年月日"><br>
	<input type="password" name="password" placeholder="パスワード"><br>
	<input type="password" name="passwordCheck" placeholder="確認用パスワード"><br>
	<button>新規登録</button>
</form>
</body>
</html>