<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
</head>
<body>
<c:set var="Login_mei" value="${Login}" />
<p><a href="/shop/LogoutController?Login_id=${ Login_mei }">ログアウト</a></p>
<c:out value="${ Login_mei }" />さん
<h2>注文履歴</h2>
<a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">商品一覧に戻る</a>
	<table border="1">
		<tr>
			<th>注文ID</th>
			<th>画像</th>
			<th>商品名</th>
			<th>単価</th>
			<th>数量</th>
			<th>注文日</th>
		</tr>
			<c:forEach var="chumonlistitem" items="${ chumonlist }">
				<tr>
					<td><input type="hidden" name="chumon_id" value="${ chumonlistitem.chumon_id }" />
						<c:out value="${ chumonlistitem.chumon_id }" /></td>
					<td><img src="/shop/img/${ chumonlistitem.shashin }" name="shashin" width="50" height="50"/></td>
					<td><input type="hidden" name="shohin_mei" value="${ chumonlistitem.shohin_mei }" />
						<c:out value="${ chumonlistitem.shohin_mei }" /></td>
					<td><input type="hidden" name="tanka" value="${ chumonlistitem.tanka }" />
						<c:out value="${ chumonlistitem.tanka }" />円</td>
					<td><input type="hidden" name="zaiko" value="${ chumonlistitem.zaiko }" />
						<c:out value="${ chumonlistitem.zaiko }" />個</td>
					<td><input type="hidden" name="chumon_bi" value="${ chumonlistitem.chumon_bi }" />
						<c:out value="${ chumonlistitem.chumon_bi }" /></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>