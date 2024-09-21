<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #ff7e5f, #feb47b);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            color: white;
        }
        .container {
            text-align: center;
            background: rgba(0, 0, 0, 0.5);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #ff7e5f;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #ff7e5f;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #feb47b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Hello ${username}, welcome to User Home!</h2>
        <a href="/ltweb/logout">Logout</a>
    </div>
</body>
</html>
