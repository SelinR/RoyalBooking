<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title><spring:message code="label.admin"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css">
  </head>

  <body bgcolor="#fff8dc">
  <c:import url="/WEB-INF/view/header/header.jsp"/>

    <a href="<c:url value="/admin/rooms_list"/>" target="_blank">
      <p><spring:message code="label.all_rooms"/></p>
    </a>
  <br>
    <a href="<c:url value="/admin/users"/>" target="_blank">
      <p><spring:message code="label.all_users"/></p>
    </a>
  <br>
  <a href="<c:url value="/admin/orders"/>" target="_blank">
    <p><spring:message code="label.all_orders"/></p>
  </a>
  </body>
</html>
