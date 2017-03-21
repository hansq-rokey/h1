package com.ibaixiong.manage.service.crm;

import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.SsssGetcash;

public interface SsssGetcashService {
	List<SsssGetcash> getList(Map<String, Object> map);
	
	void update(SsssGetcash cash);
	
	SsssGetcash get(Long id );
}
