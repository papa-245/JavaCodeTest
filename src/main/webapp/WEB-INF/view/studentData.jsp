<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

<script type="text/javascript" >

  $(document).ready(function () {
  $('.edit').click(function () {
    var currentTD = $(this).parents('tr').find('td');
         if ($(this).html() == 'Edit') {
             $.each(currentTD, function () {
                $(this).prop('contenteditable', true)
                });
         } else {
            $.each(currentTD, function () {
                $(this).prop('contenteditable', false)
                });
            }

         $(this).html($(this).html() == 'Edit' ? 'Save' : 'Edit')
         $(this).modelAttribute = student;
         //$(this).location.href = "edit";
         //$(this).action = "edit";
         //$(this).submit();
        });
    });


</script>

<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  text-align : center;
}
th {
  background-color: #96D4D4;
}
</style>
</head>
<body>
<br>
<br>
     		<table align="center" >
     			<tr>
     				<th><label>Name </label></th>
     				<th><label>Age </label></th>
     				<th><label>Course </label></th>
     				<th><label>Gender </label></th>
     				<th><label>Date Of Birth </label></th>
     				<th><label>Address </label></th>
     				<th><label>Action </label></th>
     			</tr>
                <c:forEach var="student" items="${studentList}">
     			<tr >
     				<td id="name" contenteditable="false">${student.name}</td>
     				<td id="age" contenteditable="false">${student.age}</td>
     				<td id="course" contenteditable="false">${student.course}</td>
     				<td id="gender" contenteditable="false">${student.gender}</td>
                    <td id="DOB" contenteditable="false">${student.dateOfBirth}</td>
                    <td id="address" contenteditable="false">${student.address}</td>
                    <td width="40%">

                        <spring:url value="/register/edit/${student}" var="editUrl" />
                        <spring:url value="/register/delete/${student.id}" var="deleteUrl" />
                        <spring:url value="/register/print" var="printUrl" />
                        <button class="edit">Edit</button>
                        <button onclick="location.href='${deleteUrl}'">Delete</button>
                        <button onclick="location.href='${printUrl}'">Print</button>
                    </td>
     			</tr>
     			</c:forEach>
     		</table>

</body>
</html>