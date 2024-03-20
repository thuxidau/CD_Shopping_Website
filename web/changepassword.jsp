<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Change Password</title>
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

        <style>
            .editform, input[type='password']{
                height: 40px;
                width: 100%;
            }
        </style>
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
                        <li class="active"><a  href="changepassword">Change password</a></li>
                        <li><a  href="orders">Your Order</a></li>
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
        <div class="section">
            <div class="container">
                <div class="row">
                    <!--profile-->
                    <div class="col-lg-3">
                    </div>
                    <!--end profile-->
                    <!--information-->
                    <div class="col-lg-9">
                        <div class="tab-content">
                            <form class="form" action="changepassword" method="post" id="registrationForm">
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label for="oldpassword"><h5>Enter your old password</h5></label>
                                        <input type="password" class="form-control editform" name="oldpassword" required="required">
                                        <input type="hidden" class="form-control" name="username" value="${sessionScope.account.username}"}>
                                        <br/>
                                        <h5 class="danger" style="color: #d10024;">${oldpasserror}</h5>
                                        <label for="newpassword"><h5>Enter new password</h5></label>
                                        <input type="password" class="form-control editform" name="newpassword" required="required">
                                        <br/>  
                                        <label for="cfnewpassword"><h5>Confirm your new password</h5></label>
                                        <input type="password" class="form-control editform" name="cfnewpassword" required="required">
                                        <br/>
                                        <h5 class="danger" style="color: #d10024;">${cfpasserror}</h5>
                                        
                                        <button class="btn btn-lg btn-success" type="submit" style="background-color: #d10024; border-color: #d10024"><a href="changepassword"><strong>Change</strong></a></button>
                                        <br/>
                                        <h5 class="success" style="color: #398439;">${passsuccess}</h5>
                                    </div>
                                </div>
                            </form>      
                        </div>
                    </div>
                    <!--end information-->
                </div>
            </div>
        </div>
        <div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>