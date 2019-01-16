<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Project Hotel</title>
    <%--ниже идут ссылки на css файлы. По тому же принципу можно делать со вставкой картинок
    оставил эту страницу просто что бы посмотрели как вставлять картинки ну и тут специальный стиль для них в таблице
    его можно будет вытащить из css, если понадобится--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_cute(fromTableWithPic).css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/2_buttons.css"/>
    <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet"/>

</head>
<body bgcolor="#fff8dc">
<div class="loginContainer" align="left">
    <input placeholder="email" type="email">
    <input placeholder="password" type="password">

    <%--это две кнопки зеленая и синяя--%>
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

<div class="table-users">
    <div class="header" align="center">Our best rooms</div>

    <h2></h2>
    <table cellspacing="0" cellpadding="0" border="1" width="100%">
        <tbody>
        <tr>
            <th>Picture</th>
            <th>Name</th>
            <th>Email</th>
            <th width="230">Phone</th>
            <th width="230">Comments</th>
        </tr>

        <tr>
           <%-- добавление картинки--%>
            <td><img src="${pageContext.request.contextPath}/room_pictures/1.jpg" alt=""></td>
            <td>Jane Doe</td>
            <td>jane.doe@foo.com</td>
            <td>01 800 2000</td>
            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
        </tr>

        <tr>
            <%-- добавление картинки--%>
            <td><img src="${pageContext.request.contextPath}/room_pictures/2.jpg" alt=""></td>
            <td>John Doe</td>
            <td>john.doe@foo.com</td>
            <td>01 800 2000</td>
            <td>Blanditiis, aliquid numquam iure voluptatibus ut maiores explicabo ducimus neque, nesciunt rerum
                perferendis, inventore.
            </td>
        </tr>

        <tr>
            <%-- добавление картинки--%>
            <td><img src="${pageContext.request.contextPath}/room_pictures/3.jpg" alt=""></td>
            <td>Jane Smith</td>
            <td>jane.smith@foo.com</td>
            <td>01 800 2000</td>
            <td> Culpa praesentium unde pariatur fugit eos recusandae voluptas.</td>
        </tr>

        <tr>
            <%-- добавление картинки--%>
            <td><img src="${pageContext.request.contextPath}/room_pictures/penthause.jpg" alt=""></td>
            <td>John Smith</td>
            <td>john.smith@foo.com</td>
            <td>01 800 2000</td>
            <td>Aut voluptatum accusantium, eveniet, sapiente quaerat adipisci consequatur maxime temporibus quas,
                dolorem impedit.
            </td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
