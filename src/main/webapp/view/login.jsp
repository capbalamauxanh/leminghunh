<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clean Login Form</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: url('C:\Users\User\Downloads\Hình nền Nền ảnh đen Trắng Về Một Cô Gái Anime Với Chiếc Mũ Trùm đầu Nền, ảnh đại Diện Cho Bệnh Trầm Cảm, Phiền Muộn, Trầm Cảm Background Vector để tải xuống miễn phí - Pngtree.html');
            background-size: cover;
            margin: auto;
            padding: 20px;
            background-position: center;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background: rgba(0, 0, 0, 0.75);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
            width: 400px;
            color: #fff;
            text-align: center;
        }

        .login-container h2 {
            margin-bottom: 30px;
            font-size: 24px;
            letter-spacing: 1px;
        }

        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            background: rgba(255, 255, 255, 0.2);
            color: #fff;
            font-size: 16px;
        }

        .login-container input::placeholder {
            color: #ddd;
        }

        .login-container label {
            display: block;
            text-align: left;
            margin-top: 10px;
        }

        .login-container a {
            color: #4CAF50;
            text-decoration: none;
        }

        .login-container a:hover {
            text-decoration: underline;
        }

        .login-container button {
            width: 100%;
            padding: 15px;
            margin-top: 20px;
            background: #4CAF50;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 18px;
            cursor: pointer;
        }

        .login-container button:hover {
            background: #45a049;
        }

        .social-login {
            margin-top: 20px;
        }

        .social-login a {
            margin: 0 10px;
            color: white;
            font-size: 18px;
            text-decoration: none;
        }

        .social-login a:hover {
            opacity: 0.8;
        }

        .forgot-register {
            margin-top: 15px;
        }

        .forgot-register a {
            color: #fff;
        }

        .forgot-register a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng nhập</h2>
        <form action="#" method="post">
            <input type="text" name="username" placeholder="Tên đăng nhập" required>
            <input type="password" name="password" placeholder="Mật khẩu" required>
            <label><input type="checkbox" name="remember"> Ghi nhớ đăng nhập</label>
            <button type="submit">Đăng nhập</button>

            <div class="forgot-register">
                <a href="#" id="forgot-password-link">Quên mật khẩu? Click here</a><br>
                <a href="#" id="register-link">Đăng ký</a>
            </div>

            <div class="social-login">
                <p>Hoặc đăng nhập với:</p>
                <a href="#" title="Login with Facebook">FB</a>
                <a href="#" title="Login with Twitter">TW</a>
                <a href="#" title="Login with Pinterest">P</a>
                <a href="#" title="Login with LinkedIn">IN</a>
            </div>
        </form>
    </div>

    <script>
        // Chuyển hướng khi nhấn vào "Quên mật khẩu"
        document.getElementById("forgot-password-link").onclick = function() {
            window.location.href = "/ltweb/forgotpassword";
        };

        // Chuyển hướng khi nhấn vào "Đăng ký"
        document.getElementById("register-link").onclick = function() {
            window.location.href = "/ltweb/register";
        };
    </script>
</body>
</html>
