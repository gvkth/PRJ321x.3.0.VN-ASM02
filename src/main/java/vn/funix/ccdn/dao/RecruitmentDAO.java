package vn.funix.ccdn.dao;

import java.util.List;

import vn.funix.ccdn.entity.Recruitment;

public interface RecruitmentDAO {
	public List<Recruitment> getAll();
	public Recruitment get (int id);
	public long count();
}
