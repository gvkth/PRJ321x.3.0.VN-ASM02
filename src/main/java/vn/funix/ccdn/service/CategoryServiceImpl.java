package vn.funix.ccdn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.funix.ccdn.dao.CategoryDAO;
import vn.funix.ccdn.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO categoryDAO;
	
	
	@Override
	@Transactional
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return categoryDAO.listAll();
	}

}
