package vn.funix.ccdn.dao;

import java.util.List;

import vn.funix.ccdn.entity.Donation;

public interface DonationDAO {
	public List<Donation> getDonations(int iStart, int iPageSize);
	public List<Donation> getDonations();
	
	public Donation getDonation(int theId);
	
	public long countAll();
	public void save(Donation theDonation) throws Exception;
	public int create(Donation theDonation) throws Exception;
	public void delete(int theId) throws Exception;
}
