-
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container-fluid pt-5 ">
    <div id="page-wrapper">
    <div class="container">
        <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
            <h6 class="text-primary text-uppercase">Thống kê</h6>
            <h1 class="display-5 text-uppercase mb-0">Thống kê bán hàng của hệ thống</h1>
        </div>
        <div class="col-md-12 statistical d-flex justify-content-around mb-5">
            <div class="d-flex register m-1">
                <div style="background-color: orange;" class="icon"><i class="fa-solid fa-person-circle-check"></i>
                </div>
                <div class="detail">
                    <div>${numberOfOrderToConfirm }</div>
                    <div>Tổng đơn hàng đợi xét duyệt</div>
                </div>
            </div>
            <div class="d-flex user m-1">
                <div style="background-color: rgba(0, 0, 255, 0.726);" class="icon"><i
                        class=" fa-solid fa-user"></i></div>
                <div class="detail">
                    <div>${numberOfUsers}</div>
                    <div>Số tài khoản khách hàng đã đăng kí</div>
                </div>
            </div>
            <div class="d-flex pet-sold m-1">
                <div style="background-color: #7AB730;" class="icon"><i class="fa-solid fa-paw"></i></div>
                <div class="detail">
                    <div>${numberOfPetsLeft}</div>
                    <div>Tổng số thú cưng chưa bán</div>
                </div>
            </div>

            <div class="d-flex money m-1">
                <div style="background-color: rgb(204, 0, 204);" class="icon"><i class=" fa-solid fa-coins"></i>
                </div>
                <div class="detail">
                    <div>${orderRevenue}</div>
                    <div>Doanh thu bán hàng trong năm nay</div>
                </div>
            </div>
        </div>
                    <div class="container mt-3">
    <label for="monthSelect">Chọn tháng:</label>
    <select id="monthSelect" onchange="updateMonthlyRevenue()">
        <option value="0">Vui lòng chọn tháng</option>
        <option value="1">Tháng 1</option>
        <option value="2">Tháng 2</option>
        <option value="3">Tháng 3</option>
        <option value="4">Tháng 4</option>
        <option value="5">Tháng 5</option>
        <option value="6">Tháng 6</option>
        <option value="7">Tháng 7</option>
        <option value="8">Tháng 8</option>
        <option value="9">Tháng 9</option>
        <option value="10">Tháng 10</option>
        <option value="11">Tháng 11</option>
        <option value="12">Tháng 12</option>
    </select>
</div>
    </div>
</div>
                    
<div class="container mt-3">
    <label for="monthSelect">Chọn tháng:</label>
    <select id="monthSelect" onchange="updateMonthlyRevenue()">
        <option value="0">Vui lòng chọn tháng</option>
        <option value="1">Tháng 1</option>
        <option value="2">Tháng 2</option>
        <option value="3">Tháng 3</option>
        <option value="4">Tháng 4</option>
        <option value="5">Tháng 5</option>
        <option value="6">Tháng 6</option>
        <option value="7">Tháng 7</option>
        <option value="8">Tháng 8</option>
        <option value="9">Tháng 9</option>
        <option value="10">Tháng 10</option>
        <option value="11">Tháng 11</option>
        <option value="12">Tháng 12</option>
    </select>
</div>

<div id="monthlyRevenue" class="col-md-12 statistical d-flex justify-content-around mb-5">
    <!-- Nội dung được cập nhật qua JavaScript -->
</div>
</div>




<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


<style>
    .statistical .icon {

        color: white;
        align-self: center;
        padding: 30px 20px;
        font-size: xx-large;
        border-radius: 10% 0 0 10%;
    }

    .statistical .detail {
        background-color: rgba(128, 128, 128, 0.116);
        border-radius: 0 10% 10% 0;
        width: 150px;
        font-size: smaller;
        text-align: center;
    }

    .statistical .detail :first-child {
        font-size: 40px;
        width: 100%;

        padding-top: 5%;
    }

    .statistical .money .detail {
        width: 250px !important;
    }
</style>


<script>
    function updateMonthlyRevenue() {
        var month = document.getElementById("monthSelect").value;
        var revenueContent = document.getElementById("monthlyRevenue");

        if (month === "0") {
            revenueContent.innerHTML = "<p>Vui lòng chọn tháng để hiển thị doanh thu.</p>";
            return;
        }

        var revenueForMonth;

        // Sử dụng if-else để xác định doanh thu cho từng tháng
        if (month === "1") {
            revenueForMonth = '${order1}';
        } else if (month === "2") {
            revenueForMonth = '${order2}';
        } else if (month === "3") {
            revenueForMonth = '${order3}';
        } else if (month === "4") {
            revenueForMonth = '${order4}';
        } else if (month === "5") {
            revenueForMonth = '${order5}';
        } else if (month === "6") {
            revenueForMonth = '${order6}';
        } else if (month === "7") {
            revenueForMonth = '${order7}';
        } else if (month === "8") {
            revenueForMonth = '${order8}';
        } else if (month === "9") {
            revenueForMonth = '${order9}';
        } else if (month === "10") {
            revenueForMonth = '${order10}';
        } else if (month === "11") {
            revenueForMonth = '${order11}';
        } else if (month === "12") {
            revenueForMonth = '${order12}';
        }

        var revenueHtml = '<div class="d-flex money m-1">' +
            '<div style="background-color: ' + getColorByMonth(month) + ';" class="icon"><i class="fa-solid fa-coins"></i></div>' +
            '<div class="detail">' +
            '<div>' + revenueForMonth + '</div>' +
            '<div>Doanh thu bán hàng trong tháng: ' + month + ' </div>' +
            '</div></div>';

        revenueContent.innerHTML = revenueHtml;
    }

    function getColorByMonth(month) {
        var colors = {
            "1": "rgb(204, 0, 204)",
            "2": "rgb(255, 165, 0)",
            "3": "rgb(0, 128, 0)",
            "4": "rgb(0, 0, 255)",
            "5": "rgb(128, 0, 128)",
            "6": "rgb(255, 0, 0)",
            "7": "rgb(64, 224, 208)",
            "8": "rgb(255, 20, 147)",
            "9": "rgb(0, 206, 209)",
            "10": "rgb(210, 105, 30)",
            "11": "rgb(220, 20, 60)",
            "12": "rgb(0, 0, 139)"
        };
        return colors[month];
    }

    // Khởi tạo ban đầu
    document.getElementById("monthSelect").value = "0";
    updateMonthlyRevenue();

</script>
