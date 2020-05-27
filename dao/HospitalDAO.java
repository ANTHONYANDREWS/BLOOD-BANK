package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Hospital;
import utility.ConnectionManager;

public class HospitalDAO {
	
	String addhospital = "Insert into HOSPITAL(HOSPITALID,NAME,PHONE_NO,ADDRESS,USERNAME,EMAIL,PASSWORD)VALUES(?,?,?,?,?,?,?)";
	String hospitallist = "Select * from HOSPITAL";
	String updatehospital = "update HOSPITAL set NAME=?, PHONE_NO=?, ADDRESS=?,USERNAME=?, EMAIL=?,PASSWORD=? where HOSPITALID=?";
	String Deletehospital = "Delete from Hospital where HOSPITALID=?";
	String adminhospital = "SELECT HOSPITALID, NAME, PHONE_NO, ADDRESS, EMAIL FROM HOSPITAL";
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public void addHospital(Hospital hospital) throws SQLException, Exception {
		HospitalDAO hospitaldao = new HospitalDAO();
	
		int hospital_id = hospital.getHospital_id();
		String hospital_name = hospital.getHospital_name();
		long Phone_number = hospital.getPhone_no();
		String address = hospital.getAddress();
		String username = hospital.getUsername();
		String email = hospital.getEmail();
		String password = hospital.getPassword();
		
		ConnectionManager cm = new ConnectionManager();
		
		PreparedStatement ps = cm.getConnection().prepareStatement(addhospital);
		ps.setInt(1, hospital_id);
		ps.setString(2, hospital_name);
		ps.setLong(3, Phone_number);
		ps.setString(4, address);
		ps.setString(5, username);
		ps.setString(6, email);
		ps.setString(7, password);
		
		ps.executeUpdate();
		System.out.println("Your Hospital has been succesfully registered");
	}
	
//LIST OF HOSPITALS THAT APPEARS ON ADMIN PART
	public void Hospitalslist() throws SQLException, Exception {
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery(adminhospital);
		System.out.println("ID"+"\t"+"HOSPITAL NAME"+"\t"+"\t"+"PHONE"+"\t"+"\t"+"LOCATION"+"\t"+"EMAIL");
		while (rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+"\t"+"\t"+rs.getLong(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
		}
	}
	
//UPDATING HOSPITALS INFO
	public void UpdateHospital(Hospital hospital) throws SQLException, Exception {
		HospitalDAO hospitaldao = new HospitalDAO();
		System.out.println("Update Hospital Details now:");
		System.out.println("Enter ID of your Hospital");
		int hospital_id = Integer.parseInt(br.readLine());
		if(hospitaldao.checkid(hospital_id)) {
		System.out.println("Enter name of the Hospital");
		String hospital_name = br.readLine();
		System.out.println("Enter hospital Phone-number");
		long phone_num = Long.parseLong(br.readLine()); 
		System.out.println("Enter Address of the Hospital");
		String address = br.readLine();
		System.out.println("Enter your username");
		String username = br.readLine();
		System.out.println("Enter your email address");
		String email = br.readLine();
		System.out.println("Enter your password");
		String password = br.readLine();
		
		hospital.setHospital_id(hospital_id);
		hospital.setHospital_name(hospital_name);
		hospital.setPhone_no(phone_num);
		hospital.setAddress(address);
		hospital.setUsername(username);
		hospital.setEmail(email);
		hospital.setPassword(password);
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(updatehospital);
		
		ps.setString(1, hospital.getHospital_name());
		ps.setLong(2, hospital.getPhone_no());
		ps.setString(3, hospital.getAddress());
		ps.setString(4, hospital.getUsername());
		ps.setString(5, hospital.getEmail());
		ps.setString(6, hospital.getPassword());
		ps.setInt(7, hospital.getHospital_id());
		
		boolean rowupdated = ps.executeUpdate() >0;
		if (rowupdated) {
			System.out.println("Hospital Sign in Details updated successfully");
			
		}
		else {
			System.out.println("Nothing was updated");
		}
		}
		else
		{
			System.out.println("Please enter correct ID:");
			
		}
	}

//METHOD TO CHECK ID BEFORE UPDATING THE DETAILS	
	public boolean checkid(int hospital_id) throws SQLException, Exception {
		boolean result = false;

		Statement st = ConnectionManager.getConnection().createStatement();
		
		ResultSet rs = st.executeQuery(hospitallist);
		while(rs.next())
		{
			if(rs.getInt(1)==hospital_id) {
				result = true;
			}
			break;
		}
	
	return result;
}
//METHOD TO DELETE HOSPITAL FROM LIST
	public void deletehospital(int id) throws SQLException, Exception {
		boolean rowDeleted;
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(Deletehospital);
		ps.setInt(1, id);
		rowDeleted = ps.executeUpdate() > 0;
		if (rowDeleted) {
			System.out.println("Your Hospital Profile was succesfully deleted");
		}
		else {
			System.out.println("The entered Id does not exist. Please try again using correct Id");
		}
		
	}
}
