package com.wipro.weather.service;
import java.sql.Date;

import com.wipro.weather.bean.WeatherBean;
import com.wipro.weather.dao.WeatherDAO;
import com.wipro.weather.util.InvalidInputException;

public class Administrator 
{
	//addforecast
	 public String addForecast(WeatherBean weatherBean)throws InvalidInputException
	    {
	        Date today = new Date(System.currentTimeMillis());
	        if(weatherBean.getReportId() == null || weatherBean.getLocation() == null ||
	           weatherBean.getDate() == null || weatherBean.getWind() == null ||
	           weatherBean.getForecast() == null)
	        {
	            throw new InvalidInputException();
	        }
	        if(weatherBean.getLocation().length() < 2)
	        {
	            return "INVALID LOCATION";
	        }
	        if(weatherBean.getDate().before(today))
	        {
	            return "INVALID DATE";
	        }
	        if(weatherBean.getTemperature() < -100 ||weatherBean.getTemperature() > 100)
	        {
	            return "INVALID TEMPERATURE";
	        }
	        if(weatherBean.getHumidity() < 0 ||weatherBean.getHumidity() > 100)
	        {
	            return "INVALID HUMIDITY";
	        }
	        WeatherDAO dao = new WeatherDAO();
	        return dao.createForecast(weatherBean);
	    }
	
	//view forecast
	public WeatherBean viewForecast(String location,Date date) throws InvalidInputException
	{
		WeatherDAO dao = new WeatherDAO();
		return dao.fetchForecast(location,date);
		
	}
}
