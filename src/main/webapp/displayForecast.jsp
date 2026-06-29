<%@ page import="com.wipro.weather.bean.WeatherBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
WeatherBean bean = (WeatherBean)request.getAttribute("forecast");
String message = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forecast Details</title>
</head>
<body>

<h2>Forecast Details</h2>

<%
if(bean != null)
{
%>

Report ID :
<%= bean.getReportId() %>
<br><br>

Location :
<%= bean.getLocation() %>
<br><br>

Date :
<%= bean.getDate() %>
<br><br>

Temperature :
<%= bean.getTemperature() %>
<br><br>

Humidity :
<%= bean.getHumidity() %>
<br><br>

Wind :
<%= bean.getWind() %>
<br><br>

Forecast :
<%= bean.getForecast() %>

<%
}
else
{
%>

<%= message %>
<%
}
%>
</body>
</html>



