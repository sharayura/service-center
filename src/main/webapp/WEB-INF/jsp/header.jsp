<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 26.12.2023
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
  <c:if test="${not empty sessionScope.user}">
    <form action="${pageContext.request.contextPath}/logout" method="post">
      <button type="submit">Logout</button>
    </form>
  </c:if>
</div>
