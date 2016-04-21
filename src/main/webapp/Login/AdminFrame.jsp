<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2016
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<div align="center">
  <h2>Admin Page</h2>
  <form action="<c:url value="/admin"/>" method="POST">

    <br/><input type="submit" name="Exit" value="Logout">
  </form>
</div>
</body>
</html>
