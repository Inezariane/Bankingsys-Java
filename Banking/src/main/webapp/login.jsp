<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 15/02/2024
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>

</head>
<body>
<div class="container">
    <h1>Login</h1>
    <form action="login" method="get">
        <label for="email">Email: </label>
        <input type="email" name="email" id="email"><br>
        <label for="password">Password: </label>
        <input type="password" name="password" id="password"><br>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
