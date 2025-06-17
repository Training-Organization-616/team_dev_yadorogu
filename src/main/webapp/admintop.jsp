<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
<header class="header-glass">
 <div class="header-top">
  <div class="logo">
    <a href="/team_dev_yadorogu/AdminServlet?action=">やどログ</a>
  </div>

  <div class="page-title">
    <h2>管理者用ページ</h2>
  <c:choose>
    <c:when test="${not empty user}">
    <div class="li">
      <a href="/team_dev_yadorogu/LoginServlet?action=logout">ログアウト</a>
      <a href="/team_dev_yadorogu/CustomerServlet?action=update">情報変更</a>
      </div>
    </c:when>
  </c:choose>
  </div>
</div>

<nav class="header-bottom">
  <a href="/team_dev_yadorogu/AdminServlet?action=">会員一覧</a>
  <a href="/team_dev_yadorogu/addHotel.jsp">宿情報追加</a>
  <a href="/team_dev_yadorogu/HotelServlet?action=delete">宿削除</a>
</nav>

</header>
<br>
<br>
<div class="center">
<label>会員一覧</label>
</div>
	<div class="search">
	<form action="/team_dev_yadorogu/AdminServlet?action=search" method="post">
		<input type="text" name="searchAdmin" placeholder="idを入力">
		<button>検索</button>
	</form>
	</div>
	<br>
	<br>
	
	<div class="mess">
	<strong class="mes">${message}</strong>
	</div>
	
	<table>
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>状態</th>
			<th>権限付与</th>
			<th>削除</th>
		</tr>
		<c:forEach items="${customers}" var="customer">
			<tr>
				<td>${customer.id}</td>
				<td>${customer.name}</td>
				<td>
				<c:if test="${customer.admin}">会員</c:if>
				<c:if test="${!customer.admin}">管理者</c:if>
				</td>
				<td>
				<c:if test="${customer.admin}">
				<form action="/team_dev_yadorogu/AdminServlet?action=update" method="post">
				<input type="hidden" name="id" value="${customer.id}">
				<button onclick="MoveCheck(event);">管理者に変更</button>
				</form>
				</c:if>
				</td>
				<td>
				<c:if test="${customer.admin}">
				<form action="/team_dev_yadorogu/AdminServlet?action=delete" method="post">
				<input type="hidden" name="id" value="${customer.id}">
				<button onclick="MoveCheck(event);">退会</button>
				</form>
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	
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