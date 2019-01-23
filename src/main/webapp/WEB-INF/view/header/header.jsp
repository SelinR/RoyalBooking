<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="loginContainer">

    <span style="float: right">
        <a href="<c:url value="?lang=en"/>">en</a>    |    <a href="<c:url value="?lang=ru"/>">ru</a>
    </span>

    <%--buttons--%>
    <sec:authorize access="isAnonymous()">
        <div style="display: flex">
            <a href="<c:url value="/registration"/>">
                <div class="greenButton" align="right">
                    <p><spring:message code="label.registration"/></p>
                </div>
            </a>
            <pre>   </pre>
            <a href="<c:url value="/login"/>">
                <div class="blueButton" align="right">
                    <p><spring:message code="label.login"/></p>
                </div>
            </a>
        </div>
    </sec:authorize>

    <%-- And logout for authenticated users --%>
    <sec:authorize access="isAuthenticated()">
        <div style="display: flex">
            <a href="<c:url value="/logout"/>">
                <div class="redButton" align="right">
                    <p><spring:message code="label.logout"/></p>
                </div>
            </a>
        </div>

        <sec:authorize access="hasAuthority('ADMIN')" >
            <div style="display: flex">
                <a href="<c:url value="/admin"/>">
                    <div class="purpleButton" align="left">
                        <p><spring:message code="label.admin"/></p>
                    </div>
                </a>
            </div>
        </sec:authorize>

        <sec:authorize access="hasAuthority('USER')">
            <div style="display: flex">
                <a href="<c:url value="/profile"/>">
                    <div class="purpleButton" align="left">
                        <p><spring:message code="label.profile"/></p>
                    </div>
                </a>
            </div>
        </sec:authorize>
    </sec:authorize>
</div>