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
<div>
    <a href="<c:url value="/admin/rooms"/>">
        <div class="redButton">
            <p><spring:message code="label.all_rooms"/></p>
        </div>
    </a>
    <br>
    <a href="<c:url value="/admin/users"/>">
        <div class="blueButton">
            <p><spring:message code="label.all_users"/></p>
        </div>
    </a>
    <br>
    <a href="<c:url value="/admin/orders"/>">
        <div class="purpleButton">
            <p><spring:message code="label.all_orders"/></p>
        </div>
    </a>
</div>
</body>
</html>
