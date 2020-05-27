package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date; // 
import java.time.LocalDate; //not java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Donations;
import model.Donor;
import model.Referals;
import utility.ConnectionManager;


public class DonorDAO {
	Donor donor = new Donor();
	Referals referals = new Referals();

	int donor_id1 =0;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static List<Donor> donorsList = new ArrayList<Donor>();
	
	final String INSERT_INTO_DONOR = "Insert into BLOOD_DONOR(donor_id,name,weight,address, phone_no, b_id) VALUES (?,?,?,?,?,?)";
	final static String GET_ALL_DONORS = "Select * from BLOOD_DONOR";
	private final String UPDATE_DONOR_INFO = "update BLOOD_DONOR set  name=?, weight=?, address=?, phone_no=?, b_id=? where donor_id=?";
	final String DELETE_DONOR_BY_ID =  "delete from BLOOD_DONOR where donor_id = ?";
	final static String GET_DONORS_BY_BLOODID = "Select * from BLOOD_DONOR";
	final String Donations = "INSERT INTO DONATIONS VALUES(seq_donor.nextval,?,TO_DATE(?,'YYYY/MM/DD'))";
	final String View_Donations = "SELECT BLOOD_DONOR.name, blood_donor.weight, blood_donor.phone_no,donations.time_date,blood.b_group FROM BLOOD_DONOR INNER JOIN DONATIONS on blood_donor.donor_id = donations.donorid INNER JOIN BLOOD ON blood_donor.b_id = blood.blood_id "; 

//METHOD TO GET INFORMATION ON DONATIONS MADE 
	public void Donationsinfo() throws SQLException, Exception {
		
		Statement st = ConnectionManager.getConnection().createStatement();
		
		ResultSet rs = st.executeQuery(View_Donations);
		System.out.println("Donor Name"+"\t"+"\t"+"Donor Weight"+"\t"+"Phone"+"\t"+"\t"+"Date"+"\t"+"\t"+"Blood Group");
		while (rs.next())
		{
			System.out.println(rs.getString(1)+"\t"+"\t"+rs.getInt(2)+"\t"+"\t"+rs.getLong(3)+"\t"+rs.getDate(4)+"\t"+rs.getString(5));
		}
		
	}
	
//METHOD TO ADD DONORS	
 	public void addDonor(Donor donor) throws SQLException, Exception {

		DonorDAO donordao = new DonorDAO();

		System.out.println("Enter your ID:");
		int donor_id1 = Integer.parseInt(br.readLine());
		if (donordao.searchFordonorId(donor_id1)==false) {
		System.out.println("Enter your name:");
		String name = br.readLine();
		System.out.println("Enter your weight:");
		int weight = Integer.parseInt(br.readLine());
		System.out.println("Enter your address:");
		String addresss = br.readLine();
		System.out.println("Enter your phone number:");
		long phone_no = Long.parseLong(br.readLine());
		System.out.println("Enter your blood group Id");
		System.out.println("Please enter the ID related to your Blood group:");
		System.out.println("O+ - 17");
		System.out.println("A+ - 18");
		System.out.println("A- - 19");
		System.out.println("B- - 20");
		System.out.println("AB+ - 22");
		System.out.println("Please enter your Blood ID now");		
		int b_id = Integer.parseInt(br.readLine());
		
		donor.setDonor_id(donor_id1);
		donor.setName(name);
		donor.setWeight(weight);
		donor.setAddresss(addresss);
		donor.setPhone_no(phone_no);
		donor.setB_id(b_id);
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(INSERT_INTO_DONOR);
		ps.setInt(1, donor.getDonor_id());
		ps.setString(2, donor.getName());
		ps.setInt(3, donor.getWeight());
		ps.setString(4, donor.getAddresss());
		ps.setLong(5, donor.getPhone_no());
		ps.setInt(6, donor.getB_id());
		ps.executeUpdate();		
		System.out.println("You have succesfully registered as Donor");
		}
		else
		{
			System.out.println("ID Already exits. Please use a different ID");
		}
		
	}

