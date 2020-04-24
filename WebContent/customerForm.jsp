<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Outfront Booking</title>
	</head>
	<body>
		<h1>Outfront Customer Information Form</h1>
		<form action="CustomerServlet" method="post">
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
					<td><input type="text" name="zipcode" /></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="phone" /></td>
				</tr>
				<tr>
					<td>Card Number</td>
					<td><input type="text" name="card_number" /></td>
				</tr>
			</table>
			<input type="hidden" id="acc_number" name="acc_number" value="${acc_number}" >
			<input type="hidden" id="email" name="email" value="${email}">

			<input type="submit" value="Register" />
		</form>
	</body>
</html>
