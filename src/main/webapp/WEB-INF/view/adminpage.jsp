<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title page</title>
  </head>

  <body bgcolor="#fff8dc">
    <a href="<c:url value="/view/rooms"/>" target="_blank">All rooms</a>
  </body>
  <br>
  <body>
    <a href="<c:url value="/view/users"/>" target="_blank">All users</a>

  </body>
  <br>
  <body>
  <a href="<c:url value="/view/orders"/>" target="_blank">All orders</a>
  </body>
</html>