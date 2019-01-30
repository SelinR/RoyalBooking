<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:import url="/WEB-INF/view/header/header.jsp"/>

<div>
    <h2 align="center"><spring:message code="label.order_details"/></h2>
</div>
<div>
    <div>
        <div style="    display: flex;
    justify-content: center;">
            <table id="datatable" class="tg">
                <thead>
                <tr>
                    <th width="100"><spring:message code="label.parameter"/></th>
                    <th width="250"><spring:message code="label.info"/></th>
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
    <br>
    <p style="text-align: center">
    <spring:message code="label.enter_card_info"/></p>
    <div >
        <br>
        <form style="    display: flex;
    justify-content: center;"
              name="card_form" method="post" action="<c:url value="/order/save"/>">
            <table>
                <tr>
                    <td><spring:message code="label.card_type"/></td>
                    <td>
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
                            <input width="200" name="card_number" type="text" size="80" required/>
                            <script>
                                var cc = card_form.card_number,
                                    events = ['input', 'change', 'blur', 'keyup'];
                                for (var i in events) {
                                    cc.addEventListener(events[i], formatCardCode, false);
                                }

                                function formatCardCode() {
                                    var cardCode = this.value.replace(/[^\d]/g, '').substring(0, 16);
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
                            <input width="50" name=card_cvv type="password" size="80" required>
                            <script>
                                var cc = card_form.card_cvv,
                                    events = ['input', 'change', 'blur', 'keyup'];
                                for (var i in events) {
                                    cc.addEventListener(events[i], formatCardCode, false);
                                }

                                function formatCardCode() {
                                    var cardCode = this.value.replace(/[^\d]/g, '').substring(0, 3);
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
                            <input type="month" min="2019-02" size="80" required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><input type="hidden" name="bookedRoomID" value="${order.bookedRoomID}">
                <input type="hidden" name="totalPrice" value="${order.totalPrice}">
                <input type="hidden" name="entryDate" value="${order.entryDate}">
                <input type="hidden" name="leaveDate" value="${order.leaveDate}">
                <input type="hidden" name="userID" value="${order.userID}">
                <input type="hidden" name="prepaid" value="true"> <br>
                <input type="submit" class="blueFlexButton" value="<spring:message code="label.order_confirm"/>">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
