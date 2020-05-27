package dao;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Feedback;
import utility.ConnectionManager;

	public class FeedbackDAO {
		
		Feedback feedback = new Feedback();
		private String Insert_into_feedback = "Insert into FEEDBACK(id,feedbac) VALUES (?,?)";
		private String check_feedback = "Select blood_donor.name, blood_donor.address, feedback.feedbac FROM BLOOD_DONOR INNER JOIN FEEDBACK on blood_donor.donor_id = feedback.id";
		
		public void addfeedback() throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter your ID");
		int ids = Integer.parseInt(br.readLine());
		System.out.println("Enter your feedback");
		String feedbacks = br.readLine();
		feedback.setIds(ids);
		feedback.setFeedback(feedbacks);
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(Insert_into_feedback);
		
		ps.setInt(1, feedback.getIds());
		ps.setString(2, feedback.getFeedback());
		
		ps.executeQuery();
		
		System.out.println("Thank you for taking your valuable Time for your feedback to give us your feedback");
		System.out.println("We will try to take your comments into consideration while making future imporvements.");
		
		}
		
		public void checkfeedback() throws SQLException, Exception {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(check_feedback);
			System.out.println("NAME"+"\t"+"\t"+"LOCATION"+"\t"+"\t"+"\t"+"FEEDBACK");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t" +"\t"+ rs.getString(2) + "\t" + rs.getString(3));
			}
		}
	}



