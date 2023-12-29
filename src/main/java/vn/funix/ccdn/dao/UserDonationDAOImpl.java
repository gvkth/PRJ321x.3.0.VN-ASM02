package vn.funix.ccdn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.funix.ccdn.entity.Donation;
import vn.funix.ccdn.entity.User;
import vn.funix.ccdn.entity.UserDonation;

@Repository
public class UserDonationDAOImpl implements UserDonationDAO {
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
		
		
	@Override
	public List<UserDonation> getUserDonations() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<UserDonation> theQuery = currentSession.createQuery("from UserDonation",UserDonation.class);
				
		//execute query and get result list
		List<UserDonation> userDonations = theQuery.getResultList();
		
		//return the results
		return userDonations;
	}


	@Override
	public UserDonation getDonation(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		UserDonation userDonation = currentSession.get(UserDonation.class, theId);
		return userDonation;
	}


	@Override
	public int create(UserDonation theUserDonation) throws Exception {
		int ret = 0;
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			ret=(int) currentSession.save(theUserDonation);
		}
		catch(Exception e) {
			throw e;
		}
		return ret;
	}


	@Override
	public void saveUserDonation(UserDonation theUserDonation) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			currentSession.update(theUserDonation);
		
		}
		catch (Exception e) {
			throw e;
		}
	}


	@Override
	public void delete(int theId) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			currentSession.delete(currentSession.get(UserDonation.class, theId));
		}
		catch (Exception e) {
			throw e;
		}
	}

}
