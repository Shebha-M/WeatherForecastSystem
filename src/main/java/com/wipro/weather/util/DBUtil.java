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
            	"jdbc:oracle:thin:@(description=(address=(protocol=tcps)(port=2484)(host=db.freesql.com))(connect_data=(service_name=23ai_34ui2)))",
                "SHEBHAM_06_SCHEMA_4EGKF",
                "AEZ7F5H56ZY#NE8PrYQLEN8LJ6PEMX"
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
