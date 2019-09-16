<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
	<table>
		<tr>
			<td>id</td>
			<td>pass</td>
			<td>name</td>
		</tr>
		<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.id }</td>
				<td>${dto.password }</td>
				<td>${dto.name }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
