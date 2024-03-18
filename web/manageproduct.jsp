<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Manage Product</title>
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
            .editform, input[type='text'], input[type='number'] {
                height: 40px;
                width: 100%;
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
                        <li><a href="home">Home</a></li>
                        <li ><a href="category">Category</a></li>
                        <c:if test="${sessionScope.account.roleId == 0}">
                        <li class="active"><a href="manage">Manage Product</a></li>
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
                    <div class="table">
                        <!--title-->
                        <div class="col-lg-10 col-md-8">
                            <h3>Manage product</h3>
                        </div>
                        <div class="col-lg-2 col-md-4">
                            <a href="#addProduct" class="btn btn-danger" data-toggle="modal" ><i class="glyphicon glyphicon-plus-sign"></i> <span>Add New Product</span></a>					
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
                                        <th style="text-align: center">ID</th>
                                        <th style="text-align: center">Name</th>
                                        <th style="text-align: center">Price</th>
                                        <th style="text-align: center">Quantity</th>
                                        <th style="text-align: center">Image</th>
                                        <th style="text-align: center">Description</th>
                                        <th style="text-align: center">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.product}" var="p">
                                        <tr>
                                            <td>${p.id}</td>
                                            <td>${p.name}</td>
                                            <td>$${p.price}</td>
                                            <td>${p.quantity}</td>
                                            <td><img src="${p.image}" width="150px"/></td>
                                            <td>${p.description}</td>
                                            <td>
                                                <a href="edit?pid=${p.id}" class="edit" data-toggle="modal"><i class="glyphicon glyphicon-edit" data-toggle="tooltip" title="Edit"></i></a> &nbsp;&nbsp;&nbsp;
                                                <a href="delete?pid=${p.id}" class="delete" data-toggle="modal" onclick="return confirm('Are you sure to delete this product?')"><i class="glyphicon glyphicon-trash" data-toggle="tooltip" title="Delete"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!--table of products-->
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
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!--add product-->
        <div id="addProduct" class="modal fade">
            <div class="modal-dialog" style="width: 30%">
                <div class="modal-content">
                    <form action="add" method="post">
                        <div class="modal-header" style="background-color: #D10024; display: flex; justify-content: center; align-items: center; position: relative;">						
                            <h4 class="modal-title" style="color: white;">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white; font-size: 250%; position: absolute; right: 10px;">×</button>
                        </div>

                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control editform" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control editform" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="number" value="1" min="1" class="form-control editform" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control editform" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" placeholder="Enter image URL" class="form-control editform" required>
                            </div>                            
                            <div class="form-group">
                                <label>Category</label> &nbsp;
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${requestScope.category}" var="c">
                                        <option value="${c.id}">${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Country</label> &nbsp;
                                <select name="country" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${requestScope.country}" var="c">
                                        <option value="${c.id}">${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--add product-->

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
