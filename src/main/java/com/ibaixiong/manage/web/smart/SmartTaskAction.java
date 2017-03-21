/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.smart;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.entity.SmartTask;
import com.ibaixiong.entity.SmartUpgradeLog;
import com.ibaixiong.manage.service.smart.SmartTaskService;
import com.ibaixiong.manage.service.smart.SmartUpgradeLogService;
import com.ibaixiong.manage.task.QuartzTask;
import com.ibaixiong.manage.web.util.DateEditor;
import com.ibaixiong.manage.web.util.Response;
import com.papabear.commons.utils.StringUtils;

@Controller
@RequestMapping("/quartz")
public class SmartTaskAction{
	@Autowired  
    private SchedulerFactoryBean  schedulerFactoryBean;
	@Resource
	private SmartTaskService smarkTaskService;
	@Resource
	private SmartUpgradeLogService logService;
	
	@ResponseBody
	@RequestMapping("/startTask")
	public String addJob(
			@RequestParam(value="id",required=false)Integer id,
			@RequestParam(value="startTime",required=false) Date startTime,
			@RequestParam(value="endTime",required=false) Date endTime,HttpServletRequest request) {
		Response response = new Response();
		try {
			Scheduler sched = schedulerFactoryBean.getScheduler();
			SmartTask task = smarkTaskService.selectByPrimaryKey(id);
			if(task!=null){
				JobDetail jobDetail = new JobDetail(task.getJobName(), task.getJobGroupName(), QuartzTask.class);// 任务名，任务组，任务执行类
				JobDataMap map = jobDetail.getJobDataMap();
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				map.put("methodName", task.getMethodName());
				// 触发器
				CronTrigger trigger = new CronTrigger(task.getTriggerName(), task.getTriggerGroupName());// 触发器名,触发器组
				trigger.setCronExpression(task.getIntervalTime());// 触发器时间设定,例如："0/1 * * * * ?"
				// 启动
				if (!sched.isShutdown() && task.getJobStatus()!=1) {
					sched.scheduleJob(jobDetail, trigger);
					sched.start();
					task.setStartDateTime(startTime);
					task.setEndDateTime(endTime);
					task.setJobStatus((byte)1);//改为执行状态
					task.setUpdateDateTime(new Date());
					smarkTaskService.updateByPrimaryKey(task);
					response.setMessage("启动成功");
				}else{
					response.setMessage("任务已经是启动状态");
					response.setSuccess(false);
				}
			}else{
				response.setMessage("启动任务不存在，启动失败");
				response.setSuccess(false);
			}
		} catch (Exception e) {
			response.setMessage("启动失败");
			response.setSuccess(false);
			throw new RuntimeException(e);
		}
		return JSON.toJSONString(response);
	}
	
	@ResponseBody
	@RequestMapping("/removeTask")
	public String removeJob(@RequestParam(value="id",required=false)Integer id) {
		Response response = new Response();
		try {
			SmartTask task = smarkTaskService.selectByPrimaryKey(id);
			if(task!=null && task.getJobStatus()==1){
				task.setJobStatus((byte)0);
				Scheduler sched = schedulerFactoryBean.getScheduler();
				sched.pauseTrigger(task.getTriggerName(), task.getTriggerGroupName());// 停止触发器
				sched.unscheduleJob(task.getTriggerName(), task.getTriggerGroupName());// 移除触发器
				sched.deleteJob(task.getJobName(), task.getJobGroupName());// 删除任务
				task.setUpdateDateTime(new Date());
				smarkTaskService.updateByPrimaryKey(task);
				response.setMessage("暂停成功");
			}else{
				response.setMessage("任务不存在或者任务已经不是执行状态");
				response.setSuccess(false);
			}
		} catch (Exception e) {
			response.setMessage("暂停失败");
			response.setSuccess(false);
			throw new RuntimeException(e);
		}
		return JSON.toJSONString(response);
	}

	
	/**
	 * 更新升级日志文件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/clearLog")
	public String clearLog(@RequestParam(value="type",required=false) String type){
		Response response = new Response();
		List<SmartUpgradeLog> logs = logService.queryListByType(Short.parseShort(type));
		if(logs.size()>0){
			for(SmartUpgradeLog log : logs){
				log.setInvalid((byte)1);
				int count = logService.updateSmartUpgradeLog(log);
				if(count>0){
					response.setMessage("更新成功");
				}
			}
		}
		return JSON.toJSONString(response);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	//项目启动，自动执行状态为1的定时任务
	public void startInit() {
		try {
			Scheduler sched = schedulerFactoryBean.getScheduler();
			List<SmartTask> tasks = smarkTaskService.queryList();
			for (SmartTask task : tasks) {
				if (task != null) {
					JobDetail jobDetail = new JobDetail(task.getJobName(),
							task.getJobGroupName(), QuartzTask.class);// 任务名，任务组，任务执行类
					JobDataMap map = jobDetail.getJobDataMap();
					map.put("startTime", task.getStartDateTime());
					map.put("endTime", task.getEndDateTime());
					map.put("type", task.getType());
					// 触发器
					CronTrigger trigger = new CronTrigger(
							task.getTriggerName(), task.getTriggerGroupName());// 触发器名,触发器组
					trigger.setCronExpression(task.getIntervalTime());// 触发器时间设定,例如："0/1 * * * * ?"
					// 启动
					if (!sched.isShutdown() && task.getJobStatus() == 1) {
						sched.scheduleJob(jobDetail, trigger);
						sched.start();
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}