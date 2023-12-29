package vn.funix.ccdn.service;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.funix.ccdn.dao.DonationDAO;
import vn.funix.ccdn.entity.Donation;

@Service
public class DonationServiceImpl implements DonationService{
	
	@Autowired
	private DonationDAO donationDAO;

	@Override
	@Transactional
	public List<Donation> getDonations(int iStart, int iPageSize) {
		return donationDAO.getDonations(iStart,iPageSize);
	}

	@Override
	@Transactional
	public Donation getDonation(int theId) {
		return donationDAO.getDonation(theId);
	}

	@Override
	@Transactional
	public long countAll() {
		// TODO Auto-generated method stub
		return donationDAO.countAll();
	}

	@Override
	@Transactional
	public List<Donation> getDonations() {
		return donationDAO.getDonations();
	}
	
	@Override
	@Transactional
	public int create(Donation theDonation)  throws Exception{
		return donationDAO.create(theDonation);
	}
	
	@Override
	@Transactional
	public void save(Donation theDonation)  throws Exception{
		donationDAO.save(theDonation);
	}

	@Override
	@Transactional
	public void delete(int theId) throws Exception {
		donationDAO.delete(theId);
	}

}
