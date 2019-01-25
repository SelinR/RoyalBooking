<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="topMenu">
    <div style="padding-top: 20px;
    padding-left: 20px;
    padding-right: 20px;">
    <span style="float: right">
        <a href="<c:url value="?lang=en"/>" style="color: #ffffff; text-decoration: none">en</a>
        |    <a href="<c:url value="?lang=ru"/>" style="color: #ffffff; text-decoration: none">ru</a>
    </span>

        <div style="display: flex">

            <svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" viewBox="0 0 64 64">
                <a href="<c:url value="/"/>">
                    <path fill="yellow"
                          d="M5 19h14v3h-14v-3zm18-11c-.729 0-1.295.79-.832 1.521-1.229 1.474-3.371 2.971-4.355 2.438-1.201-.65-.277-3.302.451-4.982.958.15 1.736-.591 1.736-1.477 0-.829-.672-1.5-1.5-1.5s-1.5.671-1.5 1.5c0 .452.204.853.52 1.127-.645 1.643-2.325 3.807-3.41 3.591-1.347-.268-1.69-3.448-1.685-5.287.62-.183 1.075-.751 1.075-1.431 0-.829-.672-1.5-1.5-1.5s-1.5.671-1.5 1.5c0 .68.455 1.248 1.075 1.432.006 1.839-.338 5.019-1.685 5.287-1.084.216-2.765-1.949-3.41-3.592.316-.274.52-.675.52-1.127 0-.829-.672-1.5-1.5-1.5s-1.5.671-1.5 1.5c0 .886.778 1.627 1.736 1.476.729 1.681 1.652 4.333.451 4.982-.984.533-3.127-.964-4.355-2.438.463-.73-.103-1.52-.832-1.52-.553 0-1 .448-1 1 0 .704.726 1.221 1.413.905 1.134 1.264 3.335 4.242 3.587 7.095h14c.252-2.853 2.453-5.831 3.587-7.095.687.316 1.413-.201 1.413-.905 0-.552-.447-1-1-1z"></path>
                </a>
            </svg>

        <%--buttons--%>
        <sec:authorize access="isAnonymous()">
                <a href="<c:url value="/registration"/>">
                    <div class="greenButton" align="right">
                        <p><spring:message code="label.registration"/></p>
                    </div>
                </a>
                <a href="<c:url value="/login"/>">
                    <div class="blueButton" align="right">
                        <p><spring:message code="label.login"/></p>
                    </div>
                </a>
            </div>
        </sec:authorize>

        <%-- And logout for authenticated users --%>
        <div style="display: flex">
            <sec:authorize access="isAuthenticated()">
                <a href="<c:url value="/logout"/>">
                    <div class="redButton" align="right">
                        <p><spring:message code="label.logout"/></p>
                    </div>
                </a>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <a href="<c:url value="/admin"/>">
                        <div class="purpleButton" align="left">
                            <p><spring:message code="label.admin"/></p>
                        </div>
                    </a>
                </sec:authorize>
                <sec:authorize access="hasAuthority('USER')">
                    <a href="<c:url value="/profile"/>">
                        <div class="purpleButton" align="left">
                            <p><spring:message code="label.profile"/></p>
                        </div>
                    </a>
                </sec:authorize>
            </sec:authorize>
        </div>
    </div>
</div>