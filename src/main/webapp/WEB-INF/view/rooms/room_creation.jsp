<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.room_page"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
</head>

<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>

<h2><spring:message code="label.room_create"/></h2><br/>
<form method="post" action="<c:url value="/admin/room_add"/>">
    <label>
        <spring:message code="label.room_type"/><br>
        <input type="radio" name="roomType" value="<spring:message code="label.room_basic"/>" checked>
        <spring:message code="label.room_basic"/>
        <input type="radio" name="roomType" value="<spring:message code="label.room_family"/>">
        <spring:message code="label.room_family"/>
        <input type="radio" name="roomType" value="<spring:message code="label.room_luxury"/>">
        <spring:message code="label.room_luxury"/>
        <input type="radio" name="roomType" value="<spring:message code="label.room_penthose"/>">
        <spring:message code="label.room_penthose"/>
    </label><br>
    <label>
        <spring:message code="label.room_beds_amount"/> <br>
        <input type="number" name="bedsAmount" step="1" required>
    </label><br>
    <label>
        <spring:message code="label.room_area"/> <br>
        <input type="number" name="area" step="0.1" required=>
    </label><br>
    <label>
        <spring:message code="label.room_daily_cost"/> <br>
        <input type="number" name="dailyCost" step="0.1" required>
    </label><br>
    <label>
        <spring:message code="label.room_info"/> <br>
        <input type="text" name="additionalInfo">
    </label><br>

    <input type="submit" value="Create"><br>
</form>
<pre>

</pre>
<form method="get" action="<c:url value="/admin/rooms_list"/>">
    <button><spring:message code="label.room_back_to_list"/></button>
</form>
</body>
</html>
