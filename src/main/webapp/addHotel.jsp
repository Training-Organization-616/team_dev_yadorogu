<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿新規登録</title>
</head>
<body>

	<h1>やどログ</h1>
	<h2>管理者用ページ</h2>
	
	<!-- 廣瀬修正点 -->
	<a href="#">ログアウト</a>
	
	<br>
	<!-- 登録フォーム表示 -->
	<label>宿情報登録</label>
	<form action="team_dev_yadorogu/HotelServlet" method="post">
	<table>
		<tr><th>宿名</th>
				<td><input type="text" name="hotelName"></td>
		</tr>
		<tr>
			<th>分類</th>
				<td><select name="categoryId">
					<option value="0">シティホテル</option>
					<option value="1">リゾートホテル</option>
					<option value="2">ビジネスホテル</option>
					<option value="3">旅館</option>
					<option value="4">民宿</option>
					<option value="5">ペンション</option>
				</select></td></tr>
				<tr>
			<th>料金</th>
				<td><input type="text" name="price"></td>
				</tr><tr>
			<th>チェックイン</th>
				<td><input type="time" name="checkin"></td>
				</tr><tr>
			<th>チェックアウト</th>
				<td><input type="time" name="checkout"></td>
				</tr><tr>
			<th>最大宿泊人数</th>
				<td><input type="text" name="maxpersons"></td>
		</tr>	
		</table>
		<!-- クリックしたらダイアログ表示 -->
		<button onclick="MoveCheck(event);">登録</button>
		<input type="hidden" name="action" value="addHotel">
	</form>
	
<script type="text/javascript">
//ダイアログでの処理
function MoveCheck(event) {
    if( confirm("新規登録していいですか") ) {
        // OKが押された場合は、何もせずにフォーム送信
    } else {
        // キャンセルが押された場合、フォーム送信を防ぐ
        event.preventDefault();
    }
}

</script>

</body>
</html>