package com.wipro.weather.servlets;
import java.io.IOException;
import java.sql.Date;

import com.wipro.weather.bean.WeatherBean;
import com.wipro.weather.service.Administrator;
import com.wipro.weather.util.InvalidInputException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MainServlet")
public class MainServlet  extends HttpServlet
{ 
	  public String addForecast(HttpServletRequest request)
	  {
		  try 
		  {
		  //administrator
		  WeatherBean bean = new WeatherBean();
		  String reportId= request.getParameter("reportId");  //names must match with jsp names
		  bean.setReportId(reportId);
		  
		  String location = request.getParameter("location");	//getParameter always return String		
		  bean.setLocation(location);

		  String dateStr = request.getParameter("date");
		  Date date = Date.valueOf(dateStr); //covert string  to date
		  bean.setDate(date);
		  
		  int humidity = Integer.parseInt(request.getParameter("humidity")); //convert string to int
		  bean.setHumidity(humidity);
		  
		  int temperature = Integer.parseInt(request.getParameter("temperature"));
		  bean.setTemperature(temperature);
		  
		  String wind = request.getParameter("wind");			
		  bean.setWind(wind);
		  
		  String forecast = request.getParameter("forecast");
		  bean.setForecast(forecast);
		  //return statement must be within the method
		  
		  Administrator admin = new Administrator();
		  String result =admin.addForecast(bean);

		  return result;
	  }
		catch(InvalidInputException e){
		      return "INVALID INPUT";
		  }
	  }	  

	  public WeatherBean viewForecast(HttpServletRequest request)
	  {
	      try
	      {
	          String location = request.getParameter("location");

	          String dateStr = request.getParameter("date");
	          System.out.println("Raw Date From JSP = " + dateStr);

	          Date date = Date.valueOf(dateStr);
	          System.out.println("Converted Date = " + date);
	          Administrator admin = new Administrator();
	          WeatherBean bean = admin.viewForecast(location,date);
	          return bean;
	      }
	      catch(InvalidInputException e)
	      {
	          return null;
	      }
	  }
	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String operation =request.getParameter("operation");

		if(operation.equals("newForecast")) //1method 
		{
		    String result = addForecast(request);
		    if(result.equals("FAIL") ||  result.equals("INVALID INPUT") || result.equals("INVALID LOCATION") ||result.equals("INVALID DATE") ||result.equals("INVALID TEMPERATURE") ||
		       result.equals("INVALID HUMIDITY")){
		         response.sendRedirect("error.html");
		    }
		    else{
		         response.sendRedirect("success.html");
		    }
		}

		else if(operation.equals("viewForecast")){  //2method
		     WeatherBean bean = viewForecast(request);
		     if(bean == null){
		          request.setAttribute("message", "No matching records exists! Please try again!");
		     }

		     else{
		          request.setAttribute("forecast",bean);
		     }

		     RequestDispatcher rd =request.getRequestDispatcher("displayForecast.jsp");
		     rd.forward(request,response);

		}
	}

}

