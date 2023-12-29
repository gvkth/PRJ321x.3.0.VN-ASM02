package vn.funix.ccdn.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.funix.ccdn.entity.Donation;

@Repository
public class DonationDAOImpl implements DonationDAO {
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Donation> getDonations(int iStart, int iPageSize) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Donation> theQuery = currentSession.createQuery("from Donation",Donation.class);
		theQuery.setFirstResult(iStart);
		theQuery.setMaxResults(iPageSize);
		
		//execute query and get result list
		List<Donation> donations = theQuery.getResultList();
		
		//return the results
		return donations;
	}
	
	@Override
	public List<Donation> getDonations() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = currentSession.createQuery("from Donation",Donation.class);
		List<Donation> donations = theQuery.getResultList();
		return donations;
	}

	@Override
	public Donation getDonation(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from DB using the primary key
		Donation donation = currentSession.get(Donation.class, theId);
		
		return donation;
	}

	@Override
	public long countAll() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from DB using the primary key
		long count = (Long) currentSession.createQuery("SELECT COUNT(*) FROM Donation").uniqueResult();

		return count;
	}

	@Override
	public int create(Donation theDonation) throws Exception{
		int ret = 0;
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			ret =(int) currentSession.save(theDonation);
		
		}
		catch (Exception e) {
			throw e;
		}
		return ret;
	}

	@Override
	public void save(Donation theDonation) throws Exception{
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			currentSession.update(theDonation);
		
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(int theId) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			currentSession.delete(currentSession.get(Donation.class, theId));
		}
		catch (Exception e) {
			throw e;
		}
	}

}
