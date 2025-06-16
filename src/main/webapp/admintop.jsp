<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
		
			<c:choose>
				<c:when test="${not empty user}">
				
					<h3>
						管理者用ページ
					</h3>


					<a href="/team_dev_yadorogu/LoginServlet?action=logout" >ログアウト</a>
                    <a href="/team_dev_yadorogu/">情報変更</a>
				</c:when>
				<c:otherwise>


					<a href="/team_dev_yadorogu/LoginServlet?action=">ログイン</a>

				</c:otherwise>

			</c:choose>
			
	</header>
	<h1>やどログ</h1>
	<table border="1">
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
				<c:if test="${customer.isAdmin}">会員</c:if>
				<c:if test="${!customer.isAdmin}">管理者</c:if>
				</td>
				<td>
				<c:if test="${customer.isAdmin}">
				<form action="/team_dev_yadorogu/AdminServlet?action=update">
				<input type="hidden" name="isAdmin" value="${customer.isAdmin}">
				<input type="hidden" name="id" value="${customer.id}">
				<button onclick="MoveCheck(event);">管理者に変更</button>
				</form>
				</c:if>
				</td>
				<td>
				<c:if test="${customer.isAdmin}">
				<form action="/team_dev_yadorogu/AdminServlet?action=delete">
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