package vn.funix.ccdn.service;

import java.util.List;

import vn.funix.ccdn.entity.Company;

public interface CompanyService {
	public List<Company> getAll();
	public List<Company> getFeatured();
	public Company get(int theId);
	
	public long count();
}
