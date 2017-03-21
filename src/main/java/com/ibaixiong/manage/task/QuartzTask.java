package com.ibaixiong.manage.task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ibaixiong.manage.service.smart.SmartUpgradeLogService;
@Component
public class QuartzTask implements Job{

	@Autowired
	private SmartUpgradeLogService smartUpgradeLogService;
	@Autowired
	@Qualifier("myPrintSchedule") 
	private OrderTask task;
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		JobDataMap map = jc.getJobDetail().getJobDataMap();
		Date startTime = (Date) map.get("startTime");
		Date endTime = (Date) map.get("endTime");
		String methodName = (String) map.get("methodName");
		for(Method m : task.getClass().getMethods()){
			if(m.getName().equals(methodName)){
				try {
					m.invoke(task,startTime,endTime);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}  
}
