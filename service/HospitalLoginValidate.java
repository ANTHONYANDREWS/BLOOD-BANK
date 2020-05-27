package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.HospitalLogin;
import utility.ConnectionManager;

public class HospitalLoginValidate {
public boolean validate(HospitalLogin hospitallogin) throws SQLException, Exception {
		
		String email = hospitallogin.getEmail();
		String password = hospitallogin.getPassword();
		
		ConnectionManager con = new ConnectionManager();
		Statement st = con.getConnection().createStatement();
		
		ResultSet rs = st.executeQuery("Select * from HOSPITAL");
		boolean result = false;
		while(rs.next())
		{
			if(email.equals(rs.getString("EMAIL")) && (password.equals(rs.getString("PASSWORD"))))
			{
				result = true;
			}
			
		}
		return result;
	}

}
