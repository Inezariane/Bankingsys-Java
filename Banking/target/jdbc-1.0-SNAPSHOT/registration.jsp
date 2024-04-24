<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 15/02/2024
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h1 class="header">Sign up </h1>
    <span>Create a better Future with a safe bank account.</span>
    <form action="register" method="post">

        <label for="username">UserName:</label>
        <input type="text" placeholder="Daniella" id="username" name="username"><br>
        <label for="email">Email :</label>
        <input type="email" placeholder="daniellaganza30@gmail.com" name="email" id="email"><br>
        <label for="tel">Phone :</label>
        <input type="tel" placeholder="0788......" name="phone" id="tel"><br>
        <label for="password">Password</label>
        <input type="password" name="password" id="password"><br>
        <label for="accountType"> Account Type: </label>
        <input type="radio" value="savingAccount" name="account_type" id="accountType"><span>Saving Account</span>
        <input type="radio" value="creditAccount" name="account_type" id="accountType"><span>Current Account</span><br>
        <label for="amount">Initial Deposit: </label>
        <input type="number" id="amount" name="amount"><br>
        <label for="dob">Date of Birth</label>
        <input type="date" name="dob" id="dob"><br>

        <button type="submit">Register</button>
    </form>
</div>
</body>
</html>
