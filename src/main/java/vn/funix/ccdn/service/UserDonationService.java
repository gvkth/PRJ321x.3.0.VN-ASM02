package vn.funix.ccdn.service;

import vn.funix.ccdn.entity.UserDonation;

public interface UserDonationService {
	public UserDonation getUserDonation(int theId);
	public int createUserDonation(UserDonation userDonation) throws Exception;
	public void saveUserDonation(UserDonation userDonation) throws Exception;
	public void delete(int theId) throws Exception;
}
