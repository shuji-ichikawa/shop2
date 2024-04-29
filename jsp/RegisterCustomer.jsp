<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<form action="/shop/RegisterCustomer_DoneController" method="POST">
<table>
<tr><td><h1>登録事項</h1></td></tr>
</table>
<br>
<h2>下記の登録をお願いします!!</h2>
<table>
<tr><td>名前</td></tr>
<tr><td><input type="text" name="km" placeholder="例：山田太郎" style="width: 460px; height: 30px;">
		<font color="red"><c:out value="${ msgkm1 }" /><c:out value="${ msgkm2 }" /></font></td></tr>
<tr><td>ユーザーＩＤ(半角英数字8文字以内で入力をお願いします。)</td></tr>
<tr><td><input type="text" name="ki" placeholder="例：polytech01" style="width: 460px; height: 30px;">
		<font color="red"><c:out value="${ msgki1 }" /><c:out value="${ msgki2 }" /><c:out value="${ msgki3 }" /></font></td></tr>
<tr><td>パスワード(半角英数字16文字以内で入力をお願いします。)</td></tr>
<tr><td><input type="password"  name="ps"  placeholder="例：password" style="width: 460px; height: 30px;">
		<font color="red"><c:out value="${ msgps1 }" /><c:out value="${ msgps2 }" /></font></td></tr>
<tr><td>郵便番号("-"を入れず半角数字7文字以内で入力をお願いします。)</td></tr>
<tr><td><input type="text" name="bin" placeholder="例：1234567" style="width: 460px; height: 30px;">
		<font color="red"><c:out value="${ msgbin1 }" /><c:out value="${ msgbin2 }" /></font></td></tr>
<tr><td>住所</td></tr>
<tr><td><input type="text" name="jyu" placeholder="例：東京都千代田区永田町1-10-1" style="width: 460px; height: 30px;">
		<font color="red"><c:out value="${ msgjyu1 }" /><c:out value="${ msgjyu2 }" /></font></td></tr>
<tr><td>電話番号("-"を入れず半角数字11文字以内で入力をお願いします。)</td></tr>
<tr><td><input type="text" name="den" placeholder="例：0538278765" style="width: 460px; height: 30px;">
		<font color="red"><c:out value="${ msgden1 }" /><c:out value="${ msgden2 }" /></font></td></tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
<tr><td><input class="clear" type="reset" value="リセット"></td>
<td><input class="sends" type="submit" value=" 送信 "></td>
</tr>
<tr><td>
<a href="/shop/jsp/Login.jsp"><br>ログイン画面に戻る</a>
</td></tr>
</table>
</form>
<script type="text/javascript">
function checkForm($this)
{
    var str=$this.value;
    while(str.match(/[^0-9]+/i))
    {
        str=str.replace(/[^0-9]+/i,"");
        alert("半角数字を入力してください") ;
    }
    $this.value=str;
}
</script>

<script type="text/javascript">
function checkForm1($this)
{
    var str=$this.value;
    while(str.match(/[^A-Z^a-z\d\-]/))
    {
        str=str.replace(/[^A-Z^a-z\d\-]/,"");
        alert("半角英数字を入力してください") ;
    }
    $this.value=str;
}
</script>

</body>
</html>