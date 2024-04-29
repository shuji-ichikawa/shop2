<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" href="./css/style2.css" type="text/css" />
<style>
#cart {
 text-align: right;
}
</style>
</head>
<body>
<c:set var="Login_mei" value="${Login}" />
<c:set var="sort_number" value="${sort_number}" />
	<div class="parent">
		<p class="title">ショッピングセンター</p>
		<div id="cart"><a href="/shop/ProductDetails_CartListController?Login_id=${Login_mei}"><img src="./img/images.png" alt="Parrots" width="30" height="30"></a>　　<a href="/shop/jsp/Register_Item.jsp?Login_id=${Login_mei}"><img src="./img/shouhintouroku.png" alt="Parrots" width="30" height="30"></a>　　<a href="/shop/CartList_ThanksController?Login_id=${Login_mei}"><img src="./img/rireki.png" alt="Parrots" width="30" height="30"></a>　　<a href="/shop/LogoutController?Login_id=${ Login_mei }"><img src="./img/logout.png" alt="Parrots" width="30" height="30"></a></div>
	</div>
ようこそ、<c:out value="${ Login_mei }" />さん
<h2>商品一覧</h2>
<%int sort_number = Integer.parseInt(request.getParameter("sort_number"));%>
<select onChange="location.href=value;">
	<option value="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}" <% if(sort_number==1) { %>selected<% ; } %>>新しいもの順</option>
	<option value="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${2}"<% if(sort_number==2) { %>selected<% ; } %>>古いもの順</option>
	<option value="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${3}"<% if(sort_number==3) { %>selected<% ; } %>>安いもの順</option>
	<option value="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${4}"<% if(sort_number==4) { %>selected<% ; } %>>高いもの順</option>
</select>
	<table border="1">
		<tr>
			<th>商品ID</th>
			<th>画像</th>
			<th>商品名</th>
			<th>単価</th>
			<th>在庫数</th>
			<th></th>
		</tr>
		<c:forEach var="product_listitem" items="${ product_sublist }">
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
				<td><a href="/shop/ProductsList_ProductDetailsController?shohin_id=${ product_listitem.shohin_id }&Login_id=${Login_mei}">詳細</a></td>
			</tr>
		</c:forEach>
	</table>

	<c:if test="${totalPageCount >= 1}">
		<table>
			<tr>
				<c:choose>
					<c:when test="${pagecount > 1}">
						<th><a href ="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${pagecount - 1}&sort_number=${sort_number}">前へ</a></th>
					</c:when>
					<c:when test="${pagecount == 1}">
						<th>前へ</th>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${pagecount > 1}">
						<th><a href ="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${sort_number}">1</a></th>
					</c:when>
					<c:when test="${pagecount == 1}">
						<th>1</th>
					</c:when>
				</c:choose>
				<c:if test="${pageFrom > 2}">
					<th>…</th>
				</c:if>
				<c:forEach begin="${pageFrom}" end="${pageTo}" step="1" var="i">
						<c:choose>
							<c:when test="${pagecount != i}">
								<th><a href ="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${i}&sort_number=${sort_number}">
								<input type="hidden" name="pageFrom" value="${i}" />
								<c:out value="${i}" /></a></th>
							</c:when>
							<c:when test="${pagecount == i}">
								<th><input type="hidden" name="pageFrom" value="${i}" />
								<c:out value="${i}" /></th>
							</c:when>
						</c:choose>
				</c:forEach>
				<c:if test="${totalPageCount - 1 > pageTo}">
					<th>…</th>
				</c:if>
				<c:choose>
					<c:when test="${totalPageCount > pagecount}">
						<th><a href ="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${totalPageCount}&sort_number=${sort_number}">
						<input type="hidden" name="pageFrom" value="${totalPageCount}" />
						<c:out value="${totalPageCount}" /></a></th>
					</c:when>
					<c:when test="${totalPageCount == pagecount}">
						<th><input type="hidden" name="pageFrom" value="${totalPageCount}" />
						<c:out value="${totalPageCount}" /></th>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${totalPageCount > pagecount}">
						<th><a href ="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${pagecount + 1}&sort_number=${sort_number}">次へ</a></th>
					</c:when>
					<c:when test="${pagecount == totalPageCount}">
						<th>次へ</th>
					</c:when>
				</c:choose>
			</tr>
		</table>
	</c:if>
	</body>
</html>