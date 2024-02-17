package vn.funix.ccdn.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.funix.ccdn.dao.RecruitmentDAO;
import vn.funix.ccdn.entity.Recruitment;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
	@Autowired
	private RecruitmentDAO recruitmentDAO;
	
	@Override
	@Transactional
	public List<Recruitment> getAll() {
		return recruitmentDAO.getAll();
	}

	@Override
	@Transactional
	public Recruitment get(int theId) {
		return recruitmentDAO.get(theId);
	}

	@Override
	@Transactional
	public long count() {
		return recruitmentDAO.count();
	}

}
