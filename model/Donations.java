package model;

import java.time.LocalDate;

//import java.util.Date;

public class Donations {
    private int donations_id;
    private int donor_id;
	private LocalDate donation_date;
	

	public int getDonations_id() {
		return donations_id;
	}
	public void setDonations_id(int donations_id) {
		this.donations_id = donations_id;
	}
	public int getDonor_id() {
		return donor_id;
	}
	public void setDonor_id(int donor_id) {
		this.donor_id = donor_id;
	}
	public LocalDate getDonation_date() {
		return donation_date;
	}
	public void setDonation_date(LocalDate donation_date) {
		this.donation_date = donation_date;
	}
	

}