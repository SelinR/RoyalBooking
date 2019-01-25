<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.epam.royalbooking.enums.OrderStatus" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring-tags" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-tables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>Ωß
</head>
<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>
<h1> <spring-tags:message code="label.order_details"/> </h1>
<table class="tg">
    <tr>
        <th width="60"><spring-tags:message code="label.id"/></th>
        <th width="60"><spring-tags:message code="label.order_status"/></th>
        <th width="80"><spring-tags:message code="label.order_entry_date"/></th>
        <th width="80"><spring-tags:message code="label.order_leave_date"/></th>
        <th width="60"><spring-tags:message code="label.order_total_price"/></th>
        <th width="60"><spring-tags:message code="label.order_user_id"/></th>
        <th width="60"><spring-tags:message code="label.order_booked_room_id"/></th>
        <th width="60"><spring-tags:message code="label.delete"/></th>
    </tr>
    <tr align="center">
        <td><c:out value="${order.id}"/></td>
        <td><c:out value="${order.status}"/></td>
        <td><c:out value="${order.entryDate}"/></td>
        <td><c:out value="${order.leaveDate}"/></td>
        <td><c:out value="${order.totalPrice}"/></td>
        <td><c:out value="${order.userID}"/></td>
        <td><c:out value="${order.bookedRoomID}"/></td>
        <td><a href="<c:url value='/admin/orders/delete/${order.id}'/>"><spring-tags:message code="label.delete"/></a></td>
    </tr>
</table>
<h2> <spring-tags:message code="label.edit"/> </h2>

<c:url var="editAction" value="/admin/orders/edit"/>


<form:form method="post" action="${editAction}" modelAttribute="order">
    <table class="tg">
        <tr>
            <td><spring-tags:message code="label.order_booked_room_id"/></td>
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
                           value="<c:out value="${order.entryDate}"/>"
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
                           value="<c:out value="${order.leaveDate}"/>"
                           min="<c:out value="${requestScope.minDate}"/>"
                           max="<c:out value="${requestScope.maxDate}"/>">
                </label>
            </td>
        </tr>
        <tr>
            <td><spring-tags:message code="label.order_user_id"/></td>
            <td>
                <label>
                    <form:select path="userID">
                        <c:forEach var="user" items="${requestScope.users}">
                            <option><c:out value="${user.id}"/></option>
                        </c:forEach>
                    </form:select>
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
                        </form:option> <br>
                        <form:option value="EXPIRED">
                            <spring-tags:message code="label.order_status_expired"/>
                        </form:option> <br>
                        <form:option value="DECLINED">
                            <spring-tags:message code="label.order_status_declined"/>
                        </form:option> <br>
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
