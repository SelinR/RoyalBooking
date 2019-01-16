<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Project Hotel</title>
    <base href="${pageContext.request.contextPath}/WEB-INF/view/css/">
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" href="<c:url value="css/style.css"/>">
    <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body bgcolor="#fff8dc">
<!--<div content="main" align="center">
    <a href="<c:url value="/registration"/>">Registration</a>
    <br/>
    <a href="<c:url value="/login"/>">Login</a>
</div>-->


<div class="loginContainer">
    <input placeholder="email" type="email">
    <input placeholder="password" type="password">

    <div style="display: flex">
        <a href="<c:url value="/registration"/>">
            <div class="greenButton">Register</div>
        </a>
        <a href="<c:url value="/login"/>">
            <div class="blueButton">Login</div>
        </a>
    </div>
</div>


<div class="table-users">
    <div class="header">Users</div>

    <h2></h2><table cellspacing="0">
    <tbody><tr>
        <th>Picture</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th width="230">Comments</th>
    </tr>

    <tr>
        <td><img src="http://lorempixel.com/100/100/people/1" alt=""></td>
        <td>Jane Doe</td>
        <td>jane.doe@foo.com</td>
        <td>01 800 2000</td>
        <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit. </td>
    </tr>

    <tr>
        <td><img src="http://lorempixel.com/100/100/sports/2" alt=""></td>
        <td>John Doe</td>
        <td>john.doe@foo.com</td>
        <td>01 800 2000</td>
        <td>Blanditiis, aliquid numquam iure voluptatibus ut maiores explicabo ducimus neque, nesciunt rerum perferendis, inventore.</td>
    </tr>

    <tr>
        <td><img src="http://lorempixel.com/100/100/people/9" alt=""></td>
        <td>Jane Smith</td>
        <td>jane.smith@foo.com</td>
        <td>01 800 2000</td>
        <td> Culpa praesentium unde pariatur fugit eos recusandae voluptas.</td>
    </tr>

    <tr>
        <td><img src="http://lorempixel.com/100/100/people/3" alt=""></td>
        <td>John Smith</td>
        <td>john.smith@foo.com</td>
        <td>01 800 2000</td>
        <td>Aut voluptatum accusantium, eveniet, sapiente quaerat adipisci consequatur maxime temporibus quas, dolorem impedit.</td>
    </tr>
    </tbody></table>
</div>

</body>
</html>
