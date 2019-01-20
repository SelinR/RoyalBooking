<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Royal Booking</title>
</head>
<body>
    <div id="wrap">
        <header class="login">
            <div class="table-title">Royal Booking</div>
                <div class="loginContainer">
                    <sec:authorize access="isAnonymous()">
                        <%--2 buttons--%>
                        <div style="display: flex">
                            <a href="<c:url value="/registration"/>">
                                <div class="greenButton" align="right">Register</div>
                            </a>
                            <pre>   </pre>
                            <a href="<c:url value="/login"/>">
                                <div class="blueButton" align="right">Login</div>
                            </a>
                         </div>
                    </sec:authorize>

                    <%-- And logout for authenticated users --%>
                    <sec:authorize access="isAuthenticated()">
                        <div style="display: flex">
                            <a href="<c:url value="/logout"/>">
                                <div class="redButton" align="right">Logout</div>
                            </a>
                        </div>
                    </sec:authorize>

                    <%-- And administrator buttons go here --%>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div style="display: flex">
                            <a href="<c:url value="/users"/>">
                                <div class="blueButton" align="right">Users</div>
                            </a>
                            <a href="<c:url value="/view/rooms"/>">
                                <div class="blueButton" align="right">Rooms</div>
                            </a>
                            <a href="<c:url value="/view/orders"/>">
                                <div class="blueButton" align="right">Orders</div>
                            </a>
                        </div>
                    </sec:authorize>
            </div>
        </header>
    </div>
</body>
</html>
