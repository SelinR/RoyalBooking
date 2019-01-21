<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <title>Profile</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

</head>
<body>
<div class="loginContainer" align="left">

    <div style="display: flex">
        <a href="<c:url value="/registration"/>">
            <div class="greenButton">Register</div>
        </a>
        <pre>   </pre>
        <a href="<c:url value="/login"/>">
            <div class="blueButton">Login</div>
        </a>
    </div>
</div>

<table>
    <tr>
        <th width="340"><h2>Profile Details</h2></th>
        <th width="100"/>
        <th width="300"><h2>Edit user</h2></th>
    </tr>
    <tr>
        <td>
            <table id="datatable" class="tg">
                <tr>
                    <th width="140"/>
                    <th width="200">Information</th>
                </tr>

                <tr>
                    <td>Name</td>
                    <td><c:out value="${user.name}"/></td>
                </tr>
                <tr>
                    <td>Surname</td>
                    <td><c:out value="${user.surname}"/></td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td><c:out value="${user.country}"/></td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td><c:out value="${user.birthday}"/></td>
                <tr>
                    <td>Phone</td>
                    <td><c:out value="${user.phone}"/></td>
                <tr>
                    <td>Email</td>
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
                        <td><form:label path="name">Name</form:label></td>
                        <td width="200"><form:input path="name"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="surname">Surname</form:label></td>
                        <td><form:input path="surname"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="country">Country</form:label></td>
                        <td><form:input path="country"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="birthday">Birthday</form:label></td>
                        <td><form:input path="birthday"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="phone">Phone</form:label></td>
                        <td><form:input path="phone"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email">Email</form:label></td>
                        <td><form:input path="email"/></td>
                    </tr>
                    <tr>
                        <td><form:hidden path="password"/></td>
                    </tr>
                    <tr>
                        <td><form:hidden path="userType"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit"/></td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>
</table>
<br><button onclick="location.href='delete'">Delete your profile</button>
</body>
</html>
