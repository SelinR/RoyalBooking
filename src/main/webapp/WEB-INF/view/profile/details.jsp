<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.profile"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'/>

</head>
<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>
<table>
    <tr>
        <td valign="top">
            <h2><spring:message code="label.profile_details"/></h2>
            <table id="datatable" class="tg">
                <tr>
                    <th width="140"></th>
                    <th width="200"><spring:message code="label.info"/></th>
                </tr>
                <tr>
                    <td><spring:message code="label.user_name"/></td>
                    <td><c:out value="${user.name}"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.user_surname"/></td>
                    <td><c:out value="${user.surname}"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.user_country"/></td>
                    <td><c:out value="${user.country}"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.user_birthday"/></td>
                    <td><c:out value="${user.birthday}"/></td>
                <tr>
                    <td><spring:message code="label.user_phone"/></td>
                    <td><c:out value="${user.phone}"/></td>
                <tr>
                    <td><spring:message code="label.user_email"/></td>
                    <td><c:out value="${user.email}"/></td>
                </tr>
            </table>
            <br>
            <button onclick="location.href='profile/edit'" class="blueButton">
                <spring:message code="label.edit"/>
            </button>
        </td>
        <td width="100"/>
        <td valign="top">
            <h2><spring:message code="label.user_your_orders"/></h2>
            <table class="tg">
                <tr>
                    <th width="60"><spring:message code="label.id"/></th>
                    <th width="60"><spring:message code="label.order_status"/></th>
                    <th width="80"><spring:message code="label.order_entry_date"/></th>
                    <th width="80"><spring:message code="label.order_leave_date"/></th>
                    <th width="60"><spring:message code="label.order_total_price"/></th>
                    <th width="60"><spring:message code="label.room_number"/></th>
                    <th width="60"><spring:message code="label.prepaid"/></th>
                    <th width="60"><spring:message code="label.cancel"/></th>
                </tr>
                <c:forEach var="order" items="${requestScope.orders}">
                    <tr>
                        <td align="center"><c:out value="${order.id}"/></td>
                        <td>
                        <c:if test="${order.status == 'ACCEPTED'}">
                            <spring:message code="label.order_status_accepted"/>
                        </c:if>
                        <c:if test="${order.status == 'DECLINED'}">
                            <spring:message code="label.order_status_declined"/>
                        </c:if>
                        <c:if test="${order.status == 'EXPIRED'}">
                            <spring:message code="label.order_status_expired"/>
                        </c:if>
                        </td>
                        <td><c:out value="${order.entryDate}"/></td>
                        <td><c:out value="${order.leaveDate}"/></td>
                        <td><c:out value="${order.totalPrice}"/></td>
                        <td align="center"><a href="/room/${order.bookedRoomID}"><c:out value="${order.bookedRoomID}"/></a></td>
                        <td>
                            <c:if test="${order.prepaid==false}"><spring:message code="label.prepayment_refuse"/> </c:if>
                            <c:if test="${order.prepaid==true}"><spring:message code="label.prepayment_agree"/></c:if>
                        </td>
                        <td>
                            <button class="greenButton" onclick="location.href='profile/cancel/${order.id}'">
                                <spring:message code="label.cancel"/></button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
