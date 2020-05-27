package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Main;
import dao.DonorDAO;
import dao.HospitalDAO;
import model.Donations;
import model.Donor;
import model.Hospital;

public class HospitalLoginSuccess {
	
	HospitalDAO hospitaldao = new HospitalDAO();
	Hospital hospital = new Hospital();
	DonorDAO donordao = new DonorDAO();
	Donor donor = new Donor();
//	Donations donations = new Donations();
	String yes;
    public void success() throws SQLException, Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	do {
		System.out.println("Succesfull login");
		System.out.println("1. Update Hospital Details" + "\t" + "\t"+ "2.Get All Blood Donors Information");
		System.out.println("");
		System.out.println("3. Delete Donor profile"+ "\t" + "\t" + "\t"+"4.Update Donor Profile" );
		System.out.println("");
		System.out.println("5. Delete Hospital Profile"+ "\t" + "\t"+ "6.Search for Blood Donors");
		System.out.println("");
		System.out.println("7. Contact us"+ "\t" + "\t" + "\t"+ "\t"+"8.Home");
		System.out.println("");
		System.out.println("\t" +"\t" +"PLEASE ENTER YOUR CHOICE NOW:");
		int choice = 0;

			try {
		choice = Integer.parseInt(br.readLine());
			}
			catch(Exception e){
				System.out.println("Please enter correct input:");
				choice = Integer.parseInt(br.readLine());
			}
		switch(choice) {
		case 1:
		hospitaldao.UpdateHospital(hospital);

		break;
		case 2:
			donordao.DonorsList();
			break;
		case 3:
			System.out.println("Delete Donor Profile");
			int donorid = Integer.parseInt(br.readLine());
			donordao.deleteDonor(donorid);
			break;
		case 4:
			int donor_id1 = 0;
			   donor_id1 = Integer.parseInt(br.readLine());
				if (donordao.searchFordonorId(donor_id1)) {
					int weight1 =0;
					String name1 = br.readLine();
					try {
					 weight1 = Integer.parseInt(br.readLine());
					}
					catch (Exception e){
						 weight1 = Integer.parseInt(br.readLine());
					}
					String addresss1 = br.readLine();
					long phone_no1 = Long.parseLong(br.readLine());
					int b_id1 = Integer.parseInt(br.readLine());
					
					donor.setDonor_id(donor_id1);
					donor.setName(name1);
					donor.setWeight(weight1);
					donor.setAddresss(addresss1);
					donor.setPhone_no(phone_no1);
					donor.setB_id(b_id1);
					
					donordao.updateDonor(donor);
				}
				else {
					System.out.println("Incorrect ID");
					
				}
				break;
		case 5:
			System.out.println("Enter the id related to your hospital to delete your Hospital Profile");
			int id = Integer.parseInt(br.readLine());
			hospitaldao.deletehospital(id);
			break;
		case 6:
			System.out.println("Please enter the ID related to the Blood group you want to search for:");
			System.out.println("O+ - 17");
			System.out.println("A+ - 18");
			System.out.println("A- - 19");
			System.out.println("B- - 20");
			System.out.println("AB+ - 22");
			int blood_id = Integer.parseInt(br.readLine());
			donordao.searchdonorbybloodgroupid(blood_id);
			
			break;
		case 7:
			System.out.println("Currently you can contact us via email and we will get back to you within 24 hours");
			System.out.println("You can drop your email on bloodonors@gmail.com");
			System.out.println("Thank you");
			break;
		case 8:
			System.out.println("Home");
			Main m = new Main();
			String[] args = null;
			m.main(args);
		default:  System.out.println("You entered wrong option");
		}
		System.out.println("Do you want to continue");
		yes = br.readLine();
	    }while(yes.equalsIgnoreCase("yes"));

}	
		
}

    



