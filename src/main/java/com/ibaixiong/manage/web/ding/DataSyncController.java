package com.ibaixiong.manage.web.ding;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.DdAdminUser;
import com.ibaixiong.entity.DdOrg;
import com.ibaixiong.entity.DdOrgUser;
import com.ibaixiong.entity.DdPermissionOrgUser;
import com.ibaixiong.entity.DdSetWorkingDay;
import com.ibaixiong.entity.DdSetWorkingDayLog;
import com.ibaixiong.entity.DdUser;
import com.ibaixiong.entity.DdWorkLog;
import com.ibaixiong.manage.exception.OApiException;
import com.ibaixiong.manage.service.ding.DdAdminUserService;
import com.ibaixiong.manage.service.ding.DdOrgUserService;
import com.ibaixiong.manage.service.ding.DdPermissionOrgUserService;
import com.ibaixiong.manage.service.ding.DdSetWorkingDayLogService;
import com.ibaixiong.manage.service.ding.DdSetWorkingDayService;
import com.ibaixiong.manage.service.ding.DdUserService;
import com.ibaixiong.manage.service.ding.DdWorkLogService;
import com.ibaixiong.manage.service.ding.DepartmentService;
import com.ibaixiong.manage.web.util.export.ExcelAdapter;
import com.ibaixiong.manage.web.util.export.ExcelBody;
import com.ibaixiong.manage.web.util.export.ExcelExport;

@Controller
@RequestMapping("/ding")
public class DataSyncController {
	@Resource
	private DepartmentService departmentService;
	@Resource
	private DdUserService DdUserService;
	@Resource
	private DdPermissionOrgUserService ddPermissionOrgUserService;
	@Resource
	private DdAdminUserService ddAdminUserService;
	@Resource
	private DdSetWorkingDayLogService ddSetWorkingDayLogService;
	@Resource
	private DdOrgUserService ddOrgUserService;
	@Resource
	private DdWorkLogService ddWorkLogService;
	@Resource
	private DdSetWorkingDayService ddSetWorkingDayService;
	 

	/**
	 * 同步数据
	 * 
	 * @return
	 */
	@RequestMapping("/sync")
	@ResponseBody
	public String userDataSync() {
		ResponseResult result = null;
		try {
			departmentService.syncDepartment();
		} catch (OApiException e) {
			e.printStackTrace();
			result = ResponseResult.result(1, "获取失败，请联系开发人员！");
		}
		result = ResponseResult.result(0, "获取成功！");
		return JSON.toJSONString(result);
	}

