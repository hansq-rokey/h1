package com.ibaixiong.manage.service.app;

import java.util.List;

import com.ibaixiong.entity.AppRollImgsItem;

public interface AppRollImgsItemService {
	List<AppRollImgsItem> getListByRollId(Long rollId);
	void update(AppRollImgsItem item);
	void insert(AppRollImgsItem item);
	void del(Long id);
}
