<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2016
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <title>Login</title>
</head>
<body>


    <div class="container">
      <div class="login">
        <h1>Login</h1>
        <form action="<c:url value="/login"/>" method="POST">
          <p><input type="text" name="username" value="" placeholder="username"></p>
          <p><input type="password" name="password" value="" placeholder="Password"></p>

          <p> <label class="submit"><input type="submit" name="Submit" value="Submit"></label>&nbsp;&nbsp;&nbsp;&nbsp;<label ><input type="submit" value="Cancel" name="Cancel"></label></p>
        </form>
      </div>

      <script>
        var k = '${error}'
        if(k)alert("Username or password is invalid")
      </script>

</div>

</body>
</html>