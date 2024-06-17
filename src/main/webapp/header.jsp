<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Groove Galaxy Records</title>
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
            .products-grid {
                width: 99%;
                display: grid;
                grid-template-columns: repeat(4, 1fr);
                gap: 20px;
                row-gap: 50px;
                margin-left: 5px;
                margin-bottom: 50px;
            }
        </style>
    </head>
    <body>
        <header>
            <!-- TOP HEADER -->
            <div id="top-header">
                <div class="container">
                    <ul class="header-links pull-left">
                        <li><a href="#"><i class="fa fa-phone"></i>0987.654.321</a></li>
                        <li><a href="mailto:support@ggr.com"><i class="fa fa-envelope-o"></i>support@ggr.com</a></li>
                        <li><a href="https://maps.app.goo.gl/KHJU9BbDSqGMPwbo7" target="_blank"><i class="fa fa-map-marker"></i>2 Phạm Văn Đồng, Mai Dịch, Cầu Giấy, Hà Nội, Vietnam</a></li>
                    </ul>
                    <ul class="header-links pull-right">
                        <c:if test="${sessionScope.account!=null}">
                            <li>
                                <a href="myaccount" <i class="fa fa-user-o"></i> My Account</a> 
                            </li>
                            <li>
                                <a href="logout" <i class="fa fa-arrow-right"></i> Log out</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account == null}">
                            <li>
                                <a href="login" <i class="fa fa-user-o"></i> Log in</a> </li>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <!-- /TOP HEADER -->

            <!-- MAIN HEADER -->
            <div id="header">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- LOGO -->
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="#" class="logo">
                                    <a href="home"><img src="./img/category/logo.png" alt="" width="30%"></a>
                                </a>
                            </div>
                        </div>
                        <!-- /LOGO -->

                        <!-- SEARCH BAR -->
                        <div class="col-md-6">
                            <div class="header-search">
                                <form action="search" method="post">
                                    <input value="${ts}" name="txt" class="input" placeholder="Search here" style="border-bottom-left-radius: 30px; border-top-left-radius: 30px;">
                                    <button class="search-btn">Search</button>
                                </form>
                            </div>
                        </div>
                        <!-- /SEARCH BAR -->

                        <!-- ACCOUNT -->
                        <div class="col-md-3 clearfix">
                            <div class="header-ctn">
                                <!-- Wishlist -->
                                <div class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-heart-o"></i>
                                        <span>Your Wishlist</span>
                                        <div class="qty">${wlsize}</div>
                                    </a>
                                    <div class="cart-dropdown">
                                        <div class="cart-list">
                                            <c:forEach items="${sessionScope.wishlist.items}" var="i">
                                                <div class="product-widget">
                                                    <div class="product-img">
                                                        <img src="${i.product.image}" alt="${i.product.name}">
                                                    </div>
                                                    <div class="product-body">
                                                        <h3 class="product-name"><a href="detail?pid=${i.product.id}">${i.product.name}</a></h3>
                                                        <h4 class="product-price">$${i.price}</h4>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="cart-btns" style=" width: 607px;" >
                                            <a href="wishlist">View WishList</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Wishlist -->

                                <!-- Cart -->
                                <div class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Your Cart</span>
                                        <div class="qty">
                                            <!--<c:if test="${account != null}">
                                                ${size}
                                            </c:if>
                                            <c:if test="${account == null}">
                                                0
                                            </c:if> -->
                                            ${size}
                                        </div>
                                    </a>
                                    <div class="cart-dropdown">
                                        <div class="cart-list">
                                            <c:forEach items="${sessionScope.cart.items}" var="i">
                                                <div class="product-widget">
                                                    <div class="product-img">
                                                        <img src="${i.product.image}" alt="${i.product.name}">
                                                    </div>
                                                    <div class="product-body">
                                                        <h3 class="product-name"><a href="detail?pid=${i.product.id}">${i.product.name}</a></h3>
                                                        <h4 class="product-price"><span class="qty">${i.quantity}x</span>$${i.price}</h4>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="cart-summary">
                                            <small>${size} Item(s) selected</small>
                                            <h5>SUBTOTAL: $<fmt:formatNumber pattern="##.##" value="${cart.getTotalMoney()}"/></h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="viewcart">View Cart</a>
                                            <a href="checkout">Checkout<i class="fa fa-arrow-circle-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Cart -->

                                <!-- Menu Toogle -->
                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>
                                    </a>
                                </div>
                                <!-- /Menu Toogle -->
                            </div>
                        </div>
                        <!-- /ACCOUNT -->
                    </div>
                    <!-- row -->
                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->
        </header>
    </body>
</html>