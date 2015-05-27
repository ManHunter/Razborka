<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.05.2015
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
status:<%=request.getAttribute("javax.servlet.error.status_code") %>,
reason:<%=request.getAttribute("javax.servlet.error.message") %>
</body>
</html>
