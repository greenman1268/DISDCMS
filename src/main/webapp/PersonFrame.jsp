<%-- Date: 31.03.2016 --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Person Edit</title>
</head>

<body>
<form action="<c:url value="/edit"/>" method="POST">
  <input type="hidden" name="personId" value="${person.personId}"/>
  <table>
    <tr>
      <td>Фамілія:</td><td><input type="text" name="surName" value="${person.surName}"/></td>
    </tr>
    <tr>
      <td>Ім'я:</td><td><input type="text" name="firstName" value="${person.firstName}"/></td>
    </tr>
    <tr>
      <td>По батькові:</td><td><input type="text" name="patronymic" value="${person.patronymic}"/></td>
    </tr>
    <tr>
      <td>Дата народження:</td><td><input type="text" name="birthDay" value="${person.birthDay}"/></td>
    </tr>
    <tr>
      <td>Стать:</td>
      <td>
        <c:choose>
          <c:when test="${person.sex==0}">
            <input type="radio" name="sex" value="0" checked>Ч</input>
            <input type="radio" name="sex" value="1">Ж</input>
          </c:when>
          <c:otherwise>
            <input type="radio" name="sex" value="0">Ч</input>
            <input type="radio" name="sex" value="1" checked>Ж</input>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
    <tr>
      <td>Відділ:</td>
      <td>
        <select name="departmentId">
          <c:forEach var="department" items="${person.departments}">
            <c:choose>
              <c:when test="${department.departmentId==person.departmentId}">
                <option value="${department.departmentId}" selected><c:out value="${department.nameDepartment}"/></option>
              </c:when>
              <c:otherwise>
                <option value="${department.departmentId}"><c:out value="${department.nameDepartment}"/></option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </td>
    </tr>
   <%-- <tr>
      <td>Год обучения:</td><td><input type="text" name="educationYear" value="${student.educationYear}"/></td>
    </tr>--%>
  </table>
  <table>
    <tr>
      <td><input type="submit" value="OK" name="OK"/></td>
      <td><input type="submit" value="Cancel" name="Cancel"/></td>
    </tr>
  </table>
</form>
</body>
</html>
