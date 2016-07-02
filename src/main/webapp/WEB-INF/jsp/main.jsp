<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

  <head>
    <meta charset="utf-8"/>
    <title>Гостевая книга</title>
    <link rel="stylesheet" href="resources/css/style.css"/>
    <script src="resources/js/jquery.min.js"></script>
    <script src='resources/js/jquery.autosize.js'></script>
    <script src="resources/js/check_input_fields.js"></script>
  </head>

  <body>

    <div class="messagesArea">

      <table id="topTable">
        <tr>
          <td><h1>Гостевая книга</h1></td>
          <td id="loginFormAttribute"><jsp:include page="login.jsp"/></td>
        </tr>
      </table>

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
        <label>Ваше имя:</label></br>
        <input placeholder="Введите ваше имя..." id="nameField" class="inputField" type="text" name="name" maxlength="50"/></br></br>
        <label>Отзыв:</label></br>

        <textarea placeholder="Введите здесь ваш отзыв..." form="messageForm"
                  id="messageField" class="inputField" name="text" cols="102" rows="8" maxlength="1500"></textarea></br>
        <input id="sendButton" class="button" type="submit" value="Hello Nigger"/></br>
      </form>
    </div>


  </body>

</html>
