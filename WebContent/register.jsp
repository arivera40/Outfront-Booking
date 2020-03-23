<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Outfront Booking Registration</title>
</head>
<body>
<h1>Outfront Register Form</h1>
<form action="outfront_register" method="post">
	<table style="width: 50%">
		<tr>
			<td>First Name</td>
			<td><input type="text" name="first_name" /></td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td><input type="text" name="last_name" /></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><input type="text" name="address" /></td>
		</tr>
		<tr>
			<td>State</td>
			<td><input type="text" name="state" /></td>
		</tr>
		<tr>
			<td>City</td>
			<td><input type="text" name="city" /></td>
		</tr>
		<tr>
			<td>Zip Code</td>
			<td><input type="text" name="zip_code" /></td>
		</tr>
		<tr>
			<td>Phone Number</td>
			<td><input type="text" name="phone_number" /></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" name="email" /></td>
		</tr>
		<tr>
			<td>Card Number</td>
			<td><input type="text" name="card_number" /></td>
		</tr>
	</table>
	<input type="submit" value="Submit" />
</form>
</body>
</html>
