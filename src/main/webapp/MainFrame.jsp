<%-- Date: 31.03.2016 --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<link rel="stylesheet" type="text/css" href="css/style.css">
<html>
<head>
    <title>Відділи</title>
</head>

<div align="center">
<form action="<c:url value="/main"/>" method="POST">
  <table>
    <tr>
      <%--<td>Год:<input type="text" name="year" value="${form.year}"/><br/></td>--%>
      <td>Відділ:
        <select name="departmentId">
          <c:forEach var="department" items="${form.departments}">
            <c:choose>
              <c:when test="${department.departmentId==form.departmentId}">
                <option value="${department.departmentId}" selected><c:out value="${department.nameDepartment}"/></option>
              </c:when>
              <c:otherwise>
                <option value="${department.departmentId}"><c:out value="${department.nameDepartment}"/></option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </td>
      <td><input type="submit" name="getList" value="Обновить"/></td>
    </tr>
  </table>

  <table>
    <td></td><td></td>
  </table>

  <p/>
  <b>Список особового складу</b>
  <br/>
  <table>
    <tr>
      <th>№ <br>з/п</th>
      <th>вiйськове <br>звання</th>
      <th>ФІО</th>
      <th>посада</th>
      <th>рiк народження</th>

    </tr>

    <%int i = 1;%>
    <c:forEach var="person"  items="${form.persons}">
      <tr>
        <td><input type="checkbox" name="personId" value="${person.personId}"><%out.println(i);i++;%></td>
        <td><c:out value="${person.rank}"/></td>
        <td><c:out value="${person.surName} ${person.firstName} ${person.patronymic}"/></td>
        <td><c:out value="${person.position}"/></td>
        <td><c:out value="${person.btoS()}"/></td>
      </tr>
    </c:forEach>
  </table>

  <table>
    <tr>
      <td><input type="submit" value="Add" name="Add"/></td>
      <td><input type="submit" value="Edit" name="Edit"/></td>
      <td><input type="submit" value="Delete" name="Delete"/></td>
    </tr>
  </table>

  <%--<p/>--%>
  <%--<b>Перемістити особовий склад до відділу</b>--%>
  <%--<br/>--%>
  <%--<table>--%>
    <%--<tr>--%>
      <%--&lt;%&ndash;<td>Рік:<input type="text" name="newYear" value="${form.year}"/><br/></td>&ndash;%&gt;--%>
      <%--<td>Список відділів:--%>
        <%--<select name="newDepartmentId">--%>
          <%--<c:forEach var="department" items="${form.departments}">--%>
            <%--<option value="${department.departmentId}"><c:out value="${department.nameDepartment}"/></option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
      <%--</td>--%>
      <%--<td><input type="submit" name="MoveGroup" value="Перемістити"/></td>--%>
    <%--</tr>--%>
  <%--</table>--%>
</form>
</div>
</body>
</html>
