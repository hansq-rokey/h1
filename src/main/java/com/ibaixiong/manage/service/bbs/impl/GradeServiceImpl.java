package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.Grade;
import com.ibaixiong.manage.dao.bbs.GradeDao;
import com.ibaixiong.manage.service.bbs.GradeService;
@Service
public class GradeServiceImpl implements GradeService {
	@Resource
	private GradeDao gradeDao;
	@Override
	public List<Grade> getAll() {
		return gradeDao.getAll();
	}
	@Override
	public Long save(Grade grade) {
		return gradeDao.insertSelective(grade);
	}

}
