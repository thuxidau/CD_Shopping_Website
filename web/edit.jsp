<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Edit Product</title>
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
            .editform, input[type='text'], input[type='number']{
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

        <!--edit product-->
        <div id="editProduct">
            <div class="modal-dialog" style="width: 40%">
                <div class="modal-content">
                    <form action="edit" method="post">
                        <div class="modal-header" style="background-color: #D10024; display: flex; justify-content: center; align-items: center; position: relative;">						
                            <h4 class="modal-title" style="color: white;">Edit Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white; font-size: 250%; position: absolute; right: 10px;">×</button>
                        </div>

                        <div class="modal-body">	
                            <div class="form-group">
                                <label>ID</label>
                                <input value="${detail.id}" name="id" type="number" class="form-control editform" readonly required>
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input value="${detail.name}" name="name" type="text" class="form-control editform" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input value="${detail.price}" name="price" type="text" class="form-control editform" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="number" value="${detail.quantity}" min="0" class="form-control editform" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control editform" required>${detail.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input value="${detail.image}" name="image" type="text" placeholder="Enter image URL" class="form-control editform" required>
                            </div>                            
                            <div class="form-group">
                                <label>Category</label>  
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${requestScope.category}" var="c">
                                        <option ${c.id == detail.category.id ? 'selected' : ''} value="${c.id}">${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Country</label> &nbsp;
                                <select name="country" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${requestScope.country}" var="ct">
                                        <option ${ct.id == detail.country.id  ? 'selected' : ''} value="${ct.id}">${ct.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Status</label>&nbsp;
                                <input type="radio" name="status" value="1" ${detail.status ? 'checked' : ''}/> In Stock &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="status" value="0" ${detail.status ? '' : 'checked'}/> Out of Stock
                            </div>
                        </div>
                        <div class="modal-footer">
                            <form name="form" action="manage" method="POST">
                                <input onclick="manage()" type="submit" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            </form>
                            <input type="submit" class="btn btn-danger" value="Edit">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--edit product-->

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
            function manage(){
                document.form.action = "manage";
                document.form.submit();
            }
        </script>
    </body>
</html>