	/**
	 * 获取所有有效的部门
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/department")
	public String getDepartments(ModelMap model) {
		List<DdOrg> orgList = departmentService.queryInvalidDepartment(InvalidEnum.FALSE);
		model.addAttribute("orgList", orgList);
		return "ding/info";
	}

	/**
	 * 获取部门下的人员
	 * 
	 * @param args
	 * @throws OApiException
	 */
	@RequestMapping("/department/users")
	public String getDepartmentUsers(@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month
			, ModelMap model) {
		List<DdUser> userList = null;
		if(!"0".equals(departmentId))
			userList = DdUserService.queryDdUsersByDepartmentId(departmentId);
		else
			userList = DdUserService.queryAll();
		Calendar date=Calendar.getInstance();
		if(StringUtils.isBlank(year)){
			year = date.get(date.YEAR)+"";
		}
		if(StringUtils.isBlank(month)){
			month = date.get(date.MONTH)+1+"";
		}
		userList = setUserList(userList, year, month);
		model.addAttribute("userList", userList);
		model.addAttribute("year",year);
		model.addAttribute("month",month);
		model.addAttribute("departmentId",departmentId);
		return "ding/infoList";
	}
	private List<DdUser> setUserList(List<DdUser> userList,String year,String month){
		//设置本月实发本月应发数量，实发数量，部门设置
		if(userList != null && userList.size()>0){
			//查询应发数量
			DdSetWorkingDayLog dklog = ddSetWorkingDayLogService.shouldSendCountByYearMonth(Integer.parseInt(year), Integer.parseInt(month));
			int shouldCount = 0;
			if(dklog != null){
				shouldCount = dklog.getDayCount();
			}
			List<DdUser> userListTemp = new ArrayList<DdUser>();
			for (DdUser ddUser : userList) {
				//查询部门
				List<DdOrgUser> orgUsers = ddOrgUserService.getByUserId(ddUser.getId());
				if(orgUsers != null){
					String orgNames = "";
					for (DdOrgUser ddOrgUser : orgUsers) {
						orgNames=orgNames+ddOrgUser.getOrg().getOrgName()+",";
					}
					if(StringUtils.isNotBlank(orgNames))
						orgNames = orgNames.substring(0, orgNames.length()-1);
					ddUser.setOrgNames(orgNames);
				}
				//查询实际发送数量
				int sendCount = 0;
				List<DdWorkLog> logList = ddWorkLogService.getListByQueryTypeAndYM(ddUser.getId(), 1, year, month);
				if(logList != null && logList.size()>0)
					sendCount = logList.size();
				ddUser.setShouldSendCount(shouldCount);
				ddUser.setSendCount(sendCount);
				userListTemp.add(ddUser);
			}
			return userListTemp;
		}
		return null;
	}
	/**
	 * 获取部门下的人员日志
	 * 
	 * @param args
	 * @throws OApiException
	 */
	@RequestMapping("/department/users/workLog")
	public String workLogList(@RequestParam String userId,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month
			, ModelMap model) {
		Calendar date=Calendar.getInstance();
		if(StringUtils.isBlank(year)){
			year = date.get(date.YEAR)+"";
		}
		if(StringUtils.isBlank(month)){
			month = date.get(date.MONTH)+1+"";
		}
		//获取需要工作的列表
		List<DdWorkLog> logList = ddWorkLogService.getListByQueryTypeAndYM(userId, 1, year, month);
		List<DdSetWorkingDay> workD =  ddSetWorkingDayService.shouldSendCountByYearMonth(year, month);
		List<DdSetWorkingDay> workDTemp = new ArrayList<DdSetWorkingDay>();
		if(workD != null){
			for (DdSetWorkingDay ddSetWorkingDay : workD) {
				//比较年月日查找
				Calendar toCalendar = Calendar.getInstance();
				toCalendar.setTime(ddSetWorkingDay.getWorkDay());
				int y = toCalendar.get(Calendar.YEAR);  
				int m = (toCalendar.get(Calendar.MONTH) + 1);  
		        int d = toCalendar.get(Calendar.DAY_OF_MONTH); 
		        ddSetWorkingDay.setRedTag("0");
		        ddSetWorkingDay.setSendTag("0");
				for (DdWorkLog log : logList) {
					//比对年月日
					toCalendar.setTime(log.getCreateDateTime());
					int y1 = toCalendar.get(Calendar.YEAR);  
					int m1 = (toCalendar.get(Calendar.MONTH) + 1);  
			        int d1 = toCalendar.get(Calendar.DAY_OF_MONTH);
			        if(y == y1 && m == m1 && d == d1){
			        	ddSetWorkingDay.setSendTag("1");
			        	if(log.getGrade() != null && log.getGrade()>0){
			        		ddSetWorkingDay.setRedTag("1");
			        	}
			        	break;
			        }
				}
				workDTemp.add(ddSetWorkingDay);
			}
		}
		model.addAttribute("logList", workDTemp);
		model.addAttribute("year",year);
		model.addAttribute("month",month);
		return "ding/workLogList";
	}
	public static void main(String[] args) {
/*		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(new Date());
		System.out.println("年: " + toCalendar.get(Calendar.YEAR));  
        System.out.println("月: " + (toCalendar.get(Calendar.MONTH) + 1) + "");  
        System.out.println("日: " + toCalendar.get(Calendar.DAY_OF_MONTH));  */
		String startDate = 2016+"-"+2+"-01";
		Date d = DateUtil.parse(startDate);
		Date d1 = DateUtil.getMonendTime(d);
		String endDate = DateUtil.format(d1);
		System.out.println(endDate);
	}
	@ResponseBody
	@RequestMapping(value = "/ajax/department")
	public String getDepartmentsAjax(@RequestParam(value = "userId") String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DdOrg> orgList = departmentService.queryInvalidDepartment(InvalidEnum.FALSE);
		List<DdPermissionOrgUser> permissList = ddPermissionOrgUserService.queryDdPermissionOrgUserByUserId(userId);
		map.put("orgs", orgList);
		map.put("permisses", permissList);
		return JSON.toJSONString(map);
	}

