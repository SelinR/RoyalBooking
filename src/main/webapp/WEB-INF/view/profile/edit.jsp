<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.edit"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'/>
</head>
<body>
<c:import url="/WEB-INF/view/header/header.jsp"/>

<table>
    <tr>
        <th width="340"><h2><spring:message code="label.profile_details"/></h2></th>
        <th width="100"/>
        <th width="300"><h2><spring:message code="label.edit"/></h2></th>
    </tr>
    <tr>
        <td>
            <table id="datatable" class="tg">
                <tr>
                    <th width="140"/>
                    <th width="600"><spring:message code="label.info"/></th>
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
        </td>
        <td width="100"/>
        <td>
            <c:url var="editAction" value="/profile/edit/submit"/>

            <form:form method="POST" action="${editAction}" modelAttribute="user">
                <table>
                    <tr>
                        <td><form:hidden path="id"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="name">
                            <spring:message code="label.user_name"/></form:label>
                        </td>
                        <td><form:input cssStyle="width: 180px;" path="name"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="surname">
                            <spring:message code="label.user_surname"/>
                        </form:label></td>
                        <td><form:input cssStyle="width: 180px;" path="surname"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="country">
                            <spring:message code="label.user_country"/>
                        </form:label></td>
                        <td><form:input cssStyle="width: 180px;" path="country"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="birthday">
                            <spring:message code="label.user_birthday"/>
                        </form:label></td>
                        <td><form:input cssStyle="width: 180px;" path="birthday"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="phone">
                            <spring:message code="label.user_phone"/>
                        </form:label></td>
                        <td><form:input cssStyle="width: 180px;" path="phone"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email">
                            <spring:message code="label.user_email"/>
                        </form:label></td>
                        <td><form:input cssStyle="width: 180px;" path="email"/></td>
                    </tr>
                    <tr>
                        <td><form:hidden path="password"/></td>
                    </tr>
                    <tr>
                        <td><form:hidden path="userType"/></td>
                    </tr>
                    <tr>
                        <td align="center"><input type="submit" class="greenButton" value="<spring:message code="label.submit"/>"/></td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>
</table>
<br><button onclick="location.href='delete'" class="redButton">
    <spring:message code="label.delete_your_profile"/>
</button>
</body>
</html>
