package com.wipro.weather.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.wipro.weather.bean.WeatherBean;
import com.wipro.weather.util.DBUtil;

public class WeatherDAO 
{
	//insert into WEATHER_TB, if insertion fails, return fails
	public String createForecast(WeatherBean weatherBean)          
	{        
		try (Connection con = DBUtil.getDBConnection()) 
   	    {
   	        String sql = "INSERT INTO WEATHER_TB (REPORTID,LOCATION,R_DATE,TEMPERATURE,HUMIDITY,WIND,FORECAST) VALUES(?,?,?,?,?,?,?)";
   	        PreparedStatement ps = con.prepareStatement(sql);
   	        
   	        ps.setString(1, weatherBean.getReportId());
   	        ps.setString(2, weatherBean.getLocation());
   	        ps.setDate(3, weatherBean.getDate());
   	        ps.setInt(4, weatherBean.getTemperature());
   	        ps.setInt(5, weatherBean.getHumidity());
   	        ps.setString(6, weatherBean.getWind());
   	        ps.setString(7, weatherBean.getForecast());
   	        
   	        int rows = ps.executeUpdate(); //if inserted returns 1 else 0, so use int
   	        if(rows>0)
   	        	return "DATAS INSERTED SUCCESSFULLY!";
   	        else return "FAIL";
   	    } 
   	    catch (Exception e)
   	    {
   	    	e.printStackTrace();
   	    	return "FAIL";
       }	
	}
	
	//fetch weatherReport
	public WeatherBean 	fetchForecast(String location, Date date)
	{
		try (Connection con = DBUtil.getDBConnection()) 
   	    {
   	        String sql = "SELECT * FROM WEATHER_TB WHERE LOCATION = ? AND R_DATE = ?";
   	        PreparedStatement ps = con.prepareStatement(sql);
   	        ps.setString(1, location);
   	        ps.setDate(2, date);

   	  System.out.println( "Searching => " + location +" | " + date
   		);
   	        ResultSet rs = ps.executeQuery();
   	        System.out.println("Query executed");
   	        WeatherBean bb = new WeatherBean();
   	        
   	        if(rs.next())             //bring the cursor to 1st row
   	        { 
   	        System.out.println("Record Found");
   	        
   	   	     bb.setReportId(rs.getString("REPORTID"));
   	   	     bb.setLocation(rs.getString("LOCATION"));
   	   	     bb.setDate(rs.getDate("R_DATE"));
   	   	     bb.setTemperature(rs.getInt("TEMPERATURE"));
   	   	     bb.setHumidity(rs.getInt("HUMIDITY"));
   	   	     bb.setWind(rs.getString("WIND"));
   	   	     bb.setForecast(rs.getString("FORECAST"));
   	   	     }
   	     else{
   	      System.out.println("NO RECORD FOUND");
   	      return null;
   	     }
   	   	  return bb;
   	   
   	    }
		catch (Exception e)
   	    {
   	    	e.printStackTrace();
   	    	return null;
       }
		
	}
	
	//generate report
	public String generateReportID(String location, Date date)
	{
	    try(Connection con = DBUtil.getDBConnection())
	    {
	        DateFormat format = new SimpleDateFormat("yyyyMMdd");
	        String temp =format.format(date);
	        String loc =location.substring(0,2).toUpperCase();
	        String sql ="SELECT WEATHER_SEQ.NEXTVAL FROM DUAL";
	        PreparedStatement ps =con.prepareStatement(sql);
	        ResultSet rs =ps.executeQuery();

	        if(rs.next())
	        {
	            int seqNo =rs.getInt(1);
	            return temp + loc + seqNo;
	        }
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//report exists
	public boolean reportExists(String location,Date date) 
	{
		try (Connection con = DBUtil.getDBConnection())
		{
			String sql = "SELECT * FROM WEATHER_TB WHERE LOCATION = ? AND R_DATE = ?";
   	        PreparedStatement ps = con.prepareStatement(sql);
   	        ps.setString(1, location);
   	        ps.setDate(2, date);
   	        
   	        ResultSet rs = ps.executeQuery();       //query always returnd resultset
   	        if(rs.next())
	        	return true;
	        else return false;	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
