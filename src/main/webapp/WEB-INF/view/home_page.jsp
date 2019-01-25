<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Royal Booking</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3_buttons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/part_of_sort_table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/css/materialize.min.css'>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='http://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js'></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/js/materialize.min.js'></script>
    <script>
        (function (window, document, undefined) {

            var factory = function ($, DataTable) {
                "use strict";

                $('.search-toggle').click(function () {
                    if ($('.hiddensearch').css('display') == 'none')
                        $('.hiddensearch').slideDown();
                    else
                        $('.hiddensearch').slideUp();
                });

                /* Set the defaults for DataTables initialisation */
                $.extend(true, DataTable.defaults, {
                    dom: "<'hiddensearch'f'>" +
                        "tr" +
                        "<'table-footer'lip'>",
                    renderer: 'material'
                });

                /* Default class modification */
                $.extend(DataTable.ext.classes, {
                    sWrapper: "dataTables_wrapper",
                    sFilterInput: "form-control input-sm",
                    sLengthSelect: "form-control input-sm"
                });

                /* Bootstrap paging button renderer */
                DataTable.ext.renderer.pageButton.material = function (settings, host, idx, buttons, page, pages) {
                    var api = new DataTable.Api(settings);
                    var classes = settings.oClasses;
                    var lang = settings.oLanguage.oPaginate;
                    var btnDisplay, btnClass, counter = 0;

                    var attach = function (container, buttons) {
                        var i, ien, node, button;
                        var clickHandler = function (e) {
                            e.preventDefault();
                            if (!$(e.currentTarget).hasClass('disabled')) {
                                api.page(e.data.action).draw(false);
                            }
                        };

                        for (i = 0, ien = buttons.length; i < ien; i++) {
                            button = buttons[i];

                            if ($.isArray(button)) {
                                attach(container, button);
                            } else {
                                btnDisplay = '';
                                btnClass = '';

                                switch (button) {

                                    case 'first':
                                        btnDisplay = lang.sFirst;
                                        btnClass = button + (page > 0 ?
                                            '' : ' disabled');
                                        break;

                                    case 'previous':
                                        btnDisplay = '<i class="material-icons">chevron_left</i>';
                                        btnClass = button + (page > 0 ?
                                            '' : ' disabled');
                                        break;

                                    case 'next':
                                        btnDisplay = '<i class="material-icons">chevron_right</i>';
                                        btnClass = button + (page < pages - 1 ?
                                            '' : ' disabled');
                                        break;

                                    case 'last':
                                        btnDisplay = lang.sLast;
                                        btnClass = button + (page < pages - 1 ?
                                            '' : ' disabled');
                                        break;

                                }

                                if (btnDisplay) {
                                    node = $('<li>', {
                                        'class': classes.sPageButton + ' ' + btnClass,
                                        'id': idx === 0 && typeof button === 'string' ?
                                            settings.sTableId + '_' + button : null
                                    })
                                        .append($('<a>', {
                                                'href': '#',
                                                'aria-controls': settings.sTableId,
                                                'data-dt-idx': counter,
                                                'tabindex': settings.iTabIndex
                                            })
                                                .html(btnDisplay)
                                        )
                                        .appendTo(container);

                                    settings.oApi._fnBindAction(
                                        node, {
                                            action: button
                                        }, clickHandler
                                    );

                                    counter++;
                                }
                            }
                        }
                    };

                    // IE9 throws an 'unknown error' if document.activeElement is used
                    // inside an iframe or frame.
                    var activeEl;

                    try {
                        // Because this approach is destroying and recreating the paging
                        // elements, focus is lost on the select button which is bad for
                        // accessibility. So we want to restore focus once the draw has
                        // completed
                        activeEl = $(document.activeElement).data('dt-idx');
                    } catch (e) {
                    }

                    attach(
                        $(host).empty().html('<ul class="material-pagination"/>').children('ul'),
                        buttons
                    );

                    if (activeEl) {
                        $(host).find('[data-dt-idx=' + activeEl + ']').focus();
                    }
                };

                /*
                 * TableTools Bootstrap compatibility
                 * Required TableTools 2.1+
                 */
                if (DataTable.TableTools) {
                    // Set the classes that TableTools uses to something suitable for Bootstrap
                    $.extend(true, DataTable.TableTools.classes, {
                        "container": "DTTT btn-group",
                        "buttons": {
                            "normal": "btn btn-default",
                            "disabled": "disabled"
                        },
                        "collection": {
                            "container": "DTTT_dropdown dropdown-menu",
                            "buttons": {
                                "normal": "",
                                "disabled": "disabled"
                            }
                        },
                        "print": {
                            "info": "DTTT_print_info"
                        },
                        "select": {
                            "row": "active"
                        }
                    });

                    // Have the collection use a material compatible drop down
                    $.extend(true, DataTable.TableTools.DEFAULTS.oTags, {
                        "collection": {
                            "container": "ul",
                            "button": "li",
                            "liner": "a"
                        }
                    });
                }

            }; // /factory

            // Define as an AMD module if possible
            if (typeof define === 'function' && define.amd) {
                define(['jquery', 'datatables'], factory);
            } else if (typeof exports === 'object') {
                // Node/CommonJS
                factory(require('jquery'), require('datatables'));
            } else if (jQuery) {
                // Otherwise simply initialise as normal, stopping multiple evaluation
                factory(jQuery, jQuery.fn.dataTable);
            }

        })(window, document);

        $(document).ready(function () {
            $('#datatable').dataTable({
                "oLanguage": {
                    "sStripClasses": "",
                    "sSearch": "",
                    "sSearchPlaceholder": "Enter Keywords Here",
                    "sInfo": "_START_ -_END_ of _TOTAL_",
                    "sLengthMenu": '<span>Rows per page:</span><select class="browser-default">' +
                        '<option value="10">10</option>' +
                        '<option value="20">20</option>' +
                        '<option value="30">30</option>' +
                        '<option value="40">40</option>' +
                        '<option value="50">50</option>' +
                        '<option value="-1">All</option>' +
                        '</select></div>'
                },
                bAutoWidth: false
            });
        });
    </script>
</head>
<body>

<c:import url="/WEB-INF/view/header/header.jsp"/>

<%--table with rooms --%>
<div class="row">
    <div id="admin" class="col s12">
        <div class="card material-table">
            <div class="table-header">
                <span class="table-title">
                    <spring:message code="label.room_list"/>
                </span>
            </div>
            <table id="datatable">
                <thead>
                <tr>
                    <th width="50"><spring:message code="label.id"/></th>
                    <th><spring:message code="label.room_type"/></th>
                    <th><spring:message code="label.room_beds_amount"/></th>
                    <th><spring:message code="label.room_area"/></th>
                    <th><spring:message code="label.room_daily_cost"/></th>
                    <th><spring:message code="label.room_info"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="room" items="${requestScope.rooms}">
                    <tr>
                        <td><c:out value="${room.id}"/></td>
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
                        <td><c:out value="${room.bedsAmount}"/></td>
                        <td><c:out value="${room.area}"/></td>
                        <td><c:out value="${room.dailyCost}"/></td>
                        <td><c:out value="${room.additionalInfo}"/>
                        </td>
                        <td>
                            <form method="GET" action="<c:url value="/room/${room.id}"/>">
                                <button type="submit" class="blueButton" value="book"><spring:message
                                        code="label.room_details"/></button>
                            </form>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

</body>

</html>

