<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Search for ${ts}</title>

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
                        <li class="active"><a href="home">Home</a></li>
                        <li><a href="category">Category</a></li>
                            <c:if test="${sessionScope.account.roleId == 0}">
                            <li><a href="manage">Manage Product</a></li>
                            <li><a href="customerorders">Customer Orders</a></li>
                            </c:if>
                    </ul>
                    <!-- /NAV -->
                </div>
                <!-- /responsive-nav -->
            </div>
            <!-- /container -->
        </nav>
        <!-- /NAVIGATION -->

        <!-- BREADCRUMB -->
        <div id="breadcrumb" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <ul class="breadcrumb-tree">
                            <li class="active">Showing ${requestScope.count} Result(s)</li>
                        </ul>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /BREADCRUMB -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- STORE -->
                    <div id="store">
                        <!--product view-->
                        <div class="col-lg-12">
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="products-tabs">
                                        <!-- tab -->
                                        <div id="tab1" class="tab-pane active">
                                            <div class="products-grid" data-nav="#slick-nav-1">
                                                <!-- product -->
                                                <c:forEach items="${product}" var="pr">
                                                    <div class="product">
                                                        <div class="product-img">
                                                            <img src="${pr.image}" alt="${pr.name}">
                                                        </div>
                                                        <div class="product-body">
                                                            <p class="product-category">${pr.category.name}</p>
                                                            <h3 class="product-name"><a href="detail?pid=${pr.id}">${pr.name}</a></h3>
                                                            <h4 class="product-price">$${pr.price}</h4>
                                                            <div class="product-btns">
                                                                <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                                <button class="quick-view"><a href="detail?pid=${pr.id}"><i class="fa fa-eye"></i></a><span class="tooltipp">quick view</span></button>
                                                            </div>
                                                        </div>
                                                        <div class="add-to-cart">
                                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                        </div>
                                                    </div>
                                                    <!-- /product -->
                                                </c:forEach>
                                            </div>
                                            <!-- store bottom filter -->
                                            <div class="store-filter clearfix">
                                                <ul class="store-pagination">
                                                    <c:forEach begin="1" end="${endP}" var="i">
                                                        <li class="${i == tagi ? 'active' : ''}"><a href="?txt=${ts}&index=${i}">${i}</a></li>
                                                    </c:forEach>
                                                </ul> 
                                            </div>
                                            <!-- /store bottom filter -->
                                        </div>
                                        <!-- /tab -->
                                    </div>
                                </div>
                            </div>
                            <!--product view-->
                        </div>
                        <!-- /STORE -->
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