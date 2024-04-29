<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
<link rel="stylesheet" href="../css/style2.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" />
</head>
<body>
<c:set var="Login_mei" value="${Login}" />
	<div class="parent">
		<p class="title"><a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">ショッピングセンター</a></p>
	</div>
ようこそ、<c:out value="${ Login_mei }" />さん
<form action="/shop/RegisterItemDoneController" method="POST" enctype="multipart/form-data">
<table>
	<tr>
		<th>♦登録事項♦</th>
		<th></th>
	</tr>
	<tr>
		<td style="text-align:right">商品名</td>
		<td><input type="text" name="shohin_mei">
			<font color="red"><c:out value="${ msgshohin_mei1 }" /><c:out value="${ msgshohin_mei2 }" /></font></td>
	</tr>
	<tr>
		<td></td>
		<td>※60文字以内での入力をお願いします。</td>
	</tr>
	<tr>
		<td style="text-align:right">単価</td>
		<td><input type="text" name="tanka">
			<font color="red"><c:out value="${ msgtanka1 }" /><c:out value="${ msgtanka2 }" /></font></td>
	</tr>
	<tr>
		<td></td>
		<td>※半角数字9桁以内での入力をお願いします。</td>
	</tr>
	<tr>
		<td style="text-align:right">在庫数</td>
		<td><input type="text" name="zaiko">
			<font color="red"><c:out value="${ msgzaiko1 }" /><c:out value="${ msgzaiko2 }" /></font></td>
	</tr>
	<tr>
		<td></td>
		<td>※半角数字3桁以内での入力をお願いします。</td>
	</tr>
	<tr>
		<td style="text-align:right">登録画像名</td>
		<td><input type="file" name="pict">
			<font color="red"><c:out value="${ msgshashin1 }" /><c:out value="${ msgshashin2 }" /></font></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td style="text-align:right">商品説明</td>
		<td><textarea name="setsumei"></textarea></td>
	</tr>
</table>
♦以上の内容でお間違いなければ登録ボタンを押してください。<br>
<input type="reset" value="リセット">	<input type="submit" value="登録"><br>
<a href="/shop/Login_ProductsListController?Login_id=${Login_mei}&pagecount=${1}&sort_number=${1}">商品一覧に戻る</a>
</form>
</body>
</html>