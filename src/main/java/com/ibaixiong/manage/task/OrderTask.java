package com.ibaixiong.manage.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.entity.util.OrderStatusEnum;
import com.ibaixiong.manage.service.crm.SsssInvitationCodeService;
import com.ibaixiong.manage.service.crm.SsssOrderService;
import com.ibaixiong.manage.service.mall.MallOrderService;
import com.ibaixiong.manage.service.smart.SmartDateErrorService;
import com.ibaixiong.manage.service.smart.SmartUpgradeLogService;

/**
 * 订单关闭定时任务
 * @author yaoweiguo
 * @Email  yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年11月4日
 *
 */
@Component
public class OrderTask {

	@Resource
	private MallOrderService mallOrderService;
	@Resource
	private SsssOrderService ssssOrderService;
	@Resource
	private SsssInvitationCodeService ssssInvitationCodeService;
	@Resource
	private SmartDateErrorService smartDateErrorService;
	@Autowired
	private SmartUpgradeLogService smartUpgradeLogService;
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	/**  
     * 定时计算。每天凌晨 01:00 执行一次  
     */    
    public void execute(){
    	logger.debug("关闭订单开始");
    	Calendar now = Calendar.getInstance();
    	now.add(Calendar.HOUR, -12);
    	mallOrderService.listObligation(OrderStatusEnum.OBLIGATION, now.getTime());
    	logger.debug("关闭订单结束");
    }
    
    /**  
     * 定时计算。每天凌晨 01:00 执行一次   
     */    
    public void completeOrders(){
    	logger.debug("完成订单开始");
    	Calendar now = Calendar.getInstance();
    	now.add(Calendar.DAY_OF_MONTH, -15);
    	mallOrderService.completeOrdersList(OrderStatusEnum.SIPPING, now.getTime());
    	logger.debug("完成订单结束"+DateUtil.format(now.getTime()));
    }
    /**
     * 4s店计算冻结金额转到余额任务调度
     * @author zhaolei
     * @date 2016年1月2日
     */
    public void ssssFreezedOrders(){
    	logger.debug("4s店计算冻结金额转余额开始");
    	Calendar now = Calendar.getInstance();
    	now.add(Calendar.DAY_OF_MONTH, -15);
    	ssssOrderService.ssssOrderProfitConvert(now.getTime());
    	logger.debug("4s店计算冻结金额转余额结束"+DateUtil.format(now.getTime()));
    }
    /**
     * 邀请码回归任务调度
     * @author zhaolei
     * @date 2016年1月2日
     */
    public void ssssInvitationCodeReset(){
    	logger.debug("邀请码回归开始");
    	ssssInvitationCodeService.reset(new Date());
    	logger.debug("邀请码回归结束"+DateUtil.format(new Date()));
    }
    /**
     * 冻结金额去除退款订单金额
     * @author 
     * @date 
     */
    public void ssssAfterOrdersProfit(){
    	logger.debug("冻结金额去除退款订单金额开始");
    	Calendar now = Calendar.getInstance();
    	now.add(Calendar.DAY_OF_MONTH, -15);
    	ssssOrderService.ssssAfterOrderProfit(now.getTime());
    	logger.debug("冻结金额去除退款订单金额结束"+DateUtil.format(now.getTime()));
    }
    
    /**
     * 发送智能设备时间戳错误汇总记录
     */
    public void sendSmartDateError(){
    	logger.debug("处理智能温控器时间戳开始");
    	smartDateErrorService.sendSmartDateError();
    	logger.debug("处理智能温控器时间戳结束");
    	
    }
    /**
     * 强制升级
     */
    public void autoSmartUpgrade(Date startTime,Date endTime){
    	logger.debug("强制升级开始");
    	smartUpgradeLogService.autoUpgradeSmart(startTime,endTime);
    	logger.debug("强制升级结束");
    }
    
    /**
     * 强制升级配置文件
     */
    public void autoSmartUpgradeConfig(Date startTime,Date endTime){
    	logger.debug("强制升级配置开始");
    	smartUpgradeLogService.SendSystemConfigSmart(startTime,endTime);
    	logger.debug("强制升级配置结束");
    }
    
    /**
     * 强制升级配置文件
     */
//    public void autoSmartUpgradeConfig(){
//    	logger.debug("强制升级配置开始");
//    	Calendar calendar=Calendar.getInstance();
//    	calendar.set(2016, 11, 23, 0, 0, 0);
//    	smartUpgradeLogService.SendSystemConfigSmart(calendar.getTime(),null);
//    	logger.debug("强制升级配置结束");
//    }
    
    /**  
     * 心跳更新。启动时执行一次，之后每隔2秒执行一次  
     */    
   // @Scheduled(fixedRate = 1000*2)   
    public void print(){  
        logger.debug("Annotation：print run");  
    }  
}
