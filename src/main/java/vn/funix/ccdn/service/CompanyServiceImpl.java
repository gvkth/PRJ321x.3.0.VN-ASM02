package vn.funix.ccdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.funix.ccdn.dao.CompanyDAO;
import vn.funix.ccdn.entity.Company;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyDAO companyDAO;

	@Override
	@Transactional
	public List<Company> getAll() {
		return companyDAO.getAll();
	}

	@Override
	@Transactional
	public long count() {
		return companyDAO.count();
	}

	@Override
	@Transactional
	public Company get(int theId) {
		return companyDAO.get(theId);
	}

	@Override
	@Transactional
	public List<Company> getFeatured() {
		return companyDAO.getFeatured();
	}
	
	
}
