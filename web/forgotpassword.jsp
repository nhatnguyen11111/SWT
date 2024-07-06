<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quên mật khẩu</title>
    <style>
        $text-color: #30373B;
        $success-color: #7AB55C;
        $error-color: #C23628;
        $border-color: #A7A7A7;
        $body-font-size: 13px;
        $title-font-size: 32px;
        $subtitle-font-size: 20px;
        $form-font-size: 16px;
        *,
        *:before,
        *:after {
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }

        hr {
            background: #4bc970;
            height: 1px;
            border: 0;
            border-top: 1px solid #ccc;
            padding: 0;
            text-align: right;
            width: 5%;
            float: center;
        }
        span{
            color: red;
        }
        label {
            padding-top: 15px;
            font-weight: bold;
        }

        body {
            font-size: 13px;
            font-family: 'Nunito', sans-serif;
            color: #384047;
            background-color: #f87171;;
        }

        form {
            font-size: 16px;
            max-width: 300px;
            margin: 10px auto;
            padding: 10px 20px;
            background:  #f87171;;
            border-radius: 0px;
        }

        h1 {
            padding-top: 2em;
            font-size: 32px;
            margin: 0 0 30px 0;
            text-align: center;
        }

        h3 {
            padding-top: 1em;
            font-size: 20px;
            margin: 0 0 30px 0;
            text-align: center;
        }

        input[type="text"],
        input[type="password"],
        input[type="date"],
        input[type="datetime"],
        input[type="email"],
        input[type="number"],
        input[type="search"],
        input[type="tel"],
        input[type="time"],
        input[type="url"],
        textarea,
        select {

            border: none;
            font-size: 16px;
            height: auto;
            margin: 0;
            outline: 0;
            padding: 15px;
            width: 66%;

            color: #8a97a0;
            box-shadow: 0 1px 0 rgba(0, 0, 0, 0.03) inset;
            margin-bottom: 30px;
        }

        button {
            padding: 12px 39px 13px 39px;
            color: #FFF;
            background-color: #380514;
            font-size: 18px;
            text-align: center;
            font-style: normal;
            border: 1px solid #3ac162;

            margin-bottom: 10px;
            overflow: hidden;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        @media screen and (min-width: 480px) {
            form {
                max-width: 480px;
            }
        }
        </style>
    </head>
    <body>
        
        <div class="container-fluid"> 
            <div class="row"> 
                <div class="logo col-md-4">
                    <img class="img-fluid" src="images/logo.png" alt="">
                    <h1 class="navbar-brand text-primary display-6 " style="font-size: 46px; color: #ff4880">
                        Baby<span class="text-secondary" style="color: #4d65f9">Care</span>
                    </h1>
                </div>
            </div>
        </div>
             <h1>Quên mật khẩu</h1>

        <div id="forgotPasswordModal" class="modal fade show" role="dialog" style="display: block;">
            <div class="modal-dialog">
                <div class="modal-content">
                    
                    <div class="modal-body">
                        <form action="forgotPassword" method="post">
                            <div class="form-group" style="margin-left: 100px">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email của bạn" required>
                            </div>
                            <h3 class="text-danger">${thongbao}</h3>
                            <button type="submit" class="btn btn-primary center" style="margin-left: 150px">Gửi</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $('#forgotPasswordModal').modal('show');
            });
        </script>
   
    </body>
</html>
