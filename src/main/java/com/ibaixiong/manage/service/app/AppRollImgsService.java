package com.ibaixiong.manage.service.app;

import java.util.List;

import com.ibaixiong.entity.AppRollImgs;

public interface AppRollImgsService {
	List<AppRollImgs> getList(int pageNo);
	void insert(AppRollImgs roll);
	void update(AppRollImgs roll);
	void del(Long id);
	AppRollImgs get(Long id);
}
