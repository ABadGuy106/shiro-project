<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<h1>注册</h1>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    用户名：<input type="text" name="userName"/><br>
    密 码：<input type="password" name="password">
    <input type="submit" value="立即注册">
</form>
</body>
</html>