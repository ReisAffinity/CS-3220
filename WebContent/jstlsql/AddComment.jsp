<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Quotation</title>
</head>
<body>

<form action="AddQuotation" method="post">

	<c:if test="${not empty nameError }">
		<p style="color: red;">${nameError}</p>
	</c:if>
	
	Name: <input type="text" name="name" value="${param.name}">	<br>
	
	<c:if test="${not empty quotationError }">
		<p style="color: red;">${quotationError}</p>
	</c:if>
	
	Quotation: <br>
	<textarea name="quotation">${param.quotation}</textarea> <br>
	<input type="submit" value="Add Quotation">	
</form>

</body>
</html>