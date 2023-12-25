<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 25.12.2023
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="login">Login:
        <input type="text" name="login" id="login" value="${param.login}" required>
    </label><br>
    <label for="pwd">Password:
        <input type="password" name="pwd" id="pwd" required>
    </label><br>
    <button type="submit">Login</button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">Register</button>
    </a>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>Email or password is not correct</span>
        </div>
    </c:if>
</form>

</body>
</html>
