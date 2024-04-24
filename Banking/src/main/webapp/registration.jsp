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
</head>
<body>
<div class="container">
    <h1 class="header">Sign up </h1>
    <span>Create a secure bank account with Awesome Bank.</span>
    <form action="register" method="post">

        <label for="username">UserName:</label>
        <input type="text" placeholder="Your name" id="username" name="username"><br>
        <label for="email">Email :</label>
        <input type="email" placeholder="Your email" name="email" id="email"><br>
        <label for="tel">Phone :</label>
        <input type="tel" placeholder="Your number" name="phone" id="tel"><br>
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
