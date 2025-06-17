<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			
			<a href="/team_dev_yadorogu/AdminServlet?action=">会員一覧</a>
			<a href="/team_dev_yadorogu/addHotel.jsp">宿情報追加</a>
			<a href="/team_dev_yadorogu/dHotelServlet?action=delete">宿削除</a>
			
			
	</header>
	<h1>やどログ</h1>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>削除</th>
		</tr>
		<c:forEach items="${hotels}" var="hotel">
			<tr>
				<td>${hotel.id}</td>
				<td>${hotel.name}</td>
				<td>
				<form action="/team_dev_yadorogu/HotelServlet?action=deleteHotel" method="post">
				<input type="hidden" name="id" value="${hotel.id}">
				<button onclick="MoveCheck(event);">削除</button>
				</form>
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