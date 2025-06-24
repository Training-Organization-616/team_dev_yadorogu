<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿一覧</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
	
</head>
<body>

<div class="header-glass">
  <div class="container">
    <a href="/team_dev_yadorogu/HotelServlet?action=" class="logo">やどログ</a>
    
    <nav class="nav-wrapper">
      <ul class="nav-links">
        <c:choose>
          <c:when test="${not empty user}">
            <li class="greeting">こんにちは、<b>${user.name}</b>さん</li>
            <li><a href="/team_dev_yadorogu/LoginServlet?action=logout">ログアウト</a></li>
            <li><a href="/team_dev_yadorogu/CustomerServlet?action=update">情報変更</a></li>
          </c:when>
          <c:otherwise>
            <li><a href="/team_dev_yadorogu/LoginServlet?action=">ログイン</a></li>
          </c:otherwise>
        </c:choose>
        <li><a href="/team_dev_yadorogu/ReserveServlet?action=history">予約履歴</a></li>
      </ul>
    </nav>
  </div>
</div>

<div class="search-outside">
<form action="/team_dev_yadorogu/HotelServlet?action=sort" method="post">
  <div class="custom-dropdown">
    <button type="button" class="dropdown-toggle" onclick="toggleDropdown()">
      <span id="selected-value"><strong>--選択してください--</strong></span>
<span class="dropdown-arrows" style="display: inline-flex; flex-direction: row;font-size: 20px ; line-height: 1;">
  <span style="font-size: 20px;"><strong>&#8645;</strong></span>
</span>
    </button>
    <ul class="dropdown-menu">
      <li onclick="selectOption('evaluationDesc', '評価高い順')"><strong>評価高い順</strong></li>
      <hr>
      <li onclick="selectOption('evaluationAsc', '評価低い順')"><strong>評価低い順</strong></li>
      <hr>
      <li onclick="selectOption('feeDesc', '料金高い順')"><strong>料金高い順</strong></li>
      <hr>
      <li onclick="selectOption('feeAsc', '料金低い順')"><strong>料金低い順</strong></li>
      <hr>
      <li onclick="selectOption('checkinDesc', 'チェックイン遅い順')"><strong>チェックイン遅い順</strong></li>
      <hr>
      <li onclick="selectOption('checkinAsc', 'チェックイン早い順')"><strong>チェックイン早い順</strong></li>
      <hr>
      <li onclick="selectOption('checkoutDesc', 'チェックアウト遅い順')"><strong>チェックアウト遅い順</strong></li>
      <hr>
      <li onclick="selectOption('checkoutAsc', 'チェックアウト早い順')"><strong>チェックアウト早い順</strong></li>
    </ul>
    <input type="hidden" name="sortHotels" id="sort-value" />
  </div>
</form>

</div>


<div class="card-container">
<c:forEach items="${hotels}" var="hotel">
<div class="hotel-card">
  
		<a class="cardTitle" href="/team_dev_yadorogu/ReviewServlet?hotel_id=${hotel.id}">${hotel.name}</a>
		
		   
    <c:choose>
      <c:when test="${hotel.avgevaluation == null || hotel.avgevaluation == 0}">
        <p class="stersize">未評価</p>
      </c:when>
      <c:otherwise>
<p class="stersize">
    <span class="star5_rating" data-rate="${hotel.avgevaluation}"></span>
    <strong>星${hotel.avgevaluation}</strong>
    <span class="kutikomi">口コミ${hotel.commentCount}件</span>
</p>
      </c:otherwise>
    </c:choose>
    
    

<p><strong>1泊：</strong>
  <span class="price"><fmt:formatNumber value="${hotel.price}" type="number" groupingUsed="true" />円</span>
</p>


    <p><strong>カテゴリ：</strong>${hotel.category_name}</p>

    <p><strong>チェックイン：</strong>${hotel.checkin}</p>
    <p><strong>チェックアウト：</strong>${hotel.checkout}</p>
    
    
    <div class="button-wrapper">
	<form action="/team_dev_yadorogu/ReserveServlet" method="get" >
    	<input type="hidden" name="hotel_id" value="${hotel.id}">
    	<button type="submit">予約</button>
     </form>
     
    <form action="/team_dev_yadorogu/ReviewServlet" method="get">
      <input type="hidden" name="hotel_id" value="${hotel.id}">

      <button type="submit">詳細</button>
    </form>
    
   </div>
    </div>
    
</c:forEach>
</div>
<div class="bottomFooter__topBtn" onclick="scrollToTop()">TOP</div>


<script>
  function scrollToTop() { 
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
</script>
<script type="text/javascript">
const dropdown = document.querySelector('.custom-dropdown');
const toggleButton = document.querySelector('.dropdown-toggle');
 
toggleButton.addEventListener('click', () => {
  dropdown.classList.toggle('open'); // ドロップダウンの開閉を切り替え
});
function selectOption(value, label) {
    document.getElementById('selected-value').textContent = label;
    document.getElementById('sort-value').value = value;
    document.querySelector('form').submit();
  }
</script>
</body>
</html>