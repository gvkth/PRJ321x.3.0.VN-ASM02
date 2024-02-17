package vn.funix.ccdn.service;

import java.util.List;

import vn.funix.ccdn.entity.Recruitment;

public interface RecruitmentService {
	public List<Recruitment> getAll();
	public Recruitment get(int theId);
	public long count();

}
