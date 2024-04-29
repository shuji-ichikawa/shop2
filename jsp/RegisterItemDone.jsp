<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録完了</title>
<link rel="stylesheet" href="./css/style2.css" type="text/css" />
</head>
<body>
<c:set var="Login_mei" value="${Login}" />
	<div class="parent">
		<p class="title"><a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">ショッピングセンター</a></p>
		<div id="cart"><a href="/shop/ProductDetails_CartListController?Login_id=${Login_mei}"><img src="./img/images.png" alt="Parrots" width="30" height="30"></a>　　<a href="/shop/LogoutController?Login_id=${ Login_mei }"><img src="./img/logout.png" alt="Parrots" width="30" height="30"></a>　　</div>
	</div>
ようこそ、<c:out value="${ Login_mei }" />さん
<p>★登録完了しました★</p><br>
<a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">商品一覧に戻る</a>
</body>
	<table border="1">
		<tr>
			<th>商品ID</th>
			<th>画像</th>
			<th>商品名</th>
			<th>単価</th>
			<th>在庫数</th>
		</tr>
			<c:forEach var="product_listitem" items="${ product_list }">
				<tr>
					<td><input type="hidden" name="shohin_id" value="${ product_listitem.shohin_id }" />
						<c:out value="${ product_listitem.shohin_id }" /></td>
					<td><img src="/shop/img/${ product_listitem.shashin }" width="50" height="50"/></td>
					<td><input type="hidden" name="shohin_mei" value="${ product_listitem.shohin_mei }" />
						<c:out value="${ product_listitem.shohin_mei }" /></td>
					<td><input type="hidden" name="tanka" value="${ product_listitem.tanka }" />
						<c:out value="${ product_listitem.tanka }" />円</td>
					<td><input type="hidden" name="zaiko" value="${ product_listitem.zaiko }" />
						<c:out value="${ product_listitem.zaiko }" />個</td>
				</tr>
			</c:forEach>
		</table>
</html>