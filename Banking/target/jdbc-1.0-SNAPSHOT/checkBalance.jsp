<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 16/02/2024
  Time: 07:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Balance</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
  <form action="banking" method="post">

    <input type="hidden" name="action" value="checkBalance">
    <label for="email">Email: </label>
    <input type="email" name="email" placeholder="Enter Email" id="email">
    <label for="password">Password: </label>
    <input type="password" name="password" placeholder="Enter Password" id="password">
    <button type="submit">Check Balance</button>
  </form>
</div>
</body>
</html>
