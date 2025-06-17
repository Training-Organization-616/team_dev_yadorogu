<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>情報変更</title>
</head>
<body>
情報変更<br>
${message}
<form action="/team_dev_yadorogu/CustomerServlet?action=updateCustomer" method="post">
	<input type="text" name="customerName" placeholder="名前" value="${user.name}"><br>
	<input type="text" name="address" placeholder="住所" value="${user.address}"><br>
	<input type="email" name="email" placeholder="メールアドレス" value="${user.email}"><br>
	<input type="tel" name="tel" placeholder="電話番号" value="${user.tel}"><br>
	<input type="date" name="birthday" placeholder="生年月日" value="${user.birthday}"><br>
	<input type="password" name="password" placeholder="パスワード"><br>
	<input type="password" name="passwordCheck" placeholder="確認用パスワード"><br>
	<button onclick="MoveCheck(event);">変更</button>
</form>
<form action="/team_dev_yadorogu/CustomerServlet?action=delete" method="post">
<button  onclick="MoveCheck(event);">退会</button>
</form>

<script type="text/javascript">
//ダイアログでの処理
function MoveCheck(event) {
    if( confirm("操作を実行しますか？") ) {
        // OKが押された場合は、何もせずにフォーム送信
    } else {
        // キャンセルが押された場合、フォーム送信を防ぐ
        event.preventDefault();
    }
}
</script>
</body>
</html>