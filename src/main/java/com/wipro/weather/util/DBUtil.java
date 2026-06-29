package com.wipro.weather.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil
{
	public static Connection getDBConnection() 
    {
        Connection con = null;

        try 
        {
        	Class.forName("oracle.jdbc.OracleDriver");
        	
            con = DriverManager.getConnection(
    "jdbc:oracle:...",
    "YOUR_USERNAME",
    "YOUR_PASSWORD"
);
            System.out.println("DB Connected Successfully!");

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return con;
    }


}
