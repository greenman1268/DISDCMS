<%-- Date: 31.03.2016 --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="servlet.forms.MainFrameForm" %>
<%@ page import="logic.ManagementSystem" %>
<%@ page import="java.sql.SQLException" %>
<link rel="stylesheet" type="text/css" href="css/style.css">
<html>
<head>
    <title>Відділи</title>
</head>

<div align="center">
<form action="<c:url value="/main"/>" method="POST">
  <table>
    <tr>
<%String s = "";%>
      <td>Відділ:
        <select name="departmentId">
          <c:forEach var="department" items="${form.departments}">
            <c:choose>
              <c:when test="${department.departmentId==form.departmentId}">
                <option value="${department.departmentId}" selected><c:out value="${department.nameDepartment}" /></option>
                <% s = request.getParameter("departmentId");%>
              </c:when>
              <c:otherwise>
                <option value="${department.departmentId}"><c:out value="${department.nameDepartment}"/></option>
                <% s = request.getParameter("departmentId");%>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </td>
      <td><input type="submit" name="getList" value="Оновити"/></td>
    </tr>
    <tr><td>Начальник</td><td>
      <%
      try {
       if(s != null) out.print(ManagementSystem.getDepartmentById(Integer.parseInt(s)).getChief());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    %>
    </td></tr>
    <tr><td>Кiлькiсть людей</td><td>
      <%
      try {
       if(s != null) out.print(ManagementSystem.getDepartmentById(Integer.parseInt(s)).getAmount_people());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    %>
    </td></tr>
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
      <td>
        <select name="newDepartmentId">
          <c:forEach var="department" items="${form.departments}">
            <option value="${department.departmentId}"><c:out value="${department.nameDepartment}"/></option>
          </c:forEach>
        </select>
      </td>
      <td><input type="submit" name="MoveGroup" value="Перемістити"/></td>
      <td><input type="submit" value="Добавити" name="Add"/></td>
      <td><input type="submit" value="Змiнити" name="Edit"/></td>
      <td><input type="submit" value="Видалити" name="Delete"/></td>
    </tr>
    </table>
  </table>
</form>
</div>
</body>
</html>
