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

        <title>View Cart</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

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
    </head>
    <body>
        <!-- HEADER -->
        <div>
            <%@include file="header.jsp"%>
        </div>
        <!-- /HEADER -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="table">
                        <!--title-->
                        <div class="col-lg-9 col-md-8">
                            <h2>Your Cart</h2>
                        </div>
                        <!--title-->

                        <div>
                            <br/><br/><br/>
                        </div>

                        <div class="col-lg-12">
                            <!--table of products-->
                            <table width="100%" class="table table-striped table-hover">
                                <thead style="background-color: #D10024; color: white;">
                                    <tr>
                                        <th style="text-align: center">Image</th>
                                        <th style="text-align: center">Name</th>
                                        <th style="text-align: center">Category</th>
                                        <th style="text-align: center">Price</th>
                                        <th style="text-align: center">Quantity</th>
                                        <th style="text-align: center">Total</th>
                                        <th style="text-align: center">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${sessionScope.cart.items}" var="p">
                                        <tr>
                                            <td style="text-align: center"><a href="detail?pid=${p.product.id}"><img src="${p.product.image}" width="100px;"/></a></td>
                                            <td><a href="detail?pid=${p.product.id}">${p.product.name}</a></td>
                                            <td style="text-align: center">${p.product.category.name}</td>
                                            <td style="text-align: center">$${p.price}</td>
                                            <td style="text-align: center">
                                                <div class="input-number" style="width: 70px; height: 40px;">
                                                    <span id="qty-down" class="qty-down"><a href="del?num=-1&pid=${p.product.id}">-</a></span>
                                                    <input class="input-lg" id="qty-input" type="text" readonly name="quantity" value="${p.quantity}" style="width: 70%; height: 40px; font-size: 13px;">
                                                    <span id="qty-up" class="qty-up"><a href="del?num=1&pid=${p.product.id}">+</a></span>
                                                </div>
                                            </td>
                                            <td style="text-align: center">$<fmt:formatNumber pattern="##.##" value="${p.price * p.quantity}"/></td>
                                            <td style="text-align: center">
                                                <form action="process" method="POST">
                                                    <a href="process?id=${p.product.id}" class="delete" data-toggle="modal" onclick="return confirm('Are you sure to remove this product?')"><i class="glyphicon glyphicon-trash" data-toggle="tooltip" title="Delete"></i></a>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="col-lg-10">
                                <h4>TOTAL: $<fmt:formatNumber pattern="##.##" value="${cart.getTotalMoney()}"/></h4>
                            </div>
                            <div style="text-align: right;">
                                <button class="btn btn-lg btn-success" type="submit" style="background-color: #d10024; border-color: #d10024; align-items: center;"><a href="checkout"><strong>Checkout</strong></a></button> 
                            </div>
                        </div>
                        <!--table of products-->
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
    </body>
</html>