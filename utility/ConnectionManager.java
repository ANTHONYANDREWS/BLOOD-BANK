package utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {
	
//	Method to fetch Jdbc properties from Property file
	
	public static Properties loadPropertiesFile() throws Exception {
		Properties prop = new Properties();	
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close(); 
		return prop;
	}
	
//	Method to Connect with Database
	
	public final static Connection getConnection() throws Exception {
		Properties prop = null;
		
		prop= loadPropertiesFile();
		
		final String driver = prop.getProperty("driver");
		final String url = prop.getProperty("url");
		
		
		Class.forName(driver);                  //Register the Driver
		
		Connection con = null;                  //Object for Connection

		
		con = DriverManager.getConnection(url);        //Connecting To Database
		
		if (con!=null)
		{
			return con;
		}
		else
		{
			con.close();
			return null;
		}
		
	}

}