	/**
	 * 设置用户权限
	 * 
	 * @param userId
	 *            用户ID
	 * @param orgId
	 *            部门ID
	 * @param flag
	 *            1：save 0:delete
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ajax/update")
	public String updateDepartmentAndUserAjax(@RequestParam(value = "userId") String userId, @RequestParam String orgId, @RequestParam("flag") int flag) {
		ResponseResult result = null;
		try {
			if (flag == 1) {
				ddPermissionOrgUserService.save(userId, orgId);
			} else {
				ddPermissionOrgUserService.delete(userId, orgId);
			}
		} catch (Exception e) {
			result = ResponseResult.result(1, "更新失败，请联系开发人员！");
		}
		result = ResponseResult.result(0, "更新完成！");
		return JSON.toJSONString(result);
	}
	
	
	/**
	 * 去树形展现页面
	 * @author zhaolei
	 * @date 2016年1月28日
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toTree.html")
	public String perTree(
			@RequestParam(value = "adminId", required = false) String adminId,
			Model model) {
		model.addAttribute("adminId",adminId);
		return "/ding/infoTree";
	}
	@RequestMapping("/perTreeSelect.html")
	public void perTreeSelect(
			@RequestParam(value = "adminId", required = false) String adminId
			,HttpServletResponse response) {
		//查询一级模块
		List<DdOrg> orgList = departmentService.queryInvalidDepartment(InvalidEnum.FALSE);
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//查询角色中的权限列表数据
		//获取设置过的人员ID集合
		List<DdUser> selUsers = DdUserService.queryDbUserByAdminId(adminId);
		String userIds = "";
		if(selUsers != null && selUsers.size()>0){
			for (DdUser ddUser : selUsers) {
				userIds=userIds+","+ddUser.getId();
			}
		}
		if(StringUtils.isNotBlank(userIds)){
			userIds = userIds + ",";
		}
		for (DdOrg model : orgList) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("ID","o" + model.getId());
			dataMap.put("NAME", model.getOrgName());
//			if(isCheck(role, model.getId().toString()))
//				dataMap.put("CHECKED", true);
//			else
			dataMap.put("CHECKED", null);
			dataMap.put("PARENT", 0);
			//List<SysModel> childrenList = null;//sysModelService.getModelsByPid(model.getId());
			//查询人员
			List<DdUser> userList = DdUserService.queryDdUsersByDepartmentId(model.getId());
			List<Map<String, Object>> children = new ArrayList<Map<String,Object>>();
			for (DdUser childrenModel : userList) {
				Map<String, Object> dataMap11 = new HashMap<String, Object>();
				dataMap11.put("ID", childrenModel.getId().toString());
				dataMap11.put("NAME", childrenModel.getUserName());
				//判断是否有选中
				//查询角色权限表中是否有这条数据
				if(isCheck(userIds, childrenModel.getId().toString()))
					dataMap11.put("CHECKED", true);//设置默认选中
				else
					dataMap11.put("CHECKED", null);
				dataMap11.put("PARENT","o" + model.getId());
				children.add(dataMap11);
			}
			dataMap.put("children", children);
			dataList.add(dataMap);
		}
		String str = JSON.toJSONString(dataList);
		PrintWriter writer = null;
	    try {
	   	 	writer = response.getWriter();
	        writer.write(str);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        writer.close();
	    }
	}
	private boolean isCheck(String adminIds,String modelId){
		boolean b = false;
		//比较角色中是否有存在的权限ID如果有下面设置为初始选中状态
		if(StringUtils.isNotBlank(adminIds)){
			if(adminIds.indexOf(","+modelId+",")>=0){
				b = true;
			}
		}
		return b;
	}
	@RequestMapping("/saveAdminUser.html")
	public void saveAdminUser(
			@RequestParam(value = "adminId", required = false) String adminId,
			@RequestParam(value = "privilegeids", required = false) String [] privilegeids
			,HttpServletResponse response) {
		String str = "";
		//先删除已维护过的关系
		ddAdminUserService.deleteByAdminId(adminId);
		if(privilegeids !=null && StringUtils.isNotBlank(privilegeids[0])){
			if(privilegeids.length>0){
				String[] ss = privilegeids[0].split("&");
				String[] newss = new String[ss.length];
				int i = 0;
				for (String string : ss) {
					newss[i]=string.split("=")[1];
					i++;
				}
				privilegeids=newss;
				if(privilegeids.length>0){
					//去掉重复的
					Set<String> set=new HashSet<String>();
					for(String string:privilegeids){
						set.add(string);
					}
					Iterator<String> it=set.iterator();
					while(it.hasNext()){
						String userId=it.next();
						if(userId.indexOf("o")<0){
							DdAdminUser user = new DdAdminUser();
							user.setAdminId(adminId);
							user.setUserId(userId);
							user.setCreateDateTime(new Date());
							user.setStatus(Constant.Status.NORMAL.getStatus());
							user.setInvalid((byte)0);
							ddAdminUserService.insert(user);
						}
					}
//					for (String string : privilegeids) {
//						if(string.indexOf("o")<0){
//							DdAdminUser user = new DdAdminUser();
//							user.setAdminId(adminId);
//							user.setUserId(string);
//							user.setCreateDateTime(new Date());
//							user.setStatus(Constant.Status.NORMAL.getStatus());
//							user.setInvalid((byte)0);
//							ddAdminUserService.insert(user);
//						}
//					}
				}
			}
		}
		PrintWriter writer = null;
	    try {
	   	 	writer = response.getWriter();
	        writer.write(str);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        writer.close();
	    }
	}
	/**
	 * 获取部门下的人员
	 * 
	 * @param args
	 * @throws OApiException
	 */
	@RequestMapping("/department/users/exportExcel")
	public void exportExcel(@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month,
			HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) {
		//设置行
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("序号");
		columnNames.add("部门");
		columnNames.add("姓名");
		columnNames.add("时间");
		columnNames.add("应发报告");
		columnNames.add("已发报告");
		columnNames.add("未发时间");
		columnNames.add("应评报告");
		columnNames.add("未评报告");
		columnNames.add("未评时间");
		ExcelBody excelBody = new ExcelBody(3, 1,columnNames);
		List<DdUser> userList = null;
		if(!"0".equals(departmentId))
			userList = DdUserService.queryDdUsersByDepartmentId(departmentId);
		else
			userList = DdUserService.queryAll();
		Calendar date=Calendar.getInstance();
		if(StringUtils.isBlank(year)){
			year = date.get(date.YEAR)+"";
		}
		if(StringUtils.isBlank(month)){
			month = date.get(date.MONTH)+1+"";
		}
		userList = setExcelUserList(userList, year, month);
		//获取本月最后一天
		String startDate = year+"-"+month+"-01";
		Date d = DateUtil.parse(startDate);
		Date d1 = DateUtil.getMonendTime(d);
		String endDate = DateUtil.format(d1,"yyyy-MM-dd");
		int i = 0;
		for (DdUser ddUser : userList) {
			Map<String ,Object> dataMap = new HashMap<String, Object>();
			dataMap.put("序号", (i+1));
			dataMap.put("部门", ddUser.getOrgNames());
			dataMap.put("姓名", ddUser.getUserName());
			dataMap.put("时间", startDate + " 至 "+endDate);
			dataMap.put("应发报告", ddUser.getShouldSendCount());
			dataMap.put("已发报告", ddUser.getSendCount());
			dataMap.put("未发时间", ddUser.getMap().get("date1"));
			dataMap.put("应评报告", ddUser.getMap().get("t1"));
			dataMap.put("未评报告", ddUser.getMap().get("t2"));
			dataMap.put("未评时间", ddUser.getMap().get("date2"));
			excelBody.addData(dataMap);
		}
		String url = request.getSession().getServletContext().getRealPath("excelModel/rbmb.xlsx");
		ExcelAdapter excelAdapter = new ExcelAdapter(url);
		excelAdapter.addExcelBody(excelBody);
		ExcelExport excelExport = new ExcelExport(excelAdapter);
		try {
			excelExport.exportToResponse(response,"rb.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private List<DdUser> setExcelUserList(List<DdUser> userList,String year,String month){
		//设置本月实发本月应发数量，实发数量，部门设置
		if(userList != null && userList.size()>0){
			//查询应发数量
			DdSetWorkingDayLog dklog = ddSetWorkingDayLogService.shouldSendCountByYearMonth(Integer.parseInt(year), Integer.parseInt(month));
			int shouldCount = 0;
			if(dklog != null){
				shouldCount = dklog.getDayCount();
			}
			List<DdUser> userListTemp = new ArrayList<DdUser>();
			for (DdUser ddUser : userList) {
				//查询部门
				List<DdOrgUser> orgUsers = ddOrgUserService.getByUserId(ddUser.getId());
				if(orgUsers != null){
					String orgNames = "";
					for (DdOrgUser ddOrgUser : orgUsers) {
						orgNames=orgNames+ddOrgUser.getOrg().getOrgName()+",";
					}
					if(StringUtils.isNotBlank(orgNames))
						orgNames = orgNames.substring(0, orgNames.length()-1);
					ddUser.setOrgNames(orgNames);
				}
				//查询实际发送数量
				int sendCount = 0;
				List<DdWorkLog> logList = ddWorkLogService.getListByQueryTypeAndYM(ddUser.getId(), 1, year, month);
				if(logList != null && logList.size()>0)
					sendCount = logList.size();
				ddUser.setShouldSendCount(shouldCount);
				ddUser.setSendCount(sendCount);
				ddUser.setMap(getOtherCell(ddUser.getId(), year, month));
				userListTemp.add(ddUser);
			}
			return userListTemp;
		}
		return null;
	}
	private Map<String , String> getOtherCell(String userId,String year,String month){
		Map<String , String> map = new HashMap<String, String>();
		//获取需要工作的列表
		List<DdWorkLog> logList = ddWorkLogService.getListByQueryTypeAndYM(userId, 1, year, month);
		List<DdSetWorkingDay> workD =  ddSetWorkingDayService.shouldSendCountByYearMonth(year, month);
		String date1= "";//未发时间
		int t1 = 0;//应评报告
		int t2=0;//未评报告
		String date2 = "";//未评时间
		if(workD != null){
			for (DdSetWorkingDay ddSetWorkingDay : workD) {
				//比较年月日查找
				Calendar toCalendar = Calendar.getInstance();
				toCalendar.setTime(ddSetWorkingDay.getWorkDay());
				int y = toCalendar.get(Calendar.YEAR);  
				int m = (toCalendar.get(Calendar.MONTH) + 1);  
		        int d = toCalendar.get(Calendar.DAY_OF_MONTH); 
		        ddSetWorkingDay.setRedTag("0");
		        ddSetWorkingDay.setSendTag("0");
				for (DdWorkLog log : logList) {
					//比对年月日
					toCalendar.setTime(log.getCreateDateTime());
					int y1 = toCalendar.get(Calendar.YEAR);  
					int m1 = (toCalendar.get(Calendar.MONTH) + 1);  
			        int d1 = toCalendar.get(Calendar.DAY_OF_MONTH);
			        if(y == y1 && m == m1 && d == d1){
			        	ddSetWorkingDay.setSendTag("1");
			        	if(log.getGrade() != null && log.getGrade()>0){
			        		ddSetWorkingDay.setRedTag("1");
			        	}
			        	break;
			        }
				}
				if("1".equals(ddSetWorkingDay.getSendTag())){
					t1 = t1 + 1;//发了就要评 应评报告
					//说明是日报已经发了
					//说明未评日报
					if("0".equals(ddSetWorkingDay.getRedTag())){
						t2=t2+1;
						date2 = date2+DateUtil.format(ddSetWorkingDay.getWorkDay(), "yyyy-MM-dd")+",";
					}
				}else{
					//说明这天没发日报
					date1 = date1+DateUtil.format(ddSetWorkingDay.getWorkDay(), "yyyy-MM-dd")+",";
				}
			}
		}
		if(StringUtils.isNotBlank(date1))
			date1 = date1.substring(0,date1.length()-1);
		if(StringUtils.isNotBlank(date2))
			date2 = date2.substring(0,date2.length()-1);
		map.put("date1", date1);
		map.put("t1", t1+"");
		map.put("t2", t2+"");
		map.put("date2", date2);
		return map;
	}
}
