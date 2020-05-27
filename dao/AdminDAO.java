package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.AdminRegister;
import utility.ConnectionManager;

public class AdminDAO {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	AdminRegister adminregister= new AdminRegister();
	
	String registeradmin = "Insert into ADMINLOGIN (email,password,Secretkey) VALUES (?,?,?)";
	public void registeradmin() throws SQLException, Exception {
		
		System.out.println("Enter email address you want to add as Admin");
		String email = br.readLine();
		System.out.println("Enter password for the new admin");
		String password = br.readLine();
		System.out.println("Enter the secret key to change password for Admin or perform other operations in future");
		String secretkey = br.readLine();
		adminregister.setEmail(email);
		adminregister.setPassword(password);
		adminregister.setSecretkey(secretkey);
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(registeradmin);
		
		ps.setString(1, adminregister.getEmail());
		ps.setString(2, adminregister.getPassword());
		ps.setString(3, adminregister.getSecretkey());
		
		ps.executeUpdate();
		
		System.out.println("New Admin has been succesfully added");
		
		
	}

}
