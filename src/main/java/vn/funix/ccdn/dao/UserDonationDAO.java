package vn.funix.ccdn.dao;

import java.util.List;

import vn.funix.ccdn.entity.UserDonation;

public interface UserDonationDAO {
	public List<UserDonation> getUserDonations();
	public UserDonation getDonation(int theId);
	public int create(UserDonation theUserDonation) throws Exception;
	public void saveUserDonation(UserDonation theUserDonation) throws Exception;
	public void delete(int theId) throws Exception; 
}
