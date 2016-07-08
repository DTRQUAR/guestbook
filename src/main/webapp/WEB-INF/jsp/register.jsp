<%--
  Created by IntelliJ IDEA.
  User: Qouer
  Date: 03.07.2016
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <jsp:include page="fragments/headTag.jsp"/>
<body>
    <div class="registerForm">
        <h1>Гостевая книга | Пользователь</h1><br>
        <form action="register" method="post" id="registerForm">
            <div class="registerArea">
                <sec:authorize access="isAuthenticated()">
                    <input type="text" name="id" value="${authUser.id}" hidden>
                    <label>Введите ваш email:</label>
                    <input type="email"   pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" title="Введи корректный email" required
                           placeholder="Email" class="form-control" name='email' id="registerEmailField"
                           value="${authUser.email}">
                    <br>
                    <label>Введите ваш пароль:</label>
                    <input type="password" placeholder="Password" class="form-control"
                           name='password' maxlength="100" id="registerPasswordField" required
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}"
                           title="Не менее пяти символов, содержащих хотя бы одну цифру и символы из верхнего и нижнего регистра"
                           value="${authUser.password}">
                    <br>
                    <label>Введите ваше имя:</label>
                    <input type="text" placeholder="Your name" class="form-control"
                           name='name' maxlength="800" id="registerNameField" required
                           title="Не менее одного символа"
                           value="${authUser.name}">
                    <br>
                    Получать уведомления о новых сообщениях на email &nbsp&nbsp
                    <c:if test="${authUser.emailNotif == false}">
                        <input type="checkbox" name="isEmailNotif">
                    </c:if>
                    <c:if test="${authUser.emailNotif == true}">
                        <input type="checkbox" name="isEmailNotif" value="true" checked>
                    </c:if>
                    <br><br>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <input type="text" name="id" hidden>
                    <label>Введите ваш email:</label>
                    <input type="email"   pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" title="Введи корректный email" required
                           placeholder="Email" class="form-control" name='email' id="registerEmailField"
                           value="${email}">
                    <br>
                    <label>Введите ваш пароль:</label>
                    <input type="password" placeholder="Password" class="form-control"
                           name='password' maxlength="100" id="registerPasswordField" required
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}"
                           title="Не менее пяти символов, содержащих хотя бы одну цифру и символы из верхнего и нижнего регистра"
                           value="${password}">
                    <br>
                    <label>Введите ваше имя:</label>
                    <input type="text" placeholder="Your name" class="form-control"
                           name='name' maxlength="800" id="registerNameField" required
                           title="Не менее одного символа"
                           value="${name}">
                    <br>
                    Получать уведомления о новых сообщениях на email &nbsp&nbsp
                    <c:if test="${isEmailNotif  != true}">
                        <input type="checkbox" name="isEmailNotif">
                    </c:if>
                    <c:if test="${isEmailNotif == true}">
                        <input type="checkbox" name="isEmailNotif" checked>
                    </c:if>
                    <br><br>
                </sec:authorize>
            </div>
            <button type="submit" class="btn btn-info" id="saveUserButton">Сохранить</button>
        </form>
    </div>

</body>

    <script type="text/javascript" src="resources/js/check_input_fields.js"></script>
</html>
