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
<div class="loginContainer">

    <a href="?lang=en">en</a>    |    <a href="?lang=ru">ru</a>

    <sec:authorize access="isAnonymous()">
        <%--2 buttons--%>
        <div style="display: flex">
            <a href="<c:url value="/registration"/>">
                <div class="greenButton" align="right"><spring:message code="label.registration"/></div>
            </a>
            <pre>   </pre>
            <a href="<c:url value="/login"/>">
                <div class="blueButton" align="right"><spring:message code="label.login"/></div>
            </a>
        </div>
    </sec:authorize>

    <%-- And logout for authenticated users --%>
    <sec:authorize access="isAuthenticated()">
        <div style="display: flex">
            <a href="<c:url value="/logout"/>">
                <div class="redButton" align="right"><spring:message code="label.logout"/></div>
            </a>
        </div>
    </sec:authorize>
</div>

<h2><spring:message code="label.room_create"/></h2><br/>
<form method="post" action="<c:url value="/admin/room_add"/>">
    <label>
        <spring:message code="label.room_type"/><br>
        <input type="radio" name="roomType" value="BASIC" checked>
        <spring:message code="label.room_basic"/>
        <input type="radio" name="roomType" value="FAMILY">
        <spring:message code="label.room_family"/>
        <input type="radio" name="roomType" value="LUXURY">
        <spring:message code="label.room_luxury"/>
        <input type="radio" name="roomType" value="PENTHOUSE">
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
