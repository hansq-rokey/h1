package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.Grade;

public interface GradeService {
	List<Grade> getAll();
	Long save(Grade grade);
}
