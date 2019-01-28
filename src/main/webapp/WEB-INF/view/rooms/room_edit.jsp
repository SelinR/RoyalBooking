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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-tables-style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
</head>

<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>


<h2><spring:message code="label.room_info"/></h2>
<div>
    <div align="left">
        <table id="datatable" class="tg">
            <thead>
            <tr>
                <th width="100"><spring:message code="label.parameter"/></th>
                <th width="500"><spring:message code="label.info"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><spring:message code="label.id"/></td>
                <td><c:out value="${room.id}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_type"/></td>
                <td>
                    <c:if test="${room.roomType == 'BASIC'}">
                        <spring:message code="label.room_basic"/>
                    </c:if>
                    <c:if test="${room.roomType == 'FAMILY'}">
                        <spring:message code="label.room_family"/>
                    </c:if>
                    <c:if test="${room.roomType == 'LUXURY'}">
                        <spring:message code="label.room_luxury"/>
                    </c:if>
                    <c:if test="${room.roomType == 'PENTHOUSE'}">
                        <spring:message code="label.room_penthose"/>
                    </c:if>
                </td>

            </tr>
            <tr>
                <td><spring:message code="label.room_beds_amount"/></td>
                <td><c:out value="${room.bedsAmount}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_area"/></td>
                <td><c:out value="${room.area}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_daily_cost"/></td>
                <td><c:out value="${room.dailyCost}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_info"/></td>
                <td><c:out value="${room.additionalInfo}"/></td>
            </tr>


            </tbody>
        </table>
    </div>
</div>

<form method="post" action="<c:url value="/admin/room/update"/>">


    <label>
        <spring:message code="label.room_type"/><br>
        <input width="50" type="radio" name="roomType" value="BASIC"
               <c:if test="${room.roomType.toString() == 'BASIC'}">checked</c:if> > <spring:message code="label.room_basic"/> <br>
        <input type="radio" name="roomType" value="FAMILY"
               <c:if test="${room.roomType.toString() == 'FAMILY'}">checked</c:if> > <spring:message code="label.room_family"/> <br>
        <input type="radio" name="roomType" value="LUXURY"
               <c:if test="${room.roomType.toString() == 'LUXURY'}">checked</c:if> > <spring:message code="label.room_luxury"/> <br>
        <input type="radio" name="roomType" value="PENTHOUSE"
               <c:if test="${room.roomType.toString() == 'PENTHOUSE'}">checked</c:if> > <spring:message code="label.room_penthose"/> <br>

    </label><br>
    <label>
        <spring:message code="label.room_beds_amount"/> <br>
        <input type="number" name="bedsAmount" step="1" value="${room.bedsAmount}" required>
    </label><br>
    <label>
        <spring:message code="label.room_area"/> <br>
        <input type="number" name="area" step="0.1" value="${room.area}" required=>
    </label><br>
    <label>
        <spring:message code="label.room_daily_cost"/> <br>
        <input type="number" name="dailyCost" step="0.1" value="${room.dailyCost}" required>
    </label><br>
    <label>
        <spring:message code="label.room_info"/> <br>
        <input type="text" name="additionalInfo" value="${room.additionalInfo}">
    </label><br>
    <input type="hidden" name="id" value="${room.id}">
    <input type="submit" value="<spring:message code="label.submit"/> "><br>
</form>
<pre>

</pre>
<form method="get" action="<c:url value="/admin/rooms"/>">
    <button><spring:message code="label.room_back_to_list"/></button>
</form>
</body>

</html>