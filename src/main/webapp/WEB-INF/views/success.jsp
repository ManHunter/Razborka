<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.04.2015
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%@include file="layout/include.jsp"%>
</head>
<body>
<c:forEach items="${listPhotos}" var="photo">
    <img src="${pageContext.request.contextPath}/images/${photo}" width="120" height="100">
</c:forEach>
</body>
</html>
