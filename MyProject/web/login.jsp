

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập giảng viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .login-container {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            margin-top: 100px;
            box-shadow: 0px 0px 10px 0px #000;
        }

        .login-container h2 {
            text-align: center;
        }

        .form-group {
            margin: 10px 0;
        }

        .form-group label {
            display: block;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .form-group input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng nhập giảng viên</h2>
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <input type="checkbox" name="remember" value="remember" /> Remember me. <br/>
            <div class="form-group">
                <input type="submit" value="Đăng nhập">
            </div>
        </form>
    </div>
</body>
</html>