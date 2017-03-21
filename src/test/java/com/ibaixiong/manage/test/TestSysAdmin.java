package com.ibaixiong.manage.test;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.service.sys.SysAdminService;


@RunWith(Log4jJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis-test.xml" })
public class TestSysAdmin {

	private static Logger logger = Logger.getLogger(TestSysAdmin.class);

	@Resource
	private SysAdminService sysAdminService = null;

	@Test
	public void testGetAdminById() {
		SysAdmin admin = sysAdminService.getAdminById(1L);
		logger.info(JSON.toJSONString(admin));
		assertEquals(1, admin.getId().intValue());
	}

	@Test
	public void testGetAdminByLoginName() {
		SysAdmin admin = sysAdminService.getAdminByLoginName("admin");
		logger.info(JSON.toJSONString(admin));
		assertEquals("admin", admin.getLoginName());
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		SysAdmin sysAdmin = new SysAdmin();
		sysAdmin.setPhone("15068890314");
		sysAdmin.setId(1L);
		logger.info(sysAdminService.updateAdmin(sysAdmin));
		assertEquals("15068890314", sysAdmin.getPhone());
	}
}
