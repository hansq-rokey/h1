package com.ibaixiong.manage.service.mall.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.entity.ErpPanelPrint;
import com.ibaixiong.entity.MallCustomPic;
import com.ibaixiong.entity.util.OrderStatusEnum;
import com.ibaixiong.manage.dao.mall.ErpPanelPrintDao;
import com.ibaixiong.manage.dao.mall.MallCustomPicDao;
import com.ibaixiong.manage.service.mall.MallCustomPicService;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderHistory;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.OrderConstant.OrderOperateTye;
import com.papabear.order.entity.OrderConstant.OrderStatus;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.entity.MallBasicCategoryModelFormat;

@Service
public class MallCustomPicServiceImpl implements MallCustomPicService {

	@Resource
	private MallCustomPicDao customPicDao;
//	@Resource
//	private MallOrderHistoryDao orderHistoryDao;
	@Resource
	private OrderService orderService;
//	private MallOrderDao mallOrderDao;
//	@Resource
//	private MallOrderItemDao MallOrderItemDao;
	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private ErpPanelPrintDao erpPanelPrintDao;

	@Override
	public List<MallCustomPic> queryMallCustomPic(Map<String, Object> map) {
		map.put("invalid", InvalidEnum.FALSE.getInvalidValue());
		PageHelper.startPage((Integer) map.get("pageNo"), PageConstant.pageSize, true);
		return customPicDao.queryCustomPicList(map);
	}

	@Override
	public MallCustomPic getmCustomPic(Long id) {
		return customPicDao.selectByPrimaryKey(id);
	}

	@Override
	public int upload(MultipartFile file, String orderNumber, MallCustomPic addPic, Long adminId) {
//		MallOrder order=mallOrderDao.getByOrderNumber(orderNumber);
		MallOrder order=orderService.getMallOrder(orderNumber);
		MallOrderItem orderItem=null;
		if(order==null){
			return 0;
		}
		MallCustomPic originalPicture = customPicDao.getMallCustomPicByOrderNumberAndType(orderNumber, (byte) 1);// 查询原图
		MallCustomPic serviceHandlePicture = customPicDao.getMallCustomPicByOrderNumberAndType(orderNumber, (byte) 3);// 查询工作人员制作图片
		// 判断是否有原图
		String suffx=null;
		String picName=null;
		String original=file.getOriginalFilename();
		if (originalPicture == null) {
			picName= ALiYunUtil.MALL_PATH_START + ALiYunUtil.converUserIdTo4(adminId) + ALiYunUtil.getCode();
		}else{
			suffx=original.substring(original.lastIndexOf(".")+1, original.length());
			picName = originalPicture.getPicName() + "-2";// 工作人员处理后的图片
		}
		String key = ALiYunUtil.MALL_PATH + ALiYunUtil.SEPARATOR + ALiYunUtil.createYearMonthPath() + ALiYunUtil.SEPARATOR + picName + "." + suffx;
		try {
			ALiYunUtil.uploadFile(key, file);
			addPic.setPath(key);
			addPic.setUrl(ALiYunUtil.IMAGE_URL + key);
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (serviceHandlePicture == null) {
			addPic.setUserId(order.getUserId());
			addPic.setCreateDateTime(new Date());
			addPic.setInvalid(InvalidEnum.FALSE.getInvalidValue());
//			List<MallOrderItem> orderItemList=MallOrderItemDao.queryListByOrderNumber(orderNumber);
			
			List<MallOrderItem> orderItemList=orderService.queryOrderItems(null, orderNumber);
			for(MallOrderItem item:orderItemList)
			{
				if(item.getIsCustomMade().intValue()==1){
					orderItem=item;
					break;
				}
			}
			if(originalPicture==null){
				if(orderItem==null){
					return 0;
				}
				addPic.setModelFormatId(orderItem.getProductModelFormatId());
			}else{
				addPic.setModelFormatId(originalPicture.getModelFormatId());
				
			}
			addPic.setOrderNumber(orderNumber);
			addPic.setUpdateTime(new Date());
			addPic.setSize((int) file.getSize());
			addPic.setStatus((byte) 1);
			addPic.setType((byte)3);//1、原图，2用户裁剪图片，3 工作人员处理图片
			addPic.setPicName(picName);
			addPic.setPicSuffx(suffx);
			customPicDao.insert(addPic);
			// 添加订单历史记录
//			MallOrderHistory history = new MallOrderHistory();
//			history.setOrderNumber(orderNumber);
//			history.setUpdateTime(new Date());
//			history.setOperatorType((byte) 1);
//			history.setUserId(adminId);
//			history.setOperatorIp(null);
//			history.setCreateDateTime(new Date());
//			history.setStatus(OrderStatusEnum.CUSTOM_MADE_CONFIRM.getCode());
//			orderHistoryDao.insert(history);
			orderService.addOrderHistory(orderNumber, adminId, "127.0.0.1", OrderStatus.CUSTOM_MADE_CONFIRM.getStatus(), OrderOperateTye.ADMINISTRATOR.getOperateType());
			if(orderItem!=null){
				MallBasicCategoryModelFormat format=categoryQueryService.getCategoryModelFormat(orderItem.getProductModelFormatId());
				
				//往tbl_erp_panel_print插入一条记录
				ErpPanelPrint print=new ErpPanelPrint();
				print.setCreateDateTime(new Date());
				print.setNum(orderItem.getNum());
				print.setOrderNumber(orderNumber);
				print.setProductFormat(orderItem.getProductModelFormatName());
				print.setProductFormatId(orderItem.getProductModelFormatId());
				print.setProductId(orderItem.getProductId());
				print.setProductName(orderItem.getProductTitle());
				print.setProductWidth(format.getWidth());
				print.setProductLength(format.getLength());
				print.setStatus((byte)0);//0：新增  1，已发货
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				print.setSendTime(calendar.getTime());
				print.setUpdateTime(new Date());
				erpPanelPrintDao.insertSelective(print);
			}
			
			
			
			// 添加订单历史记录
			/**
			history = new MallOrderHistory();
			history.setOrderNumber(orderNumber);
			history.setUpdateTime(new Date());
			history.setOperatorType((byte) 1);
			history.setUserId(adminId);
			history.setOperatorIp(null);
			history.setCreateDateTime(new Date());
			history.setStatus(OrderStatusEnum.CUSTOM_MADING.getCode());
			orderHistoryDao.insert(history);
			**/
		} else {
			serviceHandlePicture.setUserId(originalPicture.getUser().getId());
			serviceHandlePicture.setPath(key);
			serviceHandlePicture.setSize((int) file.getSize());
			serviceHandlePicture.setUpdateTime(new Date());
			serviceHandlePicture.setUrl(ALiYunUtil.IMAGE_URL + key);
			serviceHandlePicture.setHeigth(addPic.getHeigth());
			serviceHandlePicture.setWidth(addPic.getWidth());
			serviceHandlePicture.setPicSuffx(suffx);
			return customPicDao.updateByPrimaryKey(serviceHandlePicture);
		}
//		order.setStatus(OrderStatusEnum.CUSTOM_MADE_CONFIRM.getCode());
		orderService.updateOrderStatus(OrderStatus.CUSTOM_MADE_CONFIRM.getStatus(), order.getOrderNumber());
//		mallOrderDao.updateByPrimaryKeySelective(order);
		return 1;
	}

	@Override
	public List<MallCustomPic> queryMallCustomPicByOrderNumber(String orderNumber) {
		return customPicDao.queryMallCustomPicByOrderNumber(orderNumber);
	}

}
