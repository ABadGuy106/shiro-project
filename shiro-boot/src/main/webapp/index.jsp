<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>系统主页</h1>
<a href="${pageContext.request.contextPath}/user/logout">退出用户</a>

<ul>
    <shiro:hasAnyRoles name="user,admin">
        <li><a href="">用户管理</a>
            <ul>
                <shiro:hasPermission name="user:add:*">
                    <li>添加</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:del:*">
                    <li>删除</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update:*">
                    <li>修改</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="product:select:*">
                    <li>查询</li>
                </shiro:hasPermission>
            </ul>
        </li>
    </shiro:hasAnyRoles>
        <shiro:hasRole name="admin">
            <li>商品管理</li>
            <ul>
                <shiro:hasPermission name="product:add:*">
                    <li>添加</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="product:del:*">
                    <li>删除</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="product:update:*">
                    <li>修改</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="product:select:*">
                    <li>查询</li>
                </shiro:hasPermission>
            </ul>

            <li>订单管理</li>
            <li>物流管理</li>
        </shiro:hasRole>

</ul>

</body>
</html>