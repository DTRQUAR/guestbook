<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>

  <body>

  <%--Область Top: здесь выводится форма авторизации и кнопка регистрации--%>
    <div class="topArea">
      <sec:authorize access="isAuthenticated()">
        <table class="topTable">
          <tr>
            <td><h1>Гостевая книга </h1></td>
            <td class="loginFormAttribute">
              <form action="logout" method="post">
                <button type="submit">Выход</button>
              </form>
            </td>
          </tr>
        </table>
      </sec:authorize>

      <sec:authorize access="!isAuthenticated()">
        <table class="topTable">
          <tr>
            <td><h1>Гостевая книга</h1></td>
            <td class="loginFormAttribute">
              <form action="spring_security_check" method="post">
                <input type="text" placeholder="Email" class="authInputField" name='username'>
                <input type="password" placeholder="Password" class="authInputField" name='password'>
                <button type="submit">Вход</button>
              </form>
            </td>

            </td>
            <td class="loginFormAttribute" id="registerButton">
              <form action="register" method="get">
                <button type="submit">Регистрация</button>
              </form>
            </td>
          </tr>
        </table>
      </sec:authorize>
    </div>


  <%--Область сообщений: здесь выводятся все сообщения оставленные пользователями--%>
    <div class="messagesArea">
      <c:forEach items="${allMessages}" var="message">
        <jsp:useBean id="message" type="test.guestbook.task.to.MessageTo"/>
        <c:choose>
          <c:when test="${message.defaultName == null}">
            <textarea readonly disabled class="nameOfMessage">${message.user.name}</textarea>
          </c:when>
          <c:when test="${message.defaultName != null}">
            <textarea readonly disabled class="nameOfMessage">${message.defaultName}</textarea>
          </c:when>
        </c:choose>
        <textarea readonly disabled class="textOfMessage">${message.text}</textarea>

        <sec:authorize access="isAuthenticated()">
          <c:set var="authUser" scope="session" value="${authUser}"/>
          <c:choose>
            <c:when test="${message.getRateFromAuthUser(authUser).equals('0')}">
              <a class="rateButton" id="likeButton" href="main/rate?action=like&message=${message.id}">&nbsp&nbsp</a>${message.plus}&nbsp
              <a class="rateButton" id="notLikeButton"href="main/rate?action=notlike&message=${message.id}">&nbsp&nbsp</a>${message.minus}&nbsp
            </c:when>
            <c:when test="${message.getRateFromAuthUser(authUser).equals('1')}">
              <a class="rateButton" id="selectlikeButton" href="main/rate?action=like&message=${message.id}">&nbsp&nbsp</a>${message.plus}&nbsp
              <a class="rateButton" id="notLikeButton"href="main/rate?action=notlike&message=${message.id}">&nbsp&nbsp</a>${message.minus}&nbsp
            </c:when>
            <c:when test="${message.getRateFromAuthUser(authUser).equals('2')}">
              <a class="rateButton" id="likeButton" href="main/rate?action=like&message=${message.id}">&nbsp&nbsp</a>${message.plus}&nbsp
              <a class="rateButton" id="selectNotlikeButton"href="main/rate?action=notlike&message=${message.id}">&nbsp&nbsp</a>${message.minus}&nbsp
            </c:when>
          </c:choose>
        </sec:authorize>

        <div class="dateOfMessage"><i>${message.formattedDate}</i>
          &nbsp;&nbsp<i>${message.formattedTime}</i></div></br>
      </c:forEach>
    </div>

  <%--Область ввода нового сообщения--%>
    <div id="inputArea">
      <form id="messageForm" class="inputForm" action="main" method="post">

        <sec:authorize access="isAuthenticated()">
          <label>Ваше имя: <b>${authUser.name}</b></label></br></br>
        </sec:authorize>

        <sec:authorize access="!isAuthenticated()">
          <label>Ваше имя:</label></br>
          <input placeholder="Введите ваше имя..." id="nameField" class="inputField" type="text" name="name" maxlength="800"/></br></br>
        </sec:authorize>

        <label>Отзыв:</label></br>
        <textarea placeholder="Введите здесь ваш отзыв..." form="messageForm"
                  id="messageField" class="inputField" name="text" cols="102" rows="8" maxlength="2500"></textarea></br>
        <input id="sendButton" class="button" type="submit" value="Hello Nigger"/></br>
      </form>
    </div>

  </body>

</html>
