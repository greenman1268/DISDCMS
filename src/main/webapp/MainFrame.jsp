<%-- Date: 31.03.2016 --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Відділ програмування</title>
</head>

<body>
<form action="<c:url value="/main"/>" method="POST">
  <table>
    <tr>
      <%--<td>Год:<input type="text" name="year" value="${form.year}"/><br/></td>--%>
      <td>Список особового складу!:
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

  <p/>
  <b>Список особового складу для обраних параметрів:</b>
  <br/>
  <table>
    <tr>
      <th> </th>
      <th>Фамілія</th>
      <th>Ім'я</th>
      <th>По батькові</th>
    </tr>
    <c:forEach var="person" items="${form.persons}">
      <tr>
        <td><input type="radio" name="personId" value="${person.personId}"></td>
        <td><c:out value="${person.surName}"/></td>
        <td><c:out value="${person.firstName}"/></td>
        <td><c:out value="${person.patronymic}"/></td>
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

  <p/>
  <b>Перемістити особовий склад до відділу</b>
  <br/>
  <table>
    <tr>
      <%--<td>Рік:<input type="text" name="newYear" value="${form.year}"/><br/></td>--%>
      <td>Список відділів:
        <select name="newDepartmentId">
          <c:forEach var="department" items="${form.departments}">
            <option value="${department.departmentId}"><c:out value="${department.nameDepartment}"/></option>
          </c:forEach>
        </select>
      </td>
      <td><input type="submit" name="MoveGroup" value="Перемістити"/></td>
    </tr>
  </table>
</form>
</body>
</html>
