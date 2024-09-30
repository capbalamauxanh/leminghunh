<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style type="text/css">
/* Extra style for the cancel button (red) */
.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}
/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	.cancelbtn {
		width: 100%;
	}
}
</style>
</head>
<body>
	<h1>Welcome!</h1>
	<form action="/studyproject/home" method="post">
		<button type="submit" class="cancelbtn">Logout</button>
	</form>
</body>
</html>