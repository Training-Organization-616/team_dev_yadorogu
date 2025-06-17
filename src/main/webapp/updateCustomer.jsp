<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>情報変更</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/customer.css">
</head>
<body>
<div class="header-glass">
  <div class="container">
  	<c:if test="${user.admin}">
    	<a href="/team_dev_yadorogu/HotelServlet?action=" class="logo">やどログ</a>
	</c:if>
    <c:if test="${not user.admin}">
    	<a href="/team_dev_yadorogu/AdminServlet?action=" class="logo">やどログ</a>
	</c:if>
  </div>
</div>

<div class="center"><label>情報変更</label></div>

<br>
<div class="mess">
	<strong class="mes">${message}</strong>
	</div>
<form action="/team_dev_yadorogu/CustomerServlet?action=updateCustomer" method="post">
  <table class="form-table">
    <tr>
      <th>名前</th>
      <td><input type="text" name="customerName" value="${user.name}" placeholder="名前"></td>
    </tr>
    <tr>
      <th>住所</th>
      <td><input type="text" name="address" value="${user.address}" placeholder="住所"></td>
    </tr>
    <tr>
      <th>メールアドレス</th>
      <td><input type="email" name="email" value="${user.email}" placeholder="メールアドレス"></td>
    </tr>
    <tr>
      <th>電話番号</th>
      <td><input type="tel" name="tel" value="${user.tel}" placeholder="電話番号"></td>
    </tr>
    <tr>
      <th>生年月日</th>
      <td><input type="date" name="birthday" value="${user.birthday}" placeholder="生年月日"></td>
    </tr>
    <tr>
      <th>パスワード</th>
      <td><input type="password" name="password" placeholder="パスワード"></td>
    </tr>
    <tr>
      <th>確認用パスワード</th>
      <td><input type="password" name="passwordCheck" placeholder="確認用パスワード"></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center;">
        <button class="btn btn-border" onclick="MoveCheck(event);"><span>変更</span></button>
      </td>
    </tr>
  </table>
</form>

<div class="right">
<form action="/team_dev_yadorogu/CustomerServlet?action=delete" method="post">
<button  onclick="MoveCheck(event);" class="out"><span>退会</span></button>
</form>
</div>
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