<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 13.12.2023
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Заказы</title>
</head>
<body>
<h1>Список заказов</h1>
<ul>
    <c:if test="${not empty requestScope.orders}">
        <c:forEach var="order" items="${requestScope.orders}">
            <li>${fn:substring(order.getCreated(), 0, 10)} ${order.getModel()} - ${order.getSum()}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>
