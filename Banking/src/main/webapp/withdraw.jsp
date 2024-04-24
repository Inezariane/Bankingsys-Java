<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 15/02/2024
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Withdrawal</title>

</head>
<body>
<div class="container">
  <h1>Withdraw money</h1>
  <form action="banking" method="post">

    <input type="hidden" name="action" value="withdraw">
    <label for="email">Email: </label>
    <input type="email" name="email" placeholder="Enter Email" id="email">
    <label for="password">Password: </label>
    <input type="password" name="password" placeholder="Enter Password" id="password">
    <label for="amount">Amount </label>
    <input type="text" name="amount" placeholder="Enter amount" id="amount">
    <button type="submit">Withdraw</button>
  </form>
</div>
</body>
</html>
