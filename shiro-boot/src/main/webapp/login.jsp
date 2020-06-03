<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名：<input type="text" name="username"/><br>
    密 码：<input type="password" name="password"><br>
    请输入验证码： <input type="text"><img src="" alt=""><br>
    <input type="submit" value="登录">
</form>
</body>
</html>