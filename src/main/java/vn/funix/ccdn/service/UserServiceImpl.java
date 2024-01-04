package vn.funix.ccdn.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.funix.ccdn.dao.UserDAO;
import vn.funix.ccdn.entity.User;

@Service
public class UserServiceImpl implements UserService
	//,UserDetailsService 
{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public List<User> getUsers(int iStart, int iPageSize) {
		return userDAO.getUsers(iStart,iPageSize);
	}

	@Override
	@Transactional
	public User get(int theId) {
		return userDAO.getUser(theId);
	}
	
	@Override
	@Transactional
	public long count() {
		return userDAO.countAll();
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	@Override
	@Transactional
	public List<User> getAllUsersNotDump() {
		return userDAO.getAllUsersNotDump();
	}

	@Override
	@Transactional
	public int create(User theUser) throws Exception {
		return userDAO.create(theUser);
	}

	@Override
	@Transactional
	public void save(User theUser) throws Exception {
		userDAO.save(theUser);
	}

	@Override
	@Transactional
	public void delete(int theId) throws Exception {
		userDAO.delete(theId);
	}

	@Override
	@Transactional
	public User getByUsername(String userName) {
		return userDAO.findByEmail(userName);
	}

	@Override
	@Transactional
	public User getAnonymousUser() {
		return userDAO.getAnonymousUser();
	}

	
}