 //METHOD TO GET WHOLE DONORS LIST	
	public void DonorsList() throws SQLException, Exception {
		
		Statement st = ConnectionManager.getConnection().createStatement();

		ResultSet rs = st.executeQuery(GET_ALL_DONORS);
		System.out.println("ID"+"\t"+"NAME"+"\t"+"Wt."+"\t"+"ADDRESS"+"\t"+"\t"+"PHONE_NO"+"\t"+"B_ID");
		
		while(rs.next())
		{		
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
		}
	
		}

//METHOD TO UPDATE DONORS INFORMATION	
	public void updateDonor(Donor donor) throws SQLException, Exception {
		DonorDAO donordao = new DonorDAO();
		int donor_id1 = 0;
	   donor_id1 = Integer.parseInt(br.readLine());
		if (donordao.searchFordonorId(donor_id1)) {
			int weight1 =0;
			long phone_no1 =0;
			System.out.println("Enter name:");
			String name1 = br.readLine();
			try {
			System.out.println("Enter your weight:");	
			 weight1 = Integer.parseInt(br.readLine());
			}
			catch (Exception e){
				System.out.println("Enter your weight:");
				 weight1 = Integer.parseInt(br.readLine());
			}
			System.out.println("Enter your location:");
			String addresss1 = br.readLine();
			try {
			System.out.println("Enter your phone number:");
			phone_no1 = Long.parseLong(br.readLine());
			}
			catch(Exception e) {
				System.out.println("Enter your phone number:");
				 phone_no1 = Long.parseLong(br.readLine());
			}
			System.out.println("Enter your blood group Id");
			System.out.println("Please enter the ID related to your Blood group:");
			System.out.println("O+ - 17");
			System.out.println("A+ - 18");
			System.out.println("A- - 19");
			System.out.println("B- - 20");
			System.out.println("AB+ - 22");
			System.out.println("Please enter your Blood ID now");	
			int b_id1 = Integer.parseInt(br.readLine());
			
			donor.setDonor_id(donor_id1);
			donor.setName(name1);
			donor.setWeight(weight1);
			donor.setAddresss(addresss1);
			donor.setPhone_no(phone_no1);
			donor.setB_id(b_id1);
			

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(UPDATE_DONOR_INFO);
	      
	     ps.setString(1, donor.getName() );
	     ps.setInt(2, donor.getWeight());
		ps.setString(3, donor.getAddresss());
		ps.setLong(4, donor.getPhone_no());
		ps.setInt(5, donor.getB_id());
		ps.setInt(6, donor.getDonor_id());

		
		boolean rowUpdated = ps.executeUpdate() >0;
		if(rowUpdated)
		{
			System.out.println("Updated Donor List");
		}
		else
		{
			System.out.println("No Donor with give ID, Please check the ID");
			
		}
		}
		else {
			System.out.println("Incorrect ID");
			
		}

	}

	public boolean searchFordonorId(int donor_id1) throws Exception {

		boolean result = false;
		
		Statement st = ConnectionManager.getConnection().createStatement();

		ResultSet rs = st.executeQuery(GET_ALL_DONORS);
		while(rs.next()) {
			if(rs.getInt(1) == donor_id1) {
				result = true;
		}
		
		}
		return result;
	}

	public void deleteDonor(int donorid) throws Exception {
		boolean rowDeleted;
		Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_DONOR_BY_ID);
			statement.setInt(1, donorid);
			rowDeleted = statement.executeUpdate() > 0;
		
		if(rowDeleted) {
			System.out.println("Donor is deleted");
	}
		else {
			System.out.println("No Donor with the given id");
		}
	}

	public void searchdonorbybloodgroupid(int blood_id) throws SQLException, Exception {
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery(GET_DONORS_BY_BLOODID);
		int count = 0;
		while(rs.next()) {
			if (rs.getInt(6) ==blood_id) {
				System.out.println(rs.getInt("donor_id")+"\t"+rs.getString("name")+"\t"+rs.getString("weight")+"\t"+rs.getString("address")+"\t"+rs.getString("phone_no")+"\t"+rs.getString("b_id"));
					count++;
			}
		}
		
		if(count ==0) {
			System.out.println("Id not found");
		}
		
	}
	
	public void Donation(Donations donations) throws Exception {
		DonorDAO donordao = new DonorDAO();
		System.out.println("Please enter your Donor ID:");
		int donor_id1 = Integer.parseInt(br.readLine());
		if (donordao.searchFordonorId(donor_id1)) {
			System.out.println("Enter date when you want to donate the blood:");
	        LocalDate dateString = LocalDate.parse(br.readLine());
			donations.setDonor_id(donor_id1);
			donations.setDonation_date(dateString);
			PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(Donations);
			ps.setInt(1, donations.getDonor_id());
			ps.setDate(2, java.sql.Date.valueOf(donations.getDonation_date()));
			ps.executeUpdate();	
			System.out.println("Thank you for choosing to Donate Blood. We will contact you on the date you have mentioned.");
			
	}
		else
		{
			System.out.println("Please enter correct ID");
		}
		}
		
	}

	
	

		
		
		
	
		
	
	
	



