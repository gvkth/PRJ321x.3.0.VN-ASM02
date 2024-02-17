package vn.funix.ccdn.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.funix.ccdn.entity.Recruitment;

@Repository
public class RecruitmentDAOImpl implements RecruitmentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Recruitment> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Recruitment> theQuery = currentSession.createQuery("from Recruitment",Recruitment.class);
		List<Recruitment> recruitments = theQuery.getResultList();
		return recruitments;
	}

	@Override
	public Recruitment get(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Recruitment recruitment = currentSession.get(Recruitment.class,id);
		return recruitment;
	}

	@Override
	public long count() {
		Session currentSession = sessionFactory.getCurrentSession();
		long count = (Long)currentSession.createQuery("Select count(*) from Recruitment").uniqueResult();
		return count;
	}

}
