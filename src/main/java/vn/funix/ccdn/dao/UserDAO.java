package vn.funix.ccdn.dao;

import java.util.List;

import vn.funix.ccdn.dto.UserRegisterDTO;
import vn.funix.ccdn.entity.Donation;
import vn.funix.ccdn.entity.User;

public interface UserDAO {
	public List<User> getUsers(int iStart, int iPageSize);
	public List<User> getAllUsers();
	public List<User> getAllUsersNotDump();
	public User getAnonymousUser();
	public User getUser(int theId);
	public User findByEmail(String email);
	public long countAll();
	public int create(User theNewUser) throws Exception;
	public int create(UserRegisterDTO theNewUserRegisterDTO) throws Exception;
	public void save(User theUser) throws Exception;
	public void delete(int theId) throws Exception;
	public boolean isEmailAlreadyInUse(String email);
}
