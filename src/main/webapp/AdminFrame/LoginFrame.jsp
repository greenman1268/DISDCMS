<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2016
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>
<div align="center">
  <h2>Please LogIn</h2>
  <form action="<c:url value="/login"/>" method="POST">
    <br/>Username:<input type="text" name="username">
    <br/>Password:<input type="password" name="password">
    <br/><input type="submit" value="Submit">
  </form>
</div>
</body>
</html>
