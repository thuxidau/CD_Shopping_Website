<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Your Orders</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="icon" href="./img/category/logo.png" type="image/png">

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <!-- HEADER -->
        <div>
            <%@include file="header.jsp"%>
        </div>
        <!-- /HEADER -->

        <!-- NAVIGATION -->
        <nav id="navigation">
            <!-- container -->
            <div class="container">
                <!-- responsive-nav -->
                <div id="responsive-nav">
                    <!-- NAV -->
                    <ul class="main-nav nav navbar-nav">
                        <li><a  href="myaccount">My Account</a></li>
                        <li><a  href="changepassword">Change password</a></li>
                        <li class="active"><a href="orders">Your Order</a></li>
                            <c:if test="${sessionScope.account.roleId == 0}">
                            <li><a href="accountlist">Account List</a></li>
                            <li><a href="statistic">Statistic</a></li>
                            </c:if> 
                    </ul>
                    <!-- /NAV -->
                </div>
                <!-- /responsive-nav -->
            </div>
            <!-- /container -->
        </nav>
        <!-- /NAVIGATION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div><h2>Orders</h2></div>
                    <div class="col-lg-3">
                    </div>

                    <table width="100%" class="table table-striped table-hover">
                        <thead style="background-color: #D10024; color: white;">
                            <tr>
                                <th style="text-align: center">ID</th>
                                <th style="text-align: center">Image</th>
                                <th style="text-align: center">Name</th>
                                <th style="text-align: center">Category</th>
                                <th style="text-align: center">Price</th>
                                <th style="text-align: center">Qty</th>
                                <th style="text-align: center">Total</th>
                                <th style="text-align: center">Order date</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="lastOrderId" value="${null}" />
                            <c:set var="lastTotal" value="${null}" />
                            <c:set var="lastDate" value="${null}" />
                            <c:forEach items="${order}" var="p">
                                <tr>
                                    <c:choose>
                                        <c:when test="${not empty lastOrderId and lastOrderId == p.order.id}">
                                            <td></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${p.order.id}</td>
                                            <c:set var="lastOrderId" value="${p.order.id}" />
                                        </c:otherwise>
                                    </c:choose>
                                    <td style="text-align: center"><img src="${p.product.image}" alt="${p.product.name}" width="30px;"/></td>
                                    <td>${p.product.name}</td>
                                    <td>${p.product.category.name}</td>
                                    <td style="text-align: center">${p.price}</td>
                                    <td style="text-align: center">${p.quantity}</td>
                                    <c:choose>
                                        <c:when test="${not empty lastTotal and lastTotal == p.order.totalMoney}">
                                            <td></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>$<fmt:formatNumber pattern="##.##" value="${p.order.totalMoney}"/></td>
                                            <c:set var="lastTotal" value="${p.order.totalMoney}" />
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${not empty lastDate and lastDate == p.order.date}">
                                            <td></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${p.order.date}</td>
                                            <c:set var="lastDate" value="${p.order.date}" />
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${p.order.status == 0}">
                                            <td style="color: yellow;">Delivering</td>
                                        </c:when>
                                        <c:when test="${p.order.status == 1}">
                                            <td style="color: green;">Delivered</td>
                                        </c:when>
                                        <c:when test="${p.order.status == 2}">
                                            <td style="color: red;">Cancelled</td>
                                        </c:when>
                                        <c:when test="${p.order.status == 3}">
                                            <td style="color: orange;">Preparing</td>
                                        </c:when>
                                    </c:choose>
                                    <td>
                                        <a href="cancelorder?oid=${p.order.id}" class="checkout" data-toggle="modal" 
                                           onclick="return ${p.order.status == 3 ? 'true' : 'false'};">
                                            <i class="glyphicon glyphicon-remove-sign" data-toggle="tooltip" title="Cancel my order"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!--end information-->
                    <!-- store bottom filter -->
                    <div class="store-filter clearfix">
                        <ul class="store-pagination">
                            <c:forEach begin="1" end="${endP}" var="i">
                                <li class="${i == tagi ? 'active' : ''}"><a href="?index=${i}">${i}</a></li>
                                </c:forEach>
                        </ul> 
                    </div>
                    <!-- /store bottom filter -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- FOOTER -->
        <div id="footer">
            <%@include file="footer.jsp" %>
        </div>
        <!-- /FOOTER -->

        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>