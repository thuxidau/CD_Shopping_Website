<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>My Account</title>
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
        <div class="section">
            <div class="container">
                <div class="row">
                    <!--profile-->
                    <div><h2>My Profile</h2></div>
                    <div class="col-lg-3">
                        <div class="text-center">
                            <img src="${user.image}" class="avatar img-circle img-thumbnail" alt="avatar">
                            <br/>
                            <h6>Upload a different photo...</h6>
                            <input type="file" class="text-center center-block file-upload">
                        </div></hr><br>
                        <ul class="list-group">
                            <li class="list-group-item text-muted">Activity <i class="fa fa-dashboard fa-1x"></i></li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong>Ordered</strong></span> 125</li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong>Cart</strong></span> ${size}</li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong>Wishlist</strong></span> ${wlsize}</li>
                        </ul> 
                    </div>
                    <!--end profile-->

                    <!--information-->
                    <div class="col-lg-9">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
                            <li><a data-toggle="tab" href="#messages">Menu 1</a></li>
                            <li><a data-toggle="tab" href="#settings">Menu 2</a></li>
                        </ul>
                        <div class="tab-content">
                            <hr/><hr/>
                            <div class="form-group">

                                <div class="col-xs-6">
                                    <label for="username"><h4>Username</h4></label>
                                    <input value="${user.username}" type="text" class="form-control" name="username" id="username" readonly placeholder="Username" title="Enter your username.">
                                    <br>
                                    <label for="phone"><h4>Phone Number</h4></label>
                                    <input value="${user.phoneNumber}" type="tel" class="form-control" name="phone" id="phone" placeholder="Phone number" title="Enter your phone number.">
                                    <br>
                                    <label for="gender"><h4>Gender</h4></label> <br/>
                                    <input type="radio" name="gender"  ${user.gender == 0 ? 'checked' : ''} > Male       
                                    <input type="radio" name="gender"  ${user.gender == 1 ? 'checked' : ''} > Female       
                                    <input type="radio" name="gender"  ${user.gender == 2 ? 'checked' : ''} > Other 
                                         
                                    <br><br/>
                                    <label for="dob"><h4>Date of birth</h4></label>
                                    <input value="${user.dob}"  type="date" class="form-control" name="dob" id="dob" placeholder="Date of birth" title="Enter your date of birth.">

                                </div>
                            </div>

                            <div class="form-group">

                                <div class="col-xs-6">
                                    <label for="fullname"><h4>Full name</h4></label>
                                    <input value="${user.fullname}"  type="text" class="form-control" name="fullname" id="fullname" placeholder="Full Name" title="Enter your last name">
                                    <br>
                                    <label for="email"><h4>Email</h4></label>
                                    <input value="${user.email}"  type="text" class="form-control" name="email" id="email" placeholder="Email" title="Enter your email.">
                                    <br/>
                                    <label for="location"><h4>Location</h4></label>
                                    <input value="${user.location}"  type="text" class="form-control" name="location" id="location" placeholder="Location" title="Enter a location">

                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-xs-12">
                                    <br>
                                    <button class="btn btn-lg btn-success" type="submit" style="background-color: #d10024; border-color: #d10024"><a href="editprofile"><i class="glyphicon glyphicon-ok-sign"></i> Save</a></button> 
                                </div>
                            </div>
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