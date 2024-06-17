<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>${requestScope.detail.name}</title>

        <link rel="icon" href="./img/category/logo.png" type="image/png"/>

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
            #qty-input {
                width: 70px;
                height: 40px;
                border-bottom-right-radius: 0;
                border-top-right-radius: 0;
                font-size: 14px;
            }
            .qty-down, .qty-up{
                border-bottom-right-radius: 5px;
                border-top-right-radius: 5px;
            }
            #addtocart{
                background-color: transparent;
                border-color: transparent; 
                font-size: 12px;
            }
            #addtocart :hover{
                color: #D10024;
            }
        </style>
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
                    <!-- Product main img -->
                    <div class="col-lg-5">
                        <div id="product-main-img">
                            <div class="product-preview">
                                <img src="${detail.image}" alt="${detail.name}">
                            </div>
                        </div>
                    </div>
                    <!-- /Product main img -->

                    <!-- Product details -->
                    <div class="col-lg-7">
                        <div class="product-details">

                            <form name="form" action="addtocart?pid=${detail.id}" method="POST">
                                <input type="hidden" name="pid" value="${detail.id}"/>
                                <h2 class="product-name">${detail.name}</h2>
                                <div>
                                    <input type="hidden" name="price" value="${detail.price}"/>
                                    <h3 class="product-price">$${detail.price}</h3>
                                    <span class="product-available">${detail.status?"In Stock":"Out of Stock"}</span>
                                </div>
                                <p>${detail.description}</p>
                                <div class="add-to-cart">
                                    <div class="qty-label">
                                        Quantity &nbsp;
                                        <div class="input-number">
                                            <input class="input-lg" id="qty-input" type="text" readonly name="quantity" value="1">
                                            <span id="qty-up" class="qty-up">+</span>
                                            <span id="qty-down" class="qty-down">-</span>
                                        </div>
                                    </div>
                                    <input type="hidden" name="category" value="${detail.category.id}"/>
                                    <button id="add-to-cart-btn" class="add-to-cart-btn" onclick="addtocart(${detail.id})"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                </div>
                            </form>
                            <form name="form" action="addtowishlist?pid=${detail.id}" method="POST">
                                
                                <ul class="product-btns">
                                    <li><button onclick="addtowishlist(${detail.id})" id="addtocart"><i class="fa fa-heart-o"></i> &nbsp; ADD TO WISHLIST</button></li>
                                </ul>
                            </form>
                            <ul class="product-links">
                                <li>Category:</li>
                                <li><a href="category?cid=${detail.category.id}">${detail.category.name}</a></li>
                            </ul>


                            <ul class="product-links">
                                <li>Share:</li>
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                                <li><a href="#"><i class="fa fa-envelope"></i></a></li>
                            </ul>

                        </div>
                    </div> 
                    <!-- /Product details -->
                </div>
                <!--row--> 
            </div>
            <!--container--> 
        </div>
        <!--SECTION--> 

        <!--Section--> 
        <div class="section">
            <!--container--> 
            <div class="container">
                <!--row--> 
                <div class="row">

                    <div class="col-md-12">
                        <div class="section-title text-center">
                            <h3 class="title">Related Products</h3>
                        </div>
                    </div>

                    <!--product--> 


                    <c:forEach items="${related}" var="pr">
                        <div class="col-md-3 col-xs-6">
                            <div class="product">
                                <div class="product-img">
                                    <a href="detail?pid=${pr.id}"><img src="${pr.image}" alt="${pr.name}" width="100%"></a>
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
                                    <form name="form" action="addtocartproduct" method="post">
                                        <input type="hidden" name="quantity" value="1"/>
                                        <input type="hidden" name="price" value="${pr.price}"/>
                                        <input type="hidden" name="pid" value="${pr.id}"/>
                                        <button type="submit" class="add-to-cart-btn" onclick="addtocartproduct(${pr.id})"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>   
                    <!--/product--> 
                </div>
                <!--/row -->
            </div>
            <!--   /container -->
        </div>
        <!-- /Section -->


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
        <script>
            var input = document.getElementById('qty-input');
            var up = document.getElementById('qty-up');
            var down = document.getElementById('qty-down');

            up.addEventListener('click', function () {
                if (input.value < 10) {
                    input.value = parseInt(input.value) + 1;
                }
                checkInput();
            });

            down.addEventListener('click', function () {
                if (input.value > 1) {
                    input.value = parseInt(input.value) - 1;
                }
                checkInput();
            });

            function checkInput() {
                if (input.value >= 10) {
                    up.classList.add('disabled');
                } else {
                    up.classList.remove('disabled');
                }

                if (input.value <= 1) {
                    down.classList.add('disabled');
                } else {
                    down.classList.remove('disabled');
                }
            }
            checkInput();
        </script>
        <script type="text/javascript">
            function addtocartproduct(id) {
                document.form.action = "addtocartproduct?pid=" + id;
                document.form.submit();
            }
        </script>  
        <script type="text/javascript">
            function addtowishlist(id) {
                document.form.action = "addtowishlist?prid=" + id;
                document.form.submit();
            }
        </script>
        <script>
            window.onload = function () {
                var productStatus = ${detail.status};
                var btn = document.getElementById("add-to-cart-btn");

                if (productStatus === false) {
                    btn.disabled = true;
                } else {
                    btn.disabled = false;
                }
            }
        </script>
        <script type="text/javascript">
            function addtocart(id) {
                document.form.action = "addtocart?pid=" + id;
                document.form.submit();
            }
        </script>
    </body>
</html>