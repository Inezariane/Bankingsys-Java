<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 15/02/2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
  <h1>Deposit money</h1>
  <form action="banking" method="post">

    <input type="hidden" name="action" value="deposit">
    <label for="email">Email: </label>
    <input type="email" name="email" placeholder="Enter Email" id="email">
    <label for="password">Password: </label>
    <input type="password" name="password" placeholder="Enter Password" id="password">
    <label for="amount">Amount Needed</label>
    <input type="text" name="amount" placeholder="Enter amount" id="amount">
    <button type="submit">Deposit</button>
  </form>
</div>
</body>
</html>
