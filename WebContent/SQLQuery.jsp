<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lab 7 - SQL</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	Enter your SQL below:
	<form action="Todo" method="post">
		<div class="row">
			<div class="col-xs-9 col-sm-10">
				<div class="form-group">
					<label class="sr-only" for="description">SQL Query</label> <input
						type="text" class="form-control" id="description"
						name="description" placeholder="SELECT * FROM items">
				</div>
				<c:if test="${not empty error}">
					<p class="well-sm bg-danger">${error}</p>
				</c:if>
			</div>
		</div>
	</form>
	<div>
		<button type="submit" class="btn btn-primary">EXECUTE QUERY</button>
		<button type="submit" class="btn btn-warning">SUBMIT UPDATE</button>
	</div>
	
	
</body>
</html>