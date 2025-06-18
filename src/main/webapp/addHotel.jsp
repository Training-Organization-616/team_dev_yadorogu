<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿新規登録</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>


<header class="header-glass">
  <div class="header-top">
    <!-- ロゴ -->
    <div class="logo">
      <a href="/team_dev_yadorogu/AdminServlet?action=">やどログ</a>
    </div>

    <!-- タイトルとリンク -->
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

    <!-- ▼ 下端中央ボタン（header-top内） -->
    <div class="accordion-button-center">
      <button class="accordion-toggle">▼ メニューを開く</button>
    </div>
  </div>

  <!-- アコーディオン展開メニュー -->
  <div class="accordion-content">
    <nav class="header-bottom">
      <a href="/team_dev_yadorogu/AdminServlet?action=">会員一覧</a>
      <a href="/team_dev_yadorogu/addHotel.jsp">宿情報追加</a>
      <a href="/team_dev_yadorogu/HotelServlet?action=delete">宿削除</a>
    </nav>
  </div>
</header>

<!-- JavaScript：開閉処理 -->
<script>
  const toggle = document.querySelector('.accordion-toggle');
  const content = document.querySelector('.accordion-content');

  toggle.addEventListener('click', () => {
    const isVisible = content.style.display === 'block';
    content.style.display = isVisible ? 'none' : 'block';
    toggle.textContent = isVisible ? '▼ メニューを開く' : '▲ メニューを閉じる';
  });
</script>
	
	
	
	
	<br>
	<!-- 登録フォーム表示 -->
	<div class="center">
	<label>宿情報登録</label>
	</div>
	<br>
	<div class="center">
	<strong class="mes">${mess}</strong>
	</div>
	<form action="HotelServlet" method="post">
	<table>
		<tr><th>宿名</th>
				<td><input type="text" name="hotelName"></td>
		</tr>
		<tr>
			<th>分類</th>
				<td><select name="categoryId">
					<option value="1">シティホテル</option>
					<option value="2">リゾートホテル</option>
					<option value="3">ビジネスホテル</option>
					<option value="4">旅館</option>
					<option value="5">民宿</option>
					<option value="6">ペンション</option>
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
		
		<br>
		
		<div class="center">
		<button onclick="MoveCheck(event);" class="regist"><span>登録</span></button>
		<input type="hidden" name="action" value="addHotel">
		</div>
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