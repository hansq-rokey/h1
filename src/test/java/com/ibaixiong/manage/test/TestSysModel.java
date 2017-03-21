package com.ibaixiong.manage.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.service.sys.SysModelService;


@RunWith(Log4jJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis-test.xml" })
public class TestSysModel {

	private static Logger logger = Logger.getLogger(TestSysModel.class);

	@Resource
	private SysModelService modelService = null;

	@Test
	public void testGetAdminById() {
		//SysAdmin admin = sysAdminService.getAdminById(1);
		//logger.info(JSON.toJSONString(admin));
	}

	@Test
	public void testGetAdminByLoginName() {
		//SysAdmin admin = sysAdminService.getAdminByLoginName("admin");
		//logger.info(JSON.toJSONString(admin));
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		SysAdmin sysAdmin = new SysAdmin();
		sysAdmin.setUserPwd("admin");
		sysAdmin.setId(1l);
		//logger.info(sysAdminService.updateAdmin(sysAdmin));
	}
}
