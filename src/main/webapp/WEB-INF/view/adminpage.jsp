<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title page</title>
  </head>

<body bgcolor="#fff8dc">
  <div class="loginContainer">
    <span style="float: right">
        <a href="?lang=en">en</a>    |    <a href="?lang=ru">ru</a>
    </span>
  </div>

  <a href="<c:url value="/admin/rooms_list"/>" target="_blank">
    <p><spring:message code="label.all_rooms"/></p>
  </a>
<br>
  <a href="<c:url value="/users"/>" target="_blank">
    <p><spring:message code="label.all_users"/></p>
  </a>
<br>
<a href="<c:url value="/orders"/>" target="_blank">
  <p><spring:message code="label.all_orders"/></p>
</a>
</body>
</html>
