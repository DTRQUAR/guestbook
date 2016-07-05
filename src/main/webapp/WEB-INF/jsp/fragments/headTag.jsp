<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <meta charset=UTF-8">


    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <%--<p>${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}</p>--%>
    <title>Гостевая книга</title>
    <link rel="stylesheet" href="resources/css/style.css"/>
    <script src="resources/js/jquery-3.0.0.min.js"></script>
    <script src='resources/js/jquery.autosize.js'></script>
    <script src="resources/js/check_input_fields.js"></script>
</head>