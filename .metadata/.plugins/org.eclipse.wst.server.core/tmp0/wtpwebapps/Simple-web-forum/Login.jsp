<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="WEB-SRC/StyleSettings.css">
</head>
<body>

<h1>Login</h1>

	<form style="padding-left: 260px" name="LoginForm" onsubmit="return validateForm()"  method="post" action="LoginServlet">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="Username"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="Password1"></td>
			</tr>
			
			<tr>
				<td colspan='2'><input type="submit" name="login" value="Login">
				<input type="submit" name="cancel" value="Cancel">
			</tr>
		</table>
</form>

</body>
</html>