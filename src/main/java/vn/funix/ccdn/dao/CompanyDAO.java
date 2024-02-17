package vn.funix.ccdn.dao;
import java.util.List;

import vn.funix.ccdn.entity.Company;

public interface CompanyDAO {
	public List<Company> getAll();
	public List<Company> getFeatured();
	public Company get(int id);
	public long count();
}
