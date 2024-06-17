<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Statistic</title>
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
        <div>
            <%@include file="header.jsp"%>
        </div>
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
                        <li><a  href="orders">Your Order</a></li>
                        <c:if test="${sessionScope.account.roleId == 0}">
                            <li><a href="accountlist">Account List</a></li>
                            <li class="active"><a href="statistic">Statistic</a></li>
                        </c:if> 
                    </ul>
                    <!-- /NAV -->
                </div>
                <!-- /responsive-nav -->
            </div>
            <!-- /container -->
        </nav>
        <!-- /NAVIGATION -->

        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <table border="2" style="border-color: #D10024; width: fit-content;">
                            <tr>
                                <td>
                                    <img src="./img/category/dvd.png" width="15%;"/>
                                    <h3>Total products: ${requestScope.totalproduct}</h3>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-lg-3">
                        <table border="2" style="border-color: #D10024; width: fit-content;">
                            <tr>
                                <td>
                                    <img src="https://cdn-icons-png.flaticon.com/512/9226/9226433.png" width="15%;"/>
                                    <h3>In Stock: ${requestScope.instock}</h3>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-lg-3">
                        <table border="2" style="border-color: #D10024; width: fit-content;">
                            <tr>
                                <td>
                                    <img src="./img/category/sold.png" width="15%;"/>
                                    <h3>Product sold: ${sold}</h3>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-lg-3">
                        <table border="2" style="border-color: #D10024; width: fit-content;">
                            <tr>
                                <td>
                                    <img src="./img/category/sale.webp" width="15%;"/>
                                    <h3>Earn: $<fmt:formatNumber pattern="##.##" value="${earn}"/></h3>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-lg-12" style="margin-top: 50px;">
                        <div class="col-lg-3">
                            <table border="2" style="border-color: #D10024; width: fit-content;">
                                <tr>
                                    <td>
                                        <img src="./img/category/revenue_7044465.png" width="15%;"/>
                                        <h3>Profit (25%): $<fmt:formatNumber pattern="##.##" value="${earn * 0.25}"/></h3>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-lg-3">
                            <table border="2" style="border-color: #D10024; width: fit-content;">
                                <tr>
                                    <td>
                                        <img src="https://cdn-icons-png.flaticon.com/512/3496/3496155.png" width="15%;"/>
                                        <h3>Orders: ${orders}</h3>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-lg-3">
                            <table border="2" style="border-color: #D10024; width: fit-content;">
                                <tr>
                                    <td>
                                        <img src="https://cdn-icons-png.flaticon.com/512/456/456283.png" width="15%;"/>
                                        <h3>Users: ${user}</h3>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-lg-12">
                            <hr/><hr/>
                        </div>
                        <div class="col-lg-12">
                            <div class="title" style="text-align: center;">
                                <h2>Top 10 Best-Selling Products</h2>
                            </div>
                            <div class="table">
                                <table class="table table-striped table-hover">
                                    <thead style="background-color: #D10024; color: white;">
                                        <tr>
                                            <th>ID</th>
                                            <th>Image</th>
                                            <th>Name</th>
                                            <th>Category</th>
                                            <th>Price</th>
                                            <th>Qty Sold</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${bestsell}" var="b">
                                            <tr>
                                                <td>${b.id}</td>
                                                <td><img src="${b.image}" width="60px;"/></td>
                                                <td>${b.name}</td>
                                                <td>${b.category.name}</td>
                                                <td>${b.price}</td>
                                                <td>${b.qtysold}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <hr/><hr/>
                        </div>
                        <div class="col-lg-12">
                            <div class="title" style="text-align: center;">
                                <h2>Top 5 Most Spending User</h2>
                            </div>
                            <div class="table">
                                <table class="table table-striped table-hover">
                                    <thead style="background-color: #D10024; color: white;">
                                        <tr>
                                            <th>Username</th>
                                            <th>Image</th>
                                            <th>Full name</th>
                                            <th>Phone</th>
                                            <th>Spending</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${mostspend}" var="b">
                                            <tr>
                                                <td>${b.username}</td>
                                                <td><img src="${empty b.image ? 'https://i.pinimg.com/originals/f1/0f/f7/f10ff70a7155e5ab666bcdd1b45b726d.jpg' : b.image}" width="80px"/></td>
                                                <td>${b.fullname}</td>
                                                <td>${b.phoneNumber}</td>
                                                <td>$<fmt:formatNumber pattern="##.##" value="${b.total}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>