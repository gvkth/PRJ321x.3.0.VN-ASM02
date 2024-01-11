package vn.funix.ccdn.service;

import java.util.List;

import vn.funix.ccdn.dto.UserRegisterDTO;
import vn.funix.ccdn.entity.Donation;
import vn.funix.ccdn.entity.User;

public interface UserService {
	public List<User> getUsers(int iStart, int iPageSize);
	public List<User> getAllUsers();
	public List<User> getAllUsersNotDump();
	public User get(int theId);
	public User getAnonymousUser();
	public User getByUsername(String userName);
	public long count();
	public int create(User theDonation) throws Exception;
	public int create(UserRegisterDTO theUserRegisterDTO) throws Exception;
	public void save(User theDonation) throws Exception;
	public void delete(int theId) throws Exception;
}
