package com.ibaixiong.manage.web.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.SysAdminRole;
import com.ibaixiong.entity.SysOrg;
import com.ibaixiong.manage.service.sys.SysOrgService;

/**
 * 部门相关操作
 * @author 赵磊
 * @create 2015年8月27日
 */
@Controller
@RequestMapping("/system/org")
public class SysOrgAction {
	@Resource
	SysOrgService sysOrgService;
	
	/**
	 * 获取部门下拉
	 * @author zhaolei
	 * @date 2015年8月27日
	 * @return
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"orgs": [
		            {
		                "id": 1,					//ID
		                "name": 总公司,			//人员名称
		            }, 
	                ......
	        	]
	    	}
		}
	 */
	@RequestMapping("/getSelectOrgList.html")
	public void getSelectOrgList(Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		//根据角色获取角色下的用户
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		List<SysOrg> dataList = sysOrgService.getAllOrgList();
		for (SysOrg org : dataList) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", org.getId());
			m.put("name", org.getName());
			mapData.add(m);
		}
		map.put("orgs", mapData);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg,map)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
}
