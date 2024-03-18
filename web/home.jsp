<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
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

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">🆕 New Arrivals</h3>
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab1" class="tab-pane active">
                                    <div class="products-slick" data-nav="#slick-nav-1">
                                        <!-- product -->
                                        <c:forEach items="${requestScope.newa}" var="pr">
                                            <div class="product">
                                                <div class="product-img">
                                                    <a href="detail?pid=${pr.id}"><img src="${pr.image}" alt="${pr.name}" width="100%;"></a>
                                                </div>
                                                <div class="product-body">
                                                    <p class="product-category">${pr.category.name}</p>
                                                    <h3 class="product-name"><a href="detail?pid=${pr.id}">${pr.name}</a></h3>
                                                    <h4 class="product-price">$${pr.price}</h4>
                                                    <div class="product-btns">
                                                        <button onclick="addtowishlist(${pr.id})" class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                        <button class="quick-view"><a href="detail?pid=${pr.id}"><i class="fa fa-eye"></i></a><span class="tooltipp">quick view</span></button>
                                                    </div>
                                                </div>
                                                <div class="add-to-cart">
                                                    <form name="form" action="addtocarthome" method="post">
                                                        <input type="hidden" name="quantity" value="1"/>
                                                        <input type="hidden" name="price" value="${pr.price}"/>
                                                        <input type="hidden" name="pid" value="${pr.id}"/>
                                                        <button type="submit" class="add-to-cart-btn" onclick="addtocarthome(${pr.id})"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                    </form>
                                                </div>                                           
                                            </div>
                                            <!-- /product -->
                                        </c:forEach>
                                    </div>
                                    <div id="slick-nav-1" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">📢 Limited Edition</h3>
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab2" class="tab-pane fade in active">
                                    <div class="products-slick" data-nav="#slick-nav-2">
                                        <!-- product -->
                                        <c:forEach items="${requestScope.trend}" var="pr">
                                            <div class="product">
                                                <div class="product-img">
                                                    <a href="detail?pid=${pr.id}"><img src="${pr.image}" alt="${pr.name}" width="100%"></a>
                                                </div>
                                                <div class="product-body">
                                                    <p class="product-category">${pr.category.name}</p>
                                                    <h3 class="product-name"><a href="detail?pid=${pr.id}">${pr.name}</a></h3>
                                                    <h4 class="product-price">$${pr.price} <!--<del>$<fmt:formatNumber value="${pr.price * 1.3}" type="number" minFractionDigits="2" maxFractionDigits="2"/></del>--> </h4>
                                                    <div class="product-btns">
                                                        <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                        <button class="quick-view"><a href="detail?pid=${pr.id}"><i class="fa fa-eye"></i></a><span class="tooltipp">quick view</span></button>
                                                    </div>
                                                </div>
                                                <div class="add-to-cart">
                                                    <form name="form" action="addtocarthome" method="post">
                                                        <input type="hidden" name="quantity" value="1"/>
                                                        <input type="hidden" name="price" value="${pr.price}"/>
                                                        <input type="hidden" name="pid" value="${pr.id}"/>
                                                        <button type="submit" class="add-to-cart-btn" onclick="addtocarthome(${pr.id})"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <!-- /product -->
                                    </div>
                                    <div id="slick-nav-2" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- /Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">🔥 Best Selling</h3>
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab2" class="tab-pane fade in active">
                                    <div class="products-slick" data-nav="#slick-nav-3">
                                        <!-- product -->
                                        <c:forEach items="${requestScope.bestsell}" var="pr">
                                            <div class="product">
                                                <div class="product-img">
                                                    <a href="detail?pid=${pr.id}"><img src="${pr.image}" alt="${pr.name}" width="100%"></a>
                                                </div>
                                                <div class="product-body">
                                                    <p class="product-category">${pr.category.name}</p>
                                                    <h3 class="product-name"><a href="detail?pid=${pr.id}">${pr.name}</a></h3>
                                                    <h4 class="product-price">$${pr.price} <!--<del>$<fmt:formatNumber value="${pr.price * 1.3}" type="number" minFractionDigits="2" maxFractionDigits="2"/></del>--> </h4>
                                                    <div class="product-btns">
                                                        <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                        <button class="quick-view"><a href="detail?pid=${pr.id}"><i class="fa fa-eye"></i></a><span class="tooltipp">quick view</span></button>
                                                    </div>
                                                </div>
                                                <div class="add-to-cart">
                                                    <form name="form" action="addtocarthome" method="post">
                                                        <input type="hidden" name="quantity" value="1"/>
                                                        <input type="hidden" name="price" value="${pr.price}"/>
                                                        <input type="hidden" name="pid" value="${pr.id}"/>
                                                        <button type="submit" class="add-to-cart-btn" onclick="addtocarthome(${pr.id})"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <!-- /product -->
                                    </div>
                                    <div id="slick-nav-3" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- /Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-4 col-xs-6">
                        <div class="section-title">
                            <h4 class="title">US-UK</h4>
                            <div class="section-nav">
                                <div id="slick-nav-3" class="products-slick-nav"></div>
                            </div>
                        </div>

                        <div class="products-widget-slick" data-nav="#slick-nav-3">
                            <div>
                                <!-- product widget -->
                                <c:forEach items="${requestScope.usuk}" var="pr">
                                    <div class="product-widget">
                                        <div class="product-img">
                                            <a href="detail?pid=${pr.id}"><img src="${pr.image}" alt="${pr.name}" width="100%;"></a>
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category">${pr.category.name}</p>
                                            <h3 class="product-name"><a href="detail?pid=${pr.id}">${pr.name}</a></h3>
                                            <h4 class="product-price">$${pr.price}</h4>
                                        </div>
                                    </div>
                                </c:forEach>
                                <!-- /product widget -->
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-xs-6">
                        <div class="section-title">
                            <h4 class="title">K-POP</h4>
                            <div class="section-nav">
                                <div id="slick-nav-4" class="products-slick-nav"></div>
                            </div>
                        </div>

                        <div class="products-widget-slick" data-nav="#slick-nav-4">
                            <div>
                                <!-- product widget -->
                                <c:forEach items="${requestScope.kpop}" var="pr">
                                    <div class="product-widget">
                                        <div class="product-img">
                                            <a href="detail?pid=${pr.id}"><img src="${pr.image}" alt="${pr.name}" width="100%;"></a>
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category">${pr.category.name}</p>
                                            <h3 class="product-name"><a href="detail?pid=${pr.id}">${pr.name}</a></h3>
                                            <h4 class="product-price">$${pr.price}</h4>
                                        </div>
                                    </div>
                                </c:forEach>
                                <!-- /product widget -->
                            </div>
                        </div>
                    </div>

                    <div class="clearfix visible-sm visible-xs"></div>

                    <div class="col-md-4 col-xs-6">
                        <div class="section-title">
                            <h4 class="title">V-POP</h4>
                            <div class="section-nav">
                                <div id="slick-nav-5" class="products-slick-nav"></div>
                            </div>
                        </div>

                        <div class="products-widget-slick" data-nav="#slick-nav-5">
                            <div>
                                <!-- product widget -->
                                <c:forEach items="${requestScope.vpop}" var="pr">
                                    <div class="product-widget">
                                        <div class="product-img">
                                            <a href="detail?pid=${pr.id}"><img src="${pr.image}" alt="${pr.name}" width="100%;"></a>
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category">${pr.category.name}</p>
                                            <h3 class="product-name"><a href="detail?pid=${pr.id}">${pr.name}</a></h3>
                                            <h4 class="product-price">$${pr.price}</h4>
                                        </div>
                                    </div>
                                </c:forEach>
                                <!-- /product widget -->
                            </div>
                        </div>
                    </div>

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
        <script type="text/javascript">
            function addtocarthome(id) {
                document.form.action = "addtocarthome?pid=" + id;
                document.form.submit();
            }
        </script> 
        <script type="text/javascript">
            function addtocarthome(id) {
                document.form.action = "addtowishlist?pid=" + id;
                document.form.submit();
            }
        </script> 
    </body>
</html>