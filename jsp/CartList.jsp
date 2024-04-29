<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品購入(カート)</title>
<link rel="stylesheet" href="./css/style2.css" type="text/css" />
</head>
<body>
<c:set var="Login_mei" value="${Login}" />
	<div class="parent">
		<p class="title"><a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">ショッピングセンター</a></p>
		<div id="cart"><a href="/shop/LogoutController?Login_id=${ Login_mei }"><img src="./img/logout.png" alt="Parrots" width="30" height="30"></a>　　</div>
	</div>
<c:out value="${ Login_mei }" />さん
<h1>商品購入画面</h1>
	<c:forEach var="msg" items="${ msglist }">
		<c:out value="${ msg }" /><br />
	</c:forEach>
<c:choose>
<c:when test="${ cartlist.size() != 0 }">
<form action="/shop/CartList_ThanksController" method="POST">
<input type="hidden" name="Login_id" value="${ Login_mei }" />
	<table>
		<tr>
			<td>画像</td>
			<td>商品名</td>
			<td>単価</td>
			<td>数量</td>
			<td>注文</td>
		</tr>
		<c:forEach var="cartlistitem" items="${ cartlist }">
			<tr>
				<td><img src="/shop/img/${ cartlistitem.shashin }" width="50" height="50"/></td>
				<td><input type="hidden" name="shohin_mei" value="${ cartlistitem.shohin_mei }" />
					<c:out value="${ cartlistitem.shohin_mei }" /></td>
				<td><input type="hidden" name="tanka" value="${ cartlistitem.tanka }" />
					<c:out value="${ cartlistitem.tanka }" />円</td>
				<td><input type="hidden" name="kosu" value="${ cartlistitem.kosu }" />
					<c:out value="${ cartlistitem.kosu }" />個</td>
				<td><a href="/shop/DeleteController?shohin_mei=${cartlistitem.shohin_mei}&Login_id=${ Login_mei }">削除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td>合計<c:out value="${ sum }" />円</td>
		</tr>
		<tr>
			<td><input type="submit" value="注文確定"></td>
		</tr>
		</table>
	</form>
	</c:when>
	<c:otherwise>カートが空です</c:otherwise>
</c:choose>
<a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">商品一覧に戻る</a>
</body>
</html>