<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン完了</title>
</head>
<body>
<br><br>
<h2>ようこそ、<c:set var="Login_mei" value="${Login}" />
<c:out value="${ Login_mei }" />さん<br>
ログインが完了しました。<br></h2>
<br>
<table>
<tr>
<%--	<td><h2><a href ="/shop/jsp/ProductsList.jsp?Login_id=${Login_mei}&pagecount=${'1'}">商品一覧画面へ</a></h2></td>--%>
	<td><h2><a href ="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">商品一覧画面へ</a></h2></td>
</tr>
</table>
</body>
</html>