<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.order_confirm"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

</head>
<body>
<c:import url="/WEB-INF/view/header/header_without_i18n.jsp"/>
<span style="float: right">
        <a href="<c:url value="/order/confirm/${order.bookedRoomID}/${order.entryDate}/${order.leaveDate}?lang=en"/>">en</a>
        |
        <a href="<c:url value="/order/confirm/${order.bookedRoomID}/${order.entryDate}/${order.leaveDate}?lang=ru"/>">ru</a>
</span>

<div>
    <h2 align="center"><spring:message code="label.order_details"/></h2>
</div>
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
                <td><spring:message code="label.order_booked_room_id"/></td>
                <td><c:out value="${order.bookedRoomID}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.order_entry_date"/></td>
                <td><c:out value="${order.entryDate}"/></td>

            </tr>
            <tr>
                <td><spring:message code="label.order_leave_date"/></td>
                <td><c:out value="${order.leaveDate}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.order_total_price"/></td>
                <td><c:out value="${order.totalPrice}"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div>
<form name=card_form method="post" action="<c:url value="/order/save"/>">
    <table>
        <tr>
            <td width="200"><spring:message code="label.card_type"/></td>
            <td width="200">
                <label>
                    <select>
                        <option>VISA</option>
                        <option>Mastercard</option>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="label.card_number"/>
            </td>
            <td>
                <label>
                    <input name=card_number type="text" size="80"/>
                    <script>
                        var cc = card_form.card_number,
                            events  = ['input', 'change', 'blur', 'keyup'];
                        for (var i in events) {
                            cc.addEventListener(events[i], formatCardCode, false);
                        }
                        function formatCardCode() {
                            var cardCode = this.value.replace(/[^\d]/g, '').substring(0,16);
                            cardCode = cardCode !== '' ? cardCode.match(/.{1,4}/g).join(' ') : '';
                            this.value = cardCode;
                        }
                    </script>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <p>CVV</p>
            </td>
            <td>
                <label>
                    <input name=card_cvv type="text" size="80">
                    <script>
                        var cc = card_form.card_cvv,
                            events  = ['input', 'change', 'blur', 'keyup'];
                        for (var i in events) {
                            cc.addEventListener(events[i], formatCardCode, false);
                        }
                        function formatCardCode() {
                            var cardCode = this.value.replace(/[^\d]/g, '').substring(0,3);
                            this.value = cardCode;
                        }
                    </script>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="label.expiration_date"/>
            </td>
            <td>
                <label>
                    <input type="month" min="2019-02" size="60"/>
                </label>
            </td>
        </tr>
    </table>
    <input type="hidden" name="bookedRoomID" value="${order.bookedRoomID}">
    <input type="hidden" name="totalPrice" value="${order.totalPrice}">
    <input type="hidden" name="entryDate" value="${order.entryDate}">
    <input type="hidden" name="leaveDate" value="${order.leaveDate}">
    <input type="hidden" name="userID" value="${order.userID}"> <br>
    <input type="submit" class="blueButton" value="<spring:message code="label.order_confirm"/>">
</form>
</div>
</body>
</html>
