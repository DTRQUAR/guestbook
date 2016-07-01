<%--
  Created by IntelliJ IDEA.
  User: Qouer
  Date: 30.06.2016
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

  <head>
    <title>Гостевая книга</title>
    <link rel="stylesheet" href="resources/css/style.css"/>
    <script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js'></script>
    <script src='resources/js/jquery.autosize.js'></script>
    <script>
      $("document").ready(function(){
        $('.textOfMessage').autosize();
      });
    </script>
  </head>

  <body>
    <div id="messagesArea">
      <c:forEach items="${allMessages}" var="message">
        <jsp:useBean id="message" type="test.guestbook.task.model.Message"/>
        <p class="nameOfMessage"><b>${message.name}</b></p>
        <textarea readonly disabled class="textOfMessage">${message.text}</textarea>
        <p class="dateOfMessage"><i>${message.formattedDate}</i>
          &nbsp;&nbsp<i>${message.formattedTime}</i></p></br>
      </c:forEach>
    </div>

    <div id="inputArea">
      <form id="messageForm" action="main" method="post">
        <label>Ваше имя:</label></br>
        <input placeholder="Введите ваше имя..." id="nameField" type="text" name="name" size="10"/></br></br>
        <label>Отзыв:</label></br>
        <textarea placeholder="Введите здесь ваш отзыв..." form="messageForm"
                  id="messageField" name="text" cols="102" rows="8" maxlength="1500"></textarea></br>
        <input id="sendButton" type="submit" value="Hello Nigger"/></br>
      </form>
    </div>

  </body>

</html>
