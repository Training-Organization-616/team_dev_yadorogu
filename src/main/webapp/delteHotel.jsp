<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿削除</title>
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
<div class="center">
<label>宿削除</label>
</div>
	<table >
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
				<button onclick="MoveCheck(event);" id="buttonDelete">削除</button>
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