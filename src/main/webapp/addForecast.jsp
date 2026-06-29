<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Forecast</title>
</head>
<body>
<h2>Add Forecast</h2>
	<form action="MainServlet" method="post">
	
	<input type="hidden" name="operation" value="newForecast">

    Report ID:  <input type="text" name="reportId">   <br><br>
    Location:<input type="text" name="location">   <br><br>
    Date:<input type="date" name="date">   <br><br>
    Temperature:<input type="text" name="temperature">   <br><br>
    Humidity:<input type="text" name="humidity">   <br><br>
    Wind:<input type="text" name="wind">  <br><br>
    Forecast:<input type="text" name="forecast">  <br><br>

    <input type="submit" value="Add Forecast">

	</form>
</body>
</html>