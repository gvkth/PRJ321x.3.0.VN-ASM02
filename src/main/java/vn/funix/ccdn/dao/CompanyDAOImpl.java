package vn.funix.ccdn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.funix.ccdn.entity.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long count() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from DB using the primary key
		long count = (Long) currentSession.createQuery("SELECT COUNT(*) FROM Company").uniqueResult();

		return count;
	}

	@Override
	public List<Company> getAll() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Company> theQuery = currentSession.createQuery("from Company",Company.class);
		
		//execute query and get result list
		List<Company> companies = theQuery.getResultList();
		
		//return the results
		return companies;
	}

	@Override
	public Company get(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Company company = currentSession.get(Company.class, theId);
		return company;
	}

	@Override
	public List<Company> getFeatured() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		/*TODO: cài đặt thực lại method này. Hiện đang test với where status=1*/
		Query<Company> theQuery = currentSession.createQuery("from Company where status=1",Company.class);
		
		//execute query and get result list
		List<Company> companies = theQuery.getResultList();
		return companies;
	}

}
