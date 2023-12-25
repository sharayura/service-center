<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 25.12.2023
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Registration</title>
</head>
<body>
<form action="/registration" method="post">
  <label for="login">Login:
    <input type="text" name="login" id="login">
  </label><br/>
  <label for="pwd">Password:
    <input type="password" name="pwd" id="pwd">
  </label><br/>
  <select name="role" id="role">
    <c:forEach var="role" items="${requestScope.roles}">
      <option label="${role}">${role}</option>
    </c:forEach>
  </select>
  <br/>

  <input type="submit" value="Send">
</form>
<c:if test="${not empty requestScope.errors}">
  <div style="color: red">
    <c:forEach var="error" items="${requestScope.errors}">
      <span>${error.message}</span>
      <br>
    </c:forEach>
  </div>
</c:if>

</body>
</html>
