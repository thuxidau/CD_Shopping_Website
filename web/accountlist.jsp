<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Account List</title>
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
                        <li><a  href="orders">Your Order</a></li>
                        <c:if test="${sessionScope.account.roleId == 0}">
                            <li class="active"><a href="accountlist">Account List</a></li>
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
                    <div><h2>Account List</h2></div>
                    <div class="col-lg-3">
                    </div>
                    <table width="100%" class="table table-striped table-hover">
                        <thead style="background-color: #D10024; color: white;">
                            <tr>
                                <th style="text-align: center">Username</th>
                                <th style="text-align: center">Full name</th>
                                <th style="text-align: center">Phone</th>
                                <th style="text-align: center">Email</th>
                                <th style="text-align: center">Gender</th>
                                <th style="text-align: center">Dob</th>
                                <th style="text-align: center">Role</th>
                                <th style="text-align: center">Address</th>
                                <th style="text-align: center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${acclist}" var="p">
                                <tr>
                                    <td>${p.username}</td>
                                    <td>${p.fullname}</td>
                                    <td>${p.phoneNumber}</td>
                                    <td>${p.email}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.gender == 0}">
                                                Male
                                            </c:when>
                                            <c:when test="${p.gender == 1}">
                                                Female
                                            </c:when>
                                            <c:otherwise>
                                                Other
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${p.dob}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.roleId == 0}">
                                                Admin
                                            </c:when>
                                            <c:otherwise>
                                                User
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${p.address}, ${p.location}</td>
                                    <td style="text-align: center;">
                                        <a href="makeadmin?user=${p.username}" class="delete" data-toggle="modal" onclick="return confirm('Are you sure to make this account admin?')"><i class="glyphicon glyphicon-user" data-toggle="tooltip" title="Make admin"></i></a>
                                        <a href="removeadmin?user=${p.username}" class="delete" data-toggle="modal" onclick="return confirm('Are you sure to remove this account as admin?')"><i class="glyphicon glyphicon-remove-circle" data-toggle="tooltip" title="Remove admin"></i></a>
                                        <a href="deleteaccount?user=${p.username}" class="delete" data-toggle="modal" onclick="return confirm('Are you sure to remove this account?')"><i class="glyphicon glyphicon-remove" data-toggle="tooltip" title="Remove account"></i></a>
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