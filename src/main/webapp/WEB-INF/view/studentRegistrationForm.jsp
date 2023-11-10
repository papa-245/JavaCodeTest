<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
function clearFormFunction(){
  var element = document.getElementById("stuForm");
  element.reset();
}
$(document).ready(function() {
    $('#DOB').datepicker();
    $('#DOB').datepicker(Date.now());
});

</script>
<style>
form {
	text-align: left;
	align-items: flex-start;
}
</style>

<title>Student Registration Form</title>

</head>
<body>

	<h1 align="center">Student Registration Form</h1>
	<br>
	<form:form action="/register" method="post" modelAttribute="student" id ="stuRegForm">

		<table align="center" >
			<tr>
				<td><label>Student Name </label></td>
				<td><form:input path="name" /></td>
			</tr>
			
			<tr>
				<td><label>Age </label></td>
				<td><form:input type="number" path="age"/></td>
			</tr>
			
			<tr>
				<td><label>Course </label></td>
				<td><form:select path="course">
						<form:option value="Java" label="Java"></form:option>
						<form:option value="Python" label="Python"></form:option>
						<form:option value="PHP" label="PHP"></form:option>
						<form:option value="SQL" label="SQl"></form:option>
					</form:select></td>
			</tr>
			
			<tr>
				<td><label>Gender </label></td>
				<td><form:radiobutton path="gender" value="Male" /> Male
				<form:radiobutton path="gender" value="Female" /> Female</td>
			</tr>

			<tr>
				<td><label>Date Of Birth </label></td>
				<td><form:input path="dateOfBirth" type="date" id="DOB" value="11/10/2023"/></td>
			</tr>

			<tr>
				<td><label>Address </label></td>
				<td><form:textarea path="address" /></td>
			</tr>
			
			<tr>
			    <td> <form:button type="submit"> Submit </form:button> </td>
			    <td> <form:button type="reset" onclick= "clearFormFunction()"> Cancel </form:button> </td>
			</tr>
		</table>

	</form:form>
</body>
</html>