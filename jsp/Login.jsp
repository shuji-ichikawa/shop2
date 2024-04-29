<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="./css/style2.css" type="text/css" />
</head>
<body>
<h1>ショッピングセンター</h1>
<h2>ログイン</h2>
<br><br>
<form action="/shop/Login_ProductsListController" method="post">
<table>
<tr><td>ユーザーID	</td></tr>
<tr><td><input id="pi" type="text" name="ki" style="width: 460px; height: 30px;">
	<font color="red"><c:out value="${ msgki1 }" /><c:out value="${ msgki2 }" /></font></td>
</tr>
<tr><td>パスワード</td></tr>
<tr>
	<td><input id="pi" type="password" name="ps" style="width: 460px; height: 30px;">
	<font color="red"><c:out value="${ msgps1 }" /><c:out value="${ msgps2 }" /></font></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
	<td><input id="pk" type="submit" value="ログイン"><br></td>
</tr>
<tr><td></td>
<tr><td><a href="/shop/jsp/RegisterCustomer.jsp" ><br>新規登録はこちら</a></td></tr>
</table>
</form>
<br><br>
</body>
</html>