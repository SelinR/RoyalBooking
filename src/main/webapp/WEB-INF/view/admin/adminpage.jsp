<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title><spring:message code="label.admin"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
  </head>

  <body>
  <c:import url="/WEB-INF/view/header/header.jsp"/>

    <a class=redButton href="<c:url value="/admin/rooms_list"/>">
      <p><spring:message code="label.all_rooms"/></p>
    </a>
  <br>
    <a class=greenButton href="<c:url value="/admin/users"/>">
      <p><spring:message code="label.all_users"/></p>
    </a>
  <br>
  <a class=purpleButton href="<c:url value="/admin/orders"/>">
    <p><spring:message code="label.all_orders"/></p>
  </a>
  </body>
</html>
