<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%-- set data source --%>
<sql:setDataSource
	var="display"
	driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://cs3.calstatela.edu/cs3220stu11"
	user="cs3220stu11"
	password="RtmdCCoV" />

       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL_SQL</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>

<c:catch var="queryError">
	<sql:query var="entries" dataSource="${display}">
		<c:if test="${not empty param.Query}">
			 ${param.message}
		</c:if>
	</sql:query>
</c:catch>

<form action="JSP_SQL.jsp" method="post">

	<div class="form-group">
			  		<label for="sqlTextArea">Enter your SQL below:</label>
			  		Example Query with only database = SELECT * FROM Professors;
					<textarea class="form-control" id="message" name="message">${param.message }</textarea>
				</div>
	<button type="submit" class = "btn btn-primary" name ="Query" value="Query">EXECUTE QUERY</button>
	<button type="submit" class = "btn btn-warning" name ="Update" value="Update">UPDATE QUERY</button>
</form>

<table class = "table table-bordered table-striped table-hover">
	<c:forEach items="${entries.columnNames}" var="title">
		<th>${title}</th>
	</c:forEach>
	<c:forEach items="${entries.rowsByIndex}" var="row">
		<tr>
			<c:forEach items="${row}" var="col">
		  		<td>${col}</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

<c:catch var="updateError">
<c:if test="${not empty param.Update}">
	${updateError}
	<sql:update dataSource="${display}" var="update">
	 ${param.message}
	</sql:update>
	<c:out value="${update}"/> rows affected
</c:if>
</c:catch>

<c:if test="${not empty param.Query}">
	${queryError}
</c:if>

<c:if test="${not empty param.Update}">
	${updateError}
</c:if>

</body>
</html>