<%--
  Created by IntelliJ IDEA.
  User: Qouer
  Date: 03.07.2016
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Регистрация</title>
</head>
<body>
    <h1>Гостевая книга | Регистрация</h1>
    <form action="register" method="post">
        <input type="text" placeholder="Email" class="authInputField" name='email'>
        <input type="password" placeholder="Password" class="authInputField" name='password'>
        <input type="password" placeholder="Password" class="authInputField" name='name'>
        <button type="submit">Зарегистрироваться</button>
    </form>
</body>
</html>
