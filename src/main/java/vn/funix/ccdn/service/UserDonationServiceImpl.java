package vn.funix.ccdn.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.funix.ccdn.dao.UserDonationDAO;
import vn.funix.ccdn.entity.UserDonation;

@Service
public class UserDonationServiceImpl implements UserDonationService {
	@Autowired
	private UserDonationDAO userDonationDAO;
	
	@Override
	@Transactional
	public UserDonation getUserDonation(int theId) {
		// TODO Auto-generated method stub
		return userDonationDAO.getDonation(theId);
	}

	@Override
	@Transactional
	public int createUserDonation(UserDonation userDonation) throws Exception {
		return userDonationDAO.create(userDonation);
	}

	@Override
	@Transactional
	public void saveUserDonation(UserDonation userDonation) throws Exception {
		userDonationDAO.saveUserDonation(userDonation);
	}

	@Override
	@Transactional
	public void delete(int theId) throws Exception {
		userDonationDAO.delete(theId);
	}

}
