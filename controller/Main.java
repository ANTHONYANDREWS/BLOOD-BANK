package controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.AdminDAO;
import dao.DonorDAO;
import dao.FeedbackDAO;
import dao.HospitalDAO;
import dao.ReferalDAO;
import model.Admin;
import model.Donations;
import model.Donor;
import model.Hospital;
import model.HospitalLogin;
import service.AdminLoginValidate;
import service.HospitalLoginSuccess;
import service.HospitalLoginValidate;

public class Main {
	
	Donations donations = new Donations();

	public static void main(String[] args) throws Exception {
		String yes = null;

do {
	    System.out.println("\t" +"\t"+"WELCOME TO HYDERBAD BLOOD BANK:");
	    System.out.println("\t"+"-----------------**----------------------");
		System.out.println("\t" +"\t" +"1.Admin Login");
		System.out.println("\t" +"\t" +"2.User Register");
		System.out.println("\t" +"\t" +"3.Hospital");
		System.out.println("\t" +"\t" +"4.Exit");
		System.out.println("Please enter your choice:");

		Donor donor = new Donor();
		DonorDAO donordao = new DonorDAO();
		ReferalDAO referaldao = new ReferalDAO();
		Hospital hospital = new Hospital();
		HospitalDAO hospitaldao = new HospitalDAO();
		HospitalLogin hospitallogin = new HospitalLogin();
		HospitalLoginValidate hospitalloginvalidate = new HospitalLoginValidate();
		Admin admin = new Admin();
		AdminLoginValidate adminloginvalidate = new AdminLoginValidate();
		FeedbackDAO feedbackdao = new FeedbackDAO();
		AdminDAO admindao = new AdminDAO();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;
		try {
		choice = Integer.parseInt(br.readLine());
		}
		catch (Exception e) {
			System.out.println("Please enter correct input:");
		 choice = Integer.parseInt(br.readLine());
		}
		
		switch (choice){
//ADMIN PART
		case 1:
			do {
			System.out.println("Login to access your Admin account");
			System.out.println("Enter your email address");
			String email = br.readLine();
			System.out.println("Enter your password");
			String password =br.readLine();
			admin.setEmail(email);
			admin.setPassword(password);
			//VALIDATING THE EMAIL SO ADMIN CAN LOGIN
			if(adminloginvalidate.validatelogin(admin)==true) {
				do{
				System.out.println("\t" +"1.View Donors List" + "\t" + "\t" + "\t"+ "2.View Hospitals List");
				System.out.println("");
				System.out.println("\t" +"3.Update Donors details"  + "\t" + "\t" + "\t" + "4.Update Hospitals details" );
				System.out.println("");
				System.out.println("\t" +"5.Delete Donor Profile" + "\t" + "\t" + "\t"+ "6.Delete Hospital Profile");
				System.out.println("");
				System.out.println("\t" +"7.Check Donations informations" + "\t" + "\t" + "8.Check Referrals for Blood Donation");
				System.out.println("");
				System.out.println("\t" +"9. Check Feedback"+ "\t" + "\t" + "\t" + "10.Change password");
				System.out.println("");
				System.out.println("\t" +"11. Add new Admin"+ "\t" + "\t" + "\t" +"12.Home" );
				System.out.println("\t" +"\t" +"\t" +"PLEASE ENTER YOUR CHOICE NOW:");


					int opt =0;
					try {
				       opt = Integer.parseInt(br.readLine());
					}
					catch(Exception E) {
						System.out.println("Please Enter correct input");
						opt = Integer.parseInt(br.readLine());

					}
				switch(opt) {
				case 1:
					donordao.DonorsList();
					break;
				case 2:
					hospitaldao.Hospitalslist();
					break;
				case 3:
					donordao.updateDonor(donor);
					break;
				case 4:
					hospitaldao.UpdateHospital(hospital);
					break;
				case 5:
					System.out.println("Enter ID of the profile to Delete");
					int donorid = Integer.parseInt(br.readLine());
					donordao.deleteDonor(donorid);
					break;
				case 6:
					System.out.println("Enter the id related to the hospital to delete the Hospital Profile");
					int id = Integer.parseInt(br.readLine());
					hospitaldao.deletehospital(id);
					break;
				case 7:
					donordao.Donationsinfo();
					break;
				case 8:
					referaldao.seereferals();
					break;
				case 9:
					feedbackdao.checkfeedback();
					break;
				case 10:
					adminloginvalidate.passwordchange();
					break;
				case 11:	
					admindao.registeradmin();
					break;
//				case 12:
//					adminloginvalidate.passwordchange();
//					break;
				case 12:	
					System.out.println("Home");
					Main m = new Main();
					m.main(args);
					break;
				default:  System.out.println("You entered wrong option");
				}
					System.out.println("Do you want to continue");
					yes=br.readLine();
				}
				
				 while(yes.equalsIgnoreCase("yes"));
				
				}
				else {
					System.out.println("Incorrect Email or password");
//					switch(x){ 
//					System.out.println("1.Forgot password??");
//					System.out.println("2. Remember the password and do you want to continue Logging in?");
//					case 1:
////						adminloginvalidate.passwordchange();
//					case 2:	
//					}
					
				}
//					case 2:
			System.out.println("Do you want to continue:");
					yes=br.readLine();
			}while(yes.equalsIgnoreCase("yes"));
			
		break;
			
		
//USER PART		
		case 2: 
			do {
			System.out.println("\t" +"1.Register to Donate Blood"+ "\t" + "\t" + "\t"+ "2.Update your Profile Details");
			System.out.println("");
			System.out.println("\t" +"3.Delete your profile"+ "\t" + "\t" + "\t"+ "\t"+"4.Donate Blood now");
			System.out.println("\t" +"");
			System.out.println("\t" +"5.Refer your friends or contacts to Donate blood"+ "\t" + "\t" + "6.Give your Feedback");
			System.out.println("");
			System.out.println("\t" +"7. Contact us"+ "\t" + "\t" + "\t"+"8.Home");
			System.out.println("");
			System.out.println("\t" +"\t" +"\t" +"PLEASE ENTER YOUR CHOICE NOW:");
			
			int options = 0;
			try{
				options = Integer.parseInt(br.readLine());
			}
			catch(Exception e){
				System.out.println("Please enter correct input:");
				options = Integer.parseInt(br.readLine());
			}
			switch(options) {
			case 1:
			donordao.addDonor(donor);
			break;
		case 2:			
				donordao.updateDonor(donor);
		break;
		case 3:
			System.out.println("Delete");
			int donorid = Integer.parseInt(br.readLine());
			donordao.deleteDonor(donorid);
			break;
		
		case 4:
			Donations donations = new Donations();

			donordao.Donation(donations);
			break;
		case 5:
			referaldao.referrals();
		case 6:
			System.out.println("Please write your Feedback");
			feedbackdao.addfeedback();
			break;
		case 7:
			System.out.println("Currently you can contact us via email and we will get back to you within 24 hours");
			System.out.println("You can drop your email on bloodonors@gmail.com");
			System.out.println("Thank you");
			break;
		case 8:
			System.out.println("Home");
			Main m = new Main();
			m.main(args);
		default:  System.out.println("You entered wrong option");
			}	
		System.out.println("Do you want to continue");
		yes=br.readLine();
		}while(yes.equalsIgnoreCase("yes"));

//HOSPITAL 			
		case 3:
			do{
			System.out.println("1.Register your hospital");
			System.out.println("2.Login");
			System.out.println("3. Home");
			
		
				int option = 0;
			
			try{
				option = Integer.parseInt(br.readLine());
			} 
			catch (Exception e) {
				System.out.println("Please enter correct input:");
				option = Integer.parseInt(br.readLine());
			}
			switch(option) {
			case 1:
				System.out.println("Enter ID of your Hospital");
				int hospital_id = Integer.parseInt(br.readLine());
				if(hospitaldao.checkid(hospital_id)== false) {
				System.out.println("Enter name of the Hospital");
				String hospital_name = br.readLine();
				System.out.println("Enter hospital Phone-number");
				long phone_num = Long.parseLong(br.readLine()); 
				System.out.println("Enter Address of the Hospital");
				String address = br.readLine();
				System.out.println("Enter your username");
				String username = br.readLine();
				System.out.println("Enter your email address");
				String email1 = br.readLine();
				System.out.println("Enter your password");
				String password1 = br.readLine();
				
				hospital.setHospital_id(hospital_id);
				hospital.setHospital_name(hospital_name);
				hospital.setPhone_no(phone_num);
				hospital.setAddress(address);
				hospital.setUsername(username);
				hospital.setEmail(email1);
				hospital.setPassword(password1);
				hospitaldao.addHospital(hospital);
				}
				
				else
				{
					System.out.println("ID Already exists. Please try using a different ID");
				}
			break;
			case 2:
				System.out.println("Enter your email address");
				String emailid = br.readLine();
				System.out.println("Enter your password");
				String pass = br.readLine();
				
				hospitallogin.setEmail(emailid);
				hospitallogin.setPassword(pass);
				
				if (hospitalloginvalidate.validate(hospitallogin) == true) {
					HospitalLoginSuccess hospitalloginsuccess = new HospitalLoginSuccess();
					hospitalloginsuccess.success();
					
				}
				else
				{
					System.out.println("Please check your details");
				}
				break;
			case 3:
				System.out.println("Home");
				Main m = new Main();
				m.main(args);
				break;
		     default:  System.out.println("You entered wrong option");

			}
			
			System.out.println("Do you want to continue");
			yes=br.readLine();

			}while(yes.equalsIgnoreCase("yes"));
			
//EXIT			
		case 4:
			System.out.println("Thank you for Choosing to Donate Blood and save Lives !!");
			System.out.println("Have a good day ahead. :)");
			
			break;
		  default:
				System.out.println("You have entered wrong option:");
		}
		
		System.out.println("Do you want to continue");
		yes=br.readLine();
}
while(yes.equalsIgnoreCase("yes"));

	}
}



