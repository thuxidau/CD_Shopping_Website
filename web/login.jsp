<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="icon" href="./img/category/logo.png" type="image/png">
        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    </head>
    <body>
        <!-- Login 13 - Bootstrap Brain Component -->
        <section class="bg-light py-3 py-md-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-sm-10 col-md-8 col-lg-5 col-xl-5 col-xxl-4">
                        <div class="card border border-light-subtle rounded-3 shadow-sm">
                            <div class="card-body p-3 p-md-4 p-xl-5">
                                <div class="text-center mb-3">
                                    <a href="home">
                                        <img src="./img/category/logo.png" alt="Groove Galaxy Records" width="20%">
                                    </a>
                                </div>
                                <h1 class="fs-6 fw-normal text-center text-secondary mb-4" style="font-size: 120% !important;">Sign in to your account</h1>
                                <form action="login" method="post">
                                    <div class="row gy-2 overflow-hidden">
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" name="username" id="username" value="${username}" placeholder="haveagoodday" required style="height: 55px; width: 100%;">
                                                <label for="username" class="form-label">Username</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3" style="display: flex;">
                                                <input type="password" class="form-control" name="password" id="password" value="${password}" placeholder="Password" required style="height: 55px; width: 90%;">
                                                <label for="password" class="form-label">Password</label>
                                                <button id="togglePassword" class="btn btn-outline-secondary" type="button"><i class="fa fa-eye-slash" aria-hidden="true"></i></button>
                                                <label for="password" class="form-label">Password</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-flex gap-2 justify-content-between">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="1" name="rememberMe" id="rememberMe">
                                                    <label class="form-check-label text-secondary" for="rememberMe">
                                                        Remember me
                                                    </label>
                                                </div>
                                                <a href="#!" class="link-primary text-decoration-none" style="color: #bd0223 !important;">Forgot password?</a>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid my-3">
                                                <button class="btn btn-primary btn-lg" type="submit" style="background-color: #bd0223; border-color: #bd0223;"><b>Log in</b></button>
                                            </div>
                                            <h5 style="color: red;">${requestScope.error}</h5>
                                        </div>
                                        <div class="col-12">
                                            <p class="m-0 text-secondary text-center">Don't have an account? <a href="signup" class="link-primary text-decoration-none" style="color: #bd0223 !important;">Sign up</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div id="footer">
            <%@include file="footer.jsp" %>
        </div>
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <script>
            const togglePassword = document.querySelector('#togglePassword');
            const password = document.querySelector('#password');

            togglePassword.addEventListener('click', function (e) {
                const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
                password.setAttribute('type', type);
                this.querySelector('i').classList.toggle('fa-eye-slash');
            });
        </script>
    </body>
</html>
