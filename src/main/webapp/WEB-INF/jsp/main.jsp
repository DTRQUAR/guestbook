<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>

  <body>
    <div class="topArea">
      <sec:authorize access="isAuthenticated()">
        <table class="topTable">
          <tr>
            <td><h1>Гостевая книга </h1></td>
            <td class="loginFormAttribute"><jsp:include page="/WEB-INF/jsp/logout.jsp"/></td>
          </tr>
        </table>
      </sec:authorize>

      <sec:authorize access="!isAuthenticated()">
        <table class="topTable">
          <tr>
            <td><h1>Гостевая книга</h1></td>
            <td class="loginFormAttribute"><jsp:include page="/WEB-INF/jsp/login.jsp"/>

            </td>
            <td class="loginFormAttribute" id="registerButton">
              <form action="register" method="post">
                <button type="submit">Регистрация</button>
              </form>
            </td>
          </tr>
        </table>
      </sec:authorize>
    </div>
    <div class="messagesArea">

      <c:forEach items="${allMessages}" var="message">
        <jsp:useBean id="message" type="test.guestbook.task.model.Message"/>
        <c:choose>
          <c:when test="${message.defaultName == null}">
            <textarea readonly disabled class="nameOfMessage">${message.user.name}</textarea>
          </c:when>
          <c:when test="${message.defaultName != null}">
            <textarea readonly disabled class="nameOfMessage">${message.defaultName}</textarea>
          </c:when>
        </c:choose>
        <textarea readonly disabled class="textOfMessage">${message.text}</textarea>
        <p class="dateOfMessage"><i>${message.formattedDate}</i>
          &nbsp;&nbsp<i>${message.formattedTime}</i></p></br>
      </c:forEach>
    </div>

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
