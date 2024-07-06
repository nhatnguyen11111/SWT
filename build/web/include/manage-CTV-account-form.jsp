<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý tài khoản CTV</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
     <style>
        body {
            background-color: #f8f9fa;
        }
        .container-fluid {
            padding-top: 50px;
        }
        h2 {
            margin-bottom: 20px;
        }
        .table {
            margin-top: 20px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .table thead {
            background-color: #007bff;
            color: white;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
        #pagination {
            margin-top: 20px;
        }
        .paging-button {
            display: inline-block;
            padding: 5px 10px;
            border: 1px solid #007bff;
            background-color: white;
            color: #007bff;
            cursor: pointer;
            text-decoration: none;
            border-radius: 5px;
            margin: 0 2px;
        }
        .paging-button.active {
            background-color: #007bff;
            color: white;
        }
        .nav-pills .nav-link {
            border-radius: 0.25rem;
            margin: 0 5px;
        }
        .nav-pills .nav-link.active {
            background-color: #007bff;
            color: white;
        }
        .nav-pills .nav-link:hover {
            background-color: #0056b3;
            color: white;
        }
        .btn-danger, .btn-success {
            border-radius: 5px;
        }
        .form-control {
            border-radius: 5px;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div id="page-wrapper">
            <nav class="nav nav-pills nav-justified" style="background: #f8f9fa;">
            <a class="nav-item nav-link active" href="#">Danh sách tài khoản Cộng tác viên</a>
            <a class="nav-item nav-link" style="color : black; background: white" href="listRegisterCTV">Danh sách đăng kí cộng tác viên</a>
            </nav>
            <div class="container mt-3">
                <h2>Quản lí danh sách tài khoản Cộng tác viên</h2>
                <p>Admin có thể xem được danh sách tài khoản Cộng tác viên ở đây</p>
                <input class="form-control" id="myInput" type="text" placeholder="Tìm kiếm">
                <br>
                <table class="table table-bordered" id="data-table">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>ID Cộng tác viên</th>
                            <th>Họ</th>
                            <th>Tên</th>
                            <th>Số điện thoại </th>
                            <th>Email</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="myTable">
                        <c:forEach var="acc" items="${listCTVAcc}">
                            <tr>
                                <td>${acc.username}</td>
                                <td>${acc.userID}</td>
                                <td>${acc.firstname}</td>
                                <td>${acc.lastname}</td>
                                <td>${acc.phone}</td>
                                <td>${acc.email}</td>
                                <c:if test="${acc.status==1}">
                                    <td>
                                        <a href="lockemployee?CTVID=${acc.userID}" class="btn btn-danger">
                                            <i class="fa fa-lock" aria-hidden="true"></i> Khóa
                                        </a>
                                    </td>
                                </c:if>
                                <c:if test="${acc.status==0}">
                                    <td>
                                        <a href="unlockemployee?CTVID=${acc.userID}" class="btn btn-success">
                                            <i class="fa fa-unlock-alt" aria-hidden="true"></i> Mở
                                        </a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div id="pagination" class="text-center"></div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
        });

        var currentPage = 1;
        var recordsPerPage = 10;
        var table = document.getElementById("data-table");
        var rows = table.getElementsByTagName("tr");
        var totalPage = Math.ceil(rows.length / recordsPerPage);

        function showTable(page) {
            var start = (page - 1) * recordsPerPage;
            var end = start + recordsPerPage;

            for (var i = 1; i < rows.length; i++) { // Start from 1 to skip the header row
                if (i >= start + 1 && i < end + 1) {
                    rows[i].style.display = "table-row";
                } else {
                    rows[i].style.display = "none";
                }
            }
        }

        function createPagination() {
            var pagination = document.getElementById("pagination");
            pagination.innerHTML = "";

            for (var i = 1; i <= totalPage; i++) {
                var button = document.createElement("a");
                button.href = "#";
                button.innerHTML = i;

                if (i === currentPage) {
                    button.classList.add("paging-button", "active");
                } else {
                    button.classList.add("paging-button");
                }

                button.addEventListener("click", function (e) {
                    e.preventDefault();
                    currentPage = parseInt(this.innerHTML);
                    showTable(currentPage);

                    var currentButton = document.querySelector(".paging-button.active");
                    currentButton.classList.remove("active");

                    this.classList.add("active");
                });

                pagination.appendChild(button);
            }
        }

        showTable(currentPage);
        createPagination();
    </script>
</body>
</html>
