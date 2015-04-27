<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.04.2015
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form:form method="post" commandName="photos" action="image" enctype="multipart/form-data">
    <%--<form:errors path="*" />--%>
    <br/>
    photo 1 <input type="file" name="files[0]">
    <br/>
    photo 2 <input type="file" name="files[1]">
    <br/>
    photo 3 <input type="file" name="files[2]">
    <input type="submit" value="Upload">
</form:form>
</body>
</html>
