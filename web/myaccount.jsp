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

        <style>
            .editform, input[type='radio'] {
                height: 18px;
                width: 18px;
            }
            .editform, input[type='text'], input[type='tel'], input[type='date'] {
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
                        <li class="active"><a  href="myaccount">Home</a></li>
                        <li><a  href="changepassword">Change password</a></li>
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
                    <div><h2>My Profile</h2></div>
                    <div class="col-lg-3">
                        <div class="text-center">
                            <img src="${empty account.image ? 'https://i.pinimg.com/originals/f1/0f/f7/f10ff70a7155e5ab666bcdd1b45b726d.jpg' : account.image}" class="avatar img-circle img-thumbnail" alt="avatar" style="border-radius: 50%;">
                            <br/>
                            <h6>Upload a different photo...</h6>
                            <input type="file" class="text-center center-block file-upload">
                        </div>
                    </div>
                    <!--end profile-->

                    <!--information-->
                    <div class="col-lg-9">
                        <div class="tab-content">
                            <form id="form" action="editprofile" method="post">
                                <div class="form-group"> 
                                    <div class="col-xs-6">
                                        <label for="username"><h4>Username</h4></label>
                                        <input value="${account.username}" readonly type="text" class="form-control" name="username" id="username">
                                        <br>
                                        <label for="phone"><h4>Phone Number</h4></label>
                                        <input value="${account.phoneNumber}" readonly type="tel" class="form-control editform" name="phone" id="phone" placeholder="Phone number" title="Enter your phone number.">
                                        <br>
                                        <label for="gender"><h4>Gender</h4></label> <br/>
                                        <input type="radio" name="gender" class="editform" disabled ${account.gender == 0 ? 'checked' : ''} value="0"> Male       
                                        <input type="radio" name="gender" class="editform" disabled ${account.gender == 1 ? 'checked' : ''} value="1"> Female       
                                        <input type="radio" name="gender" class="editform" disabled ${account.gender == 2 ? 'checked' : ''} value="2"> Other       
                                        <br><br/>
                                        <label for="dob"><h4>Date of birth</h4></label>
                                        <input value="${account.dob}" readonly type="date" class="form-control editform" name="dob" id="dob" placeholder="Date of birth" title="Enter your date of birth.">

                                    </div>
                                </div>

                                <div class="form-group">

                                    <div class="col-xs-6">
                                        <label for="fullname"><h4>Full name</h4></label>
                                        <input value="${account.fullname}" readonly type="text" class="form-control editform" name="fullname" id="fullname" placeholder="Full Name" title="Enter your last name">
                                        <br>
                                        <label for="email"><h4>Email</h4></label>
                                        <input value="${account.email}" readonly type="text" class="form-control editform" name="email" id="email" placeholder="Email" title="Enter your email.">
                                        <br/>
                                        <label for="address"><h4>Address</h4></label>
                                        <input value="${account.address}" readonly type="text" class="form-control editform" name="address" placeholder="Address" title="Enter an address">
                                        <br/>
                                        <label for="location"><h4>City</h4></label>
                                        <input value="${account.location}" readonly type="text" class="form-control editform" name="location" id="location" placeholder="Location" title="Enter city">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <br>
                                        <button onclick="changetype(this)" class="btn btn-lg btn-success" id="edit" type="button" style="background-color: #d10024; border-color: #d10024">Edit</button> 
                                        <br/>
                                        <h4 style="color: green;">${msg}</h4>
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
        <script type="text/javascript">
            function changetype(button) {
                var inputElement = document.querySelectorAll(".editform");
                if (button.id === "edit") {
                    button.id = "save";
                    button.textContent = "Save";
                    inputElement.forEach(x => {
                        x.readOnly = false;
                        x.disabled = false;
                    });
                } else {
                    document.getElementById("form").submit();
                    button.id = "edit";
                    button.textContent = "Edit";
                    inputElement.forEach(x => {
                        x.readOnly = true;
                        x.disabled = true;
                    });
                }
            }
        </script>
    </body>
</html>