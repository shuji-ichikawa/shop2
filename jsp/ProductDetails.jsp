<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link rel="stylesheet" href="./css/style2.css" type="text/css" />
</head>
<body>
	<form action="/shop/ProductDetails_CartListController" method="POST">
	<input type="hidden" name="shohin_id" value="${ product_detail.shohin_id }"/>
	<c:set var="Login_mei" value="${Login}" />
	<input type="hidden" name="Login_id" value="${ Login_mei }" />
	<div class="parent">
		<p class="title"><a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">ショッピングセンター</a></p>
		<div id="cart"><a href="/shop/ProductDetails_CartListController?Login_id=${Login_mei}"><img src="./img/images.png" alt="Parrots" width="30" height="30"></a>　　<a href="/shop/LogoutController?Login_id=${ Login_mei }"><img src="./img/logout.png" alt="Parrots" width="30" height="30"></a>　　</div>
	</div>
	ようこそ、<c:out value="${ Login_mei }" />さん
	<br>
	<h2>商品詳細</h2>
		<table border="1">
		<tr>
			<td rowspan="5"><input type="hidden" name="shashin" value="${ product_detail.shashin }"/><img src="/shop/img/${ product_detail.shashin }" width="500" height="500"/></td>
			<td>商品名</td>
			<td>
				<input type="hidden" name="shohin_mei" value="${ product_detail.shohin_mei }" />
				<c:out value="${ product_detail.shohin_mei }" /></td>
		</tr>
		<tr>
			<td>単価</td>
			<td><input type="hidden" name="tanka" value="${ product_detail.tanka }" />
				<c:out value="${ product_detail.tanka }" />円</td>
		</tr>
		<tr>
			<td>在庫</td>
			<td><c:out value="${ product_detail.zaiko }" />個</td>
		</tr>
		<tr>
			<td>希望数量</td>
			<td><c:choose><c:when test="${ product_detail.zaiko - oldprbean.kosu > 0 }">
				<select name="kosu">
				<c:forEach var="i" begin="1" end="${ product_detail.zaiko - oldprbean.kosu }" step="1">
				<option value="${ i }"><c:out value="${ i }" /></option>
				</c:forEach>
				</select>個
				</c:when><c:otherwise>これ以上注文できません。</c:otherwise></c:choose>
			</td>
		<tr>
			<td colspan="2">
				<c:choose><c:when test="${ product_detail.zaiko - oldprbean.kosu != 0 }">
				<input type="submit" value="ショッピングカートに入れる">
				</c:when></c:choose>
			</td>
		</tr>
		</table>
		<table>
		<tr>
			<td class="setsumei">商品説明</td>
		</tr>
		<tr>
			<td><input type="hidden" name="setsumei" value="${ product_detail.setsumei }" />
				<c:out value="${ product_detail.setsumei }" /></td>
		</tr>
		<tr>
			<td><a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">商品一覧に戻る</a></td>
		</tr>
	</table>
	<br><br>
	</form>
</body>
</html>