package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import model.Admin;
import model.AdminRegister;
import utility.ConnectionManager;

public class AdminLoginValidate {
	
//	 final String Login = "SELECT * FROM ADMIN";
	AdminRegister adminregister = new AdminRegister();
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public boolean validatelogin(Admin admin) throws Exception{
		
		
		String email = admin.getEmail();
		String password = admin.getPassword();
	
		Statement st = ConnectionManager.getConnection().createStatement();
		
		ResultSet rs = st.executeQuery("Select * from ADMINLOGIN");
		boolean result = false;
		while (rs.next()) {
			
			if(email.equals(rs.getString("email")) && (password.equals(rs.getString("password"))))
			{
				result = true;
			}
			}
		
		return result;

	}
	Admin admin = new Admin();
	String passwordchange = "update ADMINLOGIN set password = ? where  Secretkey = ?";
	

	public void passwordchange() throws SQLException, Exception {
		System.out.println("Enter email address");
		String email_address = br.readLine();
		System.out.println("Enter the confidental input to change password");
		String secretkeyvalue = br.readLine();
		System.out.println("Enter password change:");
		String pass = br.readLine();
		
		adminregister.setEmail(email_address);
		adminregister.setPassword(pass);
		adminregister.setSecretkey(secretkeyvalue);

		
//		String emailcheck =admin.getEmail();
//		String passcheck = admin.getPassword();
//		String keycheck = admin.getKeycheck();
//        Statement st = ConnectionManager.getConnection().createStatement();
//		
//		ResultSet rs = st.executeQuery("Select * from ADMINLOGIN");
//		boolean result = false;
//		while (rs.next()) {
//			
//			if(emailcheck.equals(rs.getString("email")) && (keycheck.equals(rs.getString("Secretkey"))))
//			{
//				
//			
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(passwordchange);
//		ps.setString(1, adminregister.getEmail());
		ps.setString(1,adminregister.getPassword());
		ps.setString(2, adminregister.getSecretkey());
		boolean rowupdated = ps.executeUpdate() >0;
		
		if(rowupdated) {
			System.out.println("Password updated succesfully");
		}
		else {
			System.out.println("Issues updating password");
		}
			}
			
//		}
	}
		
		
//	}


