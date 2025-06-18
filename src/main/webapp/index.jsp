<!-- HTMLのコメントの書き方 →　管理者ツールで表示される-->
<%--jspのコメントの書き方　→　管理者ツールで表示されない--%>

<!-- localhost:8090/shoppingでアクセスするとこのjspが表示される
ここでのforwardとservletでのforwardは同じ意味なので内部的にShowItemServletが実行される -->
<jsp:forward page="/HotelServlet">
    <jsp:param name="action" value="" />
</jsp:forward>