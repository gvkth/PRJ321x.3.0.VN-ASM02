package vn.funix.ccdn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.funix.ccdn.dto.UserRegisterDTO;
import vn.funix.ccdn.entity.Donation;
import vn.funix.ccdn.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	public static final Integer DUMP_USER_ID=1000;
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public List<User> getUsers(int iStart, int iPageSize) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<User> theQuery = currentSession.createQuery("from User ",User.class);
		theQuery.setFirstResult(iStart);
		theQuery.setMaxResults(iPageSize);
		
		
		//execute query and get result list
		List<User> users = theQuery.getResultList();
		
		//return the results
		return users;
	}

	@Override
	public User getUser(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from DB using the primary key
		User user = currentSession.get(User.class, theId);
		
		return user;
	}

	@Override
	public long countAll() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from DB using the primary key
		long count = (Long) currentSession.createQuery("SELECT COUNT(*) FROM User").uniqueResult();

		return count;
	}

	@Override
	public List<User> getAllUsers() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<User> theQuery = currentSession.createQuery("from User",User.class);
		
		//execute query and get result list
		List<User> users = theQuery.getResultList();
		
		//return the results
		return users;
	}
	
	@Override
	public List<User> getAllUsersNotDump() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<User> theQuery = currentSession.createQuery("from User where id<>1000 and deleted=0",User.class);
		
		//execute query and get result list
		List<User> users = theQuery.getResultList();
		
		//return the results
		return users;
	}

	
	@Override
	public User findByEmail(String email) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = currentSession.createQuery("from User where email=:email", User.class);
		theQuery.setParameter("email", email);
		List<User> users = theQuery.getResultList();
		if (users.size()>0) {
			return users.get(0);
		};
		return null;
	}

	@Override
	public int create(User theNewUser) throws Exception {
		int ret = 0;
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			ret =(int) currentSession.save(theNewUser);
		
		}
		catch (Exception e) {
			throw e;
		}
		return ret;
	}
	
	@Override
	public int create(UserRegisterDTO theNewUserRegisterDTO) throws Exception {
		int ret = 0;
		Session currentSession = sessionFactory.getCurrentSession();
		User theNewUser = new User(theNewUserRegisterDTO);
		System.out.println("create by UserRegisterDTO");
		try {
			ret =(int) currentSession.save(theNewUser);
			System.out.println("Here: "+String.valueOf(ret));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return ret;
	}
	
	
	@Override
	public void save(User theUser) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			currentSession.update(theUser);
		
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	
	@Override
	public void delete(int theId) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			currentSession.delete(currentSession.get(User.class, theId));
		}
		catch (Exception e) {
			throw e;
		}
	}
	

	@Override
	public User getAnonymousUser() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from DB using the primary key
		User user = currentSession.get(User.class, DUMP_USER_ID);
		
		return user;
	}

	@Override
	public boolean isEmailAlreadyInUse(String email) {
		//get the current hibernate session
		User userWithEmail = this.findByEmail(email);
		return (userWithEmail!=null);
	}

	
	
}
