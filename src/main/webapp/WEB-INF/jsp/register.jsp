<%--
  Created by IntelliJ IDEA.
  User: Qouer
  Date: 03.07.2016
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <jsp:include page="fragments/headTag.jsp"/>
<body>
    <div class="registerForm">
        <h1>Гостевая книга | Регистрация</h1>
        <form action="register" method="post">
            <input type="text" placeholder="Email" class="authInputField" name='email'><br>
            <input type="password" placeholder="Password" class="authInputField" name='password'><br>
            <input type="text" placeholder="Your name" class="authInputField" name='name' maxlength="800"><br>
            <button type="submit">Зарегистрироваться</button>
        </form>
    </div>

</body>
</html>
