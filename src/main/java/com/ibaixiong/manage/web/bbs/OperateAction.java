/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.BbsOperate;
import com.ibaixiong.manage.service.bbs.OperateService;

/**
 * 操作管理
 * @description
 * @author zhaolei
 * @create 2015年7月21日
 */
@Controller
@RequestMapping("/bbs/operate")
public class OperateAction {
	@Resource
	private OperateService operateService;

	private String msg = "";
	private int code = 0;
	/**
	 * ajax请求获取下拉列表
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAjaxOperateList.html")
	public void getAjaxOperateList(HttpServletResponse response) {
		List<BbsOperate> dataList = operateService.queryAll();
		Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("operateList", dataList);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg,dataMap)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	
}
