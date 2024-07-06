<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BabyCare Admin Panel</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@600;700&family=Montserrat:wght@200;400;600&display=swap" rel="stylesheet"> 

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="materialize/css/materialize.min.css" media="screen,projection" />
    <!-- Bootstrap Styles-->
    <link href="css/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="css/custom-styles.css" rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="js/Lightweight-Chart/cssCharts.css"> 

    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            background-color: #f8f9fa;
        }

        .navbar-brand {
            font-family: 'Fredoka', sans-serif;
        }
        .nav li {
            border-bottom: white solid 3px;
        }

        .navbar {
            background-color: #007bff;
            padding: 10px 15px;
        }
        .navbar-side{
            height: 100%;
        }

        .navbar-brand i {
            vertical-align: middle;
        }

        .navbar-brand strong {
            vertical-align: middle;
            font-size: 24px;
            color: #fff;
        }

        .navbar-default .navbar-toggle {
            border-color: transparent;
        }

        .navbar-default .navbar-toggle .icon-bar {
            background-color: #fff;
        }

        .navbar-default .navbar-toggle:focus, 
        .navbar-default .navbar-toggle:hover {
            background-color: #0056b3;
        }

        .sidebar-collapse {
            background-color: #1f2837;
            padding: 20px 0;
        }

        .sidebar-collapse ul {
            list-style: none;
            padding-left: 0;
        }

        .sidebar-collapse ul li a {
            color: #000;
            padding: 10px 15px;
            display: block;
            text-decoration: none;
        }

        .sidebar-collapse ul li a:hover,
        .sidebar-collapse ul li a:focus {
            background-color: #e9ecef;
            color: #000;
        }

        .sidebar-collapse ul .nav-second-level {
            padding-left: 20px;
        }

        .img-avatar {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .nav-link {
            display: inline-block;
            color: #007bff;
            padding: 5px 10px;
            border-radius: 5px;
        }

        .nav-link.bg-primary {
            background-color: #007bff;
            color: white;
        }

        .dropdown-item {
            color: #000;
            display: block;
            width: 100%;
            padding: 5px 10px;
            clear: both;
            font-weight: 400;
            text-align: inherit;
            white-space: nowrap;
            background: 0 0;
            border: 0;
        }

        .dropdown-item:hover,
        .dropdown-item:focus {
            background-color: #e9ecef;
            color: #000;
        }

        .dropdown-item.text-danger {
            color: #dc3545;
        }

    </style>
</head>

<body>
    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" style="display: flex; justify-content: flex-start" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand waves-effect waves-dark" style="background: #007bff" href="admin"><i class="large material-icons">track_changes</i> <strong>BabyCare ADMIN</strong></a>

                <div id="sideNav" href=""><i class="material-icons dp48">toc</i></div>
            </div>
        </nav>

        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <c:if test="${sessionScope.user != null}">
                    <div class="d-flex align-items-center">
                        <img class="img-avatar text-white ms-lg-5" src="img/${sessionScope.user.avatar}" alt="">
                            <a href="#" class="nav-link  text-white px-5 ms-lg-3" style="background: #007bff">
                            ${sessionScope.user.firstname} ${sessionScope.user.lastname}
                        </a>
                    </div>
                </c:if>
                <ul class="nav" id="main-menu">
                    <li style="">
                        <a href="admin.jsp" class="dropdown-item">Thống kê bán hàng</a>
                    </li>
                    <li>
                        <a href="#" class="dropdown-item">Quản lí tài khoản</a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="manage-emp-account" class="dropdown-item">Quản lý tài khoản nhân viên</a>
                            </li>
                            <li>
                                <a href="manage-ctv-account" class="dropdown-item">Quản lý tài khoản cộng tác viên</a>
                            </li>
                            <li>
                                <a href="manage-cus-account" class="dropdown-item">Quản lý tài khoản khách hàng</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <a href="logout" class="dropdown-item text-danger">Đăng xuất</a>
            </div>
        </nav>
    </div>
</body>

</html>
