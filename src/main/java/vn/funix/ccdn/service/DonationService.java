package vn.funix.ccdn.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import vn.funix.ccdn.entity.Donation;


public interface DonationService  {
	public List<Donation> getDonations(int iStart, int iPageSize);
	public List<Donation> getDonations();
	public Donation getDonation(int theId);
	public long countAll();
	public int create(Donation theDonation) throws Exception;
	public void save(Donation theDonation) throws Exception;
	public void delete(int theId) throws Exception;
}
