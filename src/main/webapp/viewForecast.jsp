<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Forecast</title>
</head>
<body>
	
<h2>View Forecast</h2>

	<form action="MainServlet" method="post">
    <input type="hidden" name="operation" value="viewForecast">
    
    Location:<input type="text" name="location"><br><br>
    Date:<input type="date" name="date"><br><br>

    <input type="submit" value="View Forecast">

	</form>

</body>
</html>