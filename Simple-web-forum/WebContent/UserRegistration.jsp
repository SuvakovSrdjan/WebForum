<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
<link rel="stylesheet" type="text/css" href="WEB-SRC/StyleSettings.css">
</head>
<body>
<h1>USER REGISTRATION</h1>

	<form style="padding-left: 260px" name="regForm" onsubmit="return validateForm()"  method="post" action="RegisterServlet">
		<table>
			<tr>
				<td>First name:</td>
				<td><input type="text" name="FirstName"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input height="30px" type="text" name="Lastname"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="Password1"></td>
			</tr>
			<tr>
				<td>Repeat Password:</td>
				<td><input type="password" name="Password2"></td>
			</tr>
			<tr>
				<td>User name:</td>
				<td><input type="text" name="Username"></td>
			</tr>

			<tr>
				<td>Account Type:</td>
				<td><select name="AccType">
						<option value="0">ADMIN</option>
						<option value="1">USER</option>
						<option value="2">MODERATOR</option>
				</select></td>
			</tr>
			<tr>
				<td>Phone number:</td>
				<td><input type="text" name="PhoneNum"></td>
			</tr>
			<tr>
				<td colspan='2'><input type="submit" name="addUser" value="Add User">
				<input type="submit" name="cancel" value="Cancel">
			</tr>
		</table>
</form>
</body>
</html>