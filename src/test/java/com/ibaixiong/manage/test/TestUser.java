package com.ibaixiong.manage.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.entity.User;
import com.ibaixiong.manage.service.bbs.UserService;

@RunWith(Log4jJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis-test.xml" })
public class TestUser {
	@Resource
	UserService userService;
	private static Logger logger = Logger.getLogger(TestUser.class);
	@Test
	public void testQuerList() {
//		List<User> data = userService.queryUserList(Byte.parseByte("1"), "admin", 1L);
//		logger.info(JSON.toJSONString(data));
	}
	@Test
	public void testUpdateStatus(){
		User user = new User();
		user.setId(1L);
		user.setStatus(Byte.parseByte("-2"));
		user.setBlockTime(new Date());
		user.setBlockMemo("test");
		userService.updateStatus(user);
	}
}
