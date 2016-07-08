<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>

  <body>

  <%--Область Top: форма авторизации, либо кнопку "Выход"--%>
    <div class="topArea">
      <sec:authorize access="isAuthenticated()">
        <table class="topTable">
          <tr>
            <td><div class="title">Гостевая книга</div></td>
            <td id="profileButtonTd">
              <form action="register" method="get" class="form-inline">
                <button type="submit" class="btn btn-primary" id="profileButton">Мой профиль</button>
              </form>
            </td>
            <td id="logoutButtonTd">
              <form action="logout" method="post" >
                <button type="submit" class="btn btn-danger" id="logoutButton">Выход</button>
              </form>
            </td>
          </tr>
        </table>
      </sec:authorize>

      <sec:authorize access="!isAuthenticated()">
        <table class="topTable">
          <tr>
            <td><div class="title">Гостевая книга</div></td>
            <td>
              <c:if test="${auth_error == true}">
                  <form action="spring_security_check" method="post" class="login-form form-inline">
                    <div class="form-group has-error">
                      <input type="text" placeholder="Email"
                           class="authInputField form-control" name='username' id="emailInput" size="10">
                    </div>
                    <div class="form-group has-error">
                      <input type="password" placeholder="Password"
                           class="authInputField form-control" name='password' id="passwordInput" size="10">
                    </div>
                      <button type="submit" class="btn btn-success" id="loginButton">Вход</button>
                  </form>
              </c:if>
              <c:if test="${auth_error == null}">
                <form action="spring_security_check" method="post" class="login-form form-inline">
                  <div class="form-group">
                    <input type="text" placeholder="Email"
                           class="authInputField form-control" name='username' id="emailInput" size="10">
                  </div>
                  <div class="form-group">
                    <input type="password" placeholder="Password"
                           class="authInputField form-control" name='password' id="passwordInput" size="10">
                  </div>
                  <button type="submit" class="btn btn-success" id="loginButton">Вход</button>
                </form>
              </c:if>
            </td>

            </td>
            <td id="registerButtonTd">
              <form action="register" method="get">
                <button type="submit" class="btn btn-warning">Регистрация</button>
              </form>
            </td>
          </tr>
        </table>
      </sec:authorize>
    </div>

    <%--Область сообщений--%>
    <div class="messagesArea" id="messageAreaId">
    </div>

    <%--Область ввода нового сообщения--%>
    <div id="inputArea">
      <form id="messageForm" method="post">
        <%--Область отображения или ввода имения--%>
        <div id="inputNameStatusBox" class="form-group has-success">
          <sec:authorize access="isAuthenticated()">
            <label>Ваше имя: <b>${authUser.name}</b></label>
          </sec:authorize>

          <sec:authorize access="!isAuthenticated()">
            <label>Ваше имя:</label></br>
            <input placeholder="Введите ваше имя..." id="nameField" class="inputField form-control"
                   type="text" name="name" maxlength="800"/>
          </sec:authorize>
        </div>

        <%--Область ввода текста сообщения--%>
        <div id="inputTextStatusBox" class="form-group has-success">
          <label>Отзыв:</label></br>
          <textarea placeholder="Введите здесь ваш отзыв..." form="messageForm"
                    id="messageField" class="inputField form-control" name="text" cols="102" rows="6" maxlength="2500"></textarea>
          <input id="sendButton" class="button btn btn-primary"
                 type="button" value="Отправить"/>
        </div>
      </form>
    </div>

  </body>

  <script type="text/javascript" src="resources/js/check_input_fields.js"></script>
  <script type="text/javascript" src="resources/js/ajax.js"></script>


</html>
