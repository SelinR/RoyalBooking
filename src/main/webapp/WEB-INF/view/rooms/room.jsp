<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <title><spring:message code="label.room_page"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css"/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/image_style.css"/>

    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
    <script>
        $(window).on('load', function() {

            let nbImg = 0;
            $('.slider .container-images img').each(function() {
                nbImg += 1;
            });

            $('.slider .arrow').click(function() {
                let n = imageActive();

                $('.slider').removeClass('right left');

                if($(this).hasClass('left')) {
                    n -= 1;
                    $('.slider').addClass('left');
                    setTimeout(function() {
                        $('.slider .container-images img.active').addClass('to_left');
                    }, 50)
                }
                else if($(this).hasClass('right')) {
                    n += 1;
                    $('.slider').addClass('right');
                    setTimeout(function() {
                        $('.slider .container-images img.active').addClass('to_right');
                    }, 50)
                }

                if(n > nbImg) n = 1;
                if(n < 1) n = nbImg;

                setTimeout(function() {
                    $('.slider .container-images img').removeClass('active');
                    $('.slider .container-images img:nth-child('+n+')').addClass('active');

                    setTimeout(function() {
                        $('.slider .container-images img').removeClass('to_left');
                        $('.slider .container-images img').removeClass('to_right');
                    }, 500)
                }, 50)
            });

            function imageActive() {
                let n = 1;
                $('.slider .container-images img').each(function(index) {
                    if($(this).hasClass('active')) {
                        n += index;
                    }
                });
                return n;
            }

        });
    </script>

</head>
<body>

<div class="Menu" align="center">
    <c:import url="/WEB-INF/view/header/header.jsp"/>
</div>

<div class="wrapper">
    <div class="slider right">

        <div class="arrow left" >
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 292.359 292.359">
                <path d="M222.979 5.424C219.364 1.807 215.08 0 210.132 0c-4.949 0-9.233 1.807-12.848 5.424L69.378 133.331c-3.615 3.617-5.424 7.898-5.424 12.847s1.809 9.233 5.424 12.847l127.906 127.907c3.614 3.617 7.898 5.428 12.848 5.428 4.948 0 9.232-1.811 12.847-5.428 3.617-3.614 5.427-7.898 5.427-12.847V18.271c-.001-4.949-1.81-9.229-5.427-12.847z" fill="#FFFFFF"></path>
            </svg>
        </div>

        <div  class="container-images">

            <img class="active" src="${pageContext.request.contextPath}${imageUrl_1}">
            <img src="${pageContext.request.contextPath}${imageUrl_2}">
            <img src="${pageContext.request.contextPath}${imageUrl_3}">

        </div>

        <div class="arrow right">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 292.359 292.359">
                <path d="M222.979 5.424C219.364 1.807 215.08 0 210.132 0c-4.949 0-9.233 1.807-12.848 5.424L69.378 133.331c-3.615 3.617-5.424 7.898-5.424 12.847s1.809 9.233 5.424 12.847l127.906 127.907c3.614 3.617 7.898 5.428 12.848 5.428 4.948 0 9.232-1.811 12.847-5.428 3.617-3.614 5.427-7.898 5.427-12.847V18.271c-.001-4.949-1.81-9.229-5.427-12.847z" fill="#FFFFFF"></path>
            </svg>
        </div>

    </div>
</div>

<div>
    <div align="center">
        <table id="datatable" class="tg">
            <thead>
            <tr>
                <th width="100"><spring:message code="label.parameter"/></th>
                <th width="250"><spring:message code="label.info"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><spring:message code="label.room_type"/></td>
                <td>
                    <c:if test="${room.roomType == 'BASIC'}">
                        <spring:message code="label.room_basic"/>
                    </c:if>
                    <c:if test="${room.roomType == 'FAMILY'}">
                        <spring:message code="label.room_family"/>
                    </c:if>
                    <c:if test="${room.roomType == 'LUXURY'}">
                        <spring:message code="label.room_luxury"/>
                    </c:if>
                    <c:if test="${room.roomType == 'PENTHOUSE'}">
                        <spring:message code="label.room_penthose"/>
                    </c:if>
                </td>

            </tr>
            <tr>
                <td><spring:message code="label.room_beds_amount"/></td>
                <td><c:out value="${room.bedsAmount}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_area"/></td>
                <td><c:out value="${room.area}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_daily_cost"/></td>
                <td><c:out value="${room.dailyCost}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.room_info"/></td>
                <td><c:out value="${room.additionalInfo}"/></td>
            </tr>


            </tbody>
        </table>
    </div>
</div>

<div align="center">
    <form method="GET" action="<c:url value="/order/creation/${room.id}"/>">
        <button type="submit" class="blueButton" value="<spring:message code="label.book_room"/>">
            <spring:message code="label.book_room"/>
        </button>
    </form>
</div>

</body>

</html>