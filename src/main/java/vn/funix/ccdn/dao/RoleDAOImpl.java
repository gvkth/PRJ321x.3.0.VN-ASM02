package vn.funix.ccdn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.funix.ccdn.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Role> getAllRoles() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Role> theQuery = currentSession.createQuery("from Role",Role.class);
		List<Role> roles = theQuery.getResultList();
		return roles;
	}

}
