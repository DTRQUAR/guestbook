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
        <h1>Гостевая книга | Регистрация</h1><br>
        <form action="register" method="post" id="registerForm">
            <div class="registerArea">
                <label>Введите ваш email:</label>
                <input type="email"   pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" title="Введи корректный email" required
                       placeholder="Email" class="registerInputField form-control" name='email' id="registerEmailField"
                       value="${email}">
                <br>
                <label>Введите ваш пароль:</label>
                <input type="password" placeholder="Password" class="registerInputField form-control"
                       name='password' maxlength="100" id="registerPasswordField" required
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}"
                       title="Не менее пяти символов, содержащих хотя бы одну цифру и символы из верхнего и нижнего регистра"
                       value="${password}">
                <br>
                <label>Введите ваше имя:</label>
                <input type="text" placeholder="Your name" class="registerInputField form-control"
                       name='name' maxlength="800" id="registerNameField" required
                       title="Не менее одного символа"
                       value="${name}"><br>

            </div>
            <button type="submit" class="btn btn-info" id="submitRegisterButton" >Зарегистрироваться</button>
        </form>
    </div>

</body>

    <script type="text/javascript" src="resources/js/check_input_fields.js"></script>
</html>
