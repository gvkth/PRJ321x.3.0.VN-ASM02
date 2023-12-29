package vn.funix.ccdn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.funix.ccdn.dao.RoleDAO;
import vn.funix.ccdn.entity.Role;

@Service
public class RoleServiceImple implements RoleService{
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	@Transactional
	public List<Role> getAll() {
		return roleDAO.getAllRoles();
	}
}
