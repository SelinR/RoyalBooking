<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring-tags" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.epam.royalbooking.enums.OrderStatus" %>
<html>
<head>
    <meta content="text/html" charset="UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-tables.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
    <title><spring-tags:message code="label.orders"/></title>
</head>

<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>
<h1><spring-tags:message code="label.all_orders"/></h1>
<table class="tg">
    <tr>
        <th width="60"><spring-tags:message code="label.id"/></th>
        <th width="60"><spring-tags:message code="label.order_status"/></th>
        <th width="80"><spring-tags:message code="label.order_entry_date"/></th>
        <th width="80"><spring-tags:message code="label.order_leave_date"/></th>
        <th width="60"><spring-tags:message code="label.order_total_price"/></th>
        <th width="60"><spring-tags:message code="label.order_user_id"/></th>
        <th width="60"><spring-tags:message code="label.order_booked_room_id"/></th>
        <th width="60"><spring-tags:message code="label.prepaid"/></th>
        <th width="60"><spring-tags:message code="label.edit"/></th>
        <th width="60"><spring-tags:message code="label.delete"/></th>
    </tr>
    <c:forEach var="order" items="${requestScope.orders}">
        <tr align="center">
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td><c:out value="${order.entryDate}"/></td>
            <td><c:out value="${order.leaveDate}"/></td>
            <td><c:out value="${order.totalPrice}"/></td>
            <td><c:out value="${order.userID}"/></td>
            <td><c:out value="${order.bookedRoomID}"/></td>
            <td>
                <c:if test="${order.prepaid==false}"><spring-tags:message code="label.prepayment_refuse"/> </c:if>
                <c:if test="${order.prepaid==true}"><spring-tags:message code="label.prepayment_agree"/></c:if>
            </td>
            <td><a href="<c:url value='/admin/order/${order.id}'/>">
                <spring-tags:message code="label.edit"/>
            </a></td>
            <td><a href="<c:url value='/admin/orders/delete/${order.id}'/>">
                <spring-tags:message code="label.delete"/>
            </a></td>
        </tr>
    </c:forEach>
</table>
<h2><spring-tags:message code="label.order_creation"/></h2>

<c:url var="addAction" value="/admin/orders/add"/>

<form:form method="post" action="${addAction}" modelAttribute="order">
    <table class="tg">
        <tr>
            <td><spring-tags:message code="label.id"/></td>
            <td>
                <label>
                    <select size=1 name="bookedRoomID">
                        <c:forEach var="room" items="${requestScope.rooms}">
                            <option><c:out value="${room.id}"/></option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td><spring-tags:message code="label.order_entry_date"/></td>
            <td>
                <label>
                    <input type="date" id="entryDate" name="entryDate"
                           value="<c:out value="${requestScope.minDate}"/>"
                           min="<c:out value="${requestScope.minDate}"/>"
                           max="<c:out value="${requestScope.maxDate}"/>">
                </label>
            </td>
        </tr>
        <tr>
            <td><spring-tags:message code="label.order_leave_date"/></td>
            <td>
                <label>
                    <input type="date" name="leaveDate"
                           value="<c:out value="${requestScope.minDate}"/>"
                           min="<c:out value="${requestScope.minDate}"/>"
                           max="<c:out value="${requestScope.maxDate}"/>">
                </label>
            </td>
        </tr>
        <tr>
            <td><spring-tags:message code="label.order_user_id"/></td>
            <td>
                <label>
                    <select size=1 name="UserID">
                        <c:forEach var="user" items="${requestScope.users}">
                            <option><c:out value="${user.id}"/></option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td><spring-tags:message code="label.order_status"/></td>
            <td>
                <label>
                    <form:select path="status" id="status">
                        <form:option value="ACCEPTED">
                            <spring-tags:message code="label.order_status_accepted"/>
                        </form:option>
                        <form:option value="EXPIRED">
                            <spring-tags:message code="label.order_status_expired"/>
                        </form:option>
                        <form:option value="DECLINED">
                            <spring-tags:message code="label.order_status_declined"/>
                        </form:option>
                    </form:select>
                </label>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="<spring-tags:message code="label.submit"/>"/>
</form:form>
</body>
</html>