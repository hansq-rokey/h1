package com.ibaixiong.manage.service.smart.impl;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.entity.Smart;
import com.ibaixiong.entity.SmartUpgradeLog;
import com.ibaixiong.manage.dao.base.DictCodeDao;
import com.ibaixiong.manage.dao.smart.SmartDao;
import com.ibaixiong.manage.dao.smart.SmartUpgradeLogDao;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.smart.SmartUpgradeLogService;

@Service
public class SmartUpgradeLogServiceImpl implements SmartUpgradeLogService {
	
	@Autowired
	SmartDao smartDao;
	@Autowired
	SmartUpgradeLogDao logDao;
	@Autowired
	DictCodeDao codeDao;
	
	@Override
	public void autoUpgradeSmart(Date startDate,Date endDate){
		List<Smart> list= smartDao.queryNotUpgradeSmartByOnline(Constant.SmartOnline.BOOT.getOnline(),(short)192,startDate,endDate);
		for(Smart smart:list){
			try {
				
				this.sendMessage(smart.getBxcode(),smart.getBxid());
				this.addUpgradeLog(smart.getBxcode(),smart.getBxid(), smart.getcVersion());
				Thread.sleep(600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取参数
	 * @return
	 */
	public Map<String, String> getDictCode(){
		List<DictCode> list=codeDao.queryDictCodeByDictType("SMART_CONFIG");
		Map<String, String> map=new HashMap<String, String>();
		for(DictCode dc:list){
			map.put(dc.getDictCodeName(), dc.getDictCodeValue());
		}
		
		return map;
	}
	@Override
	public void SendSystemConfigSmart(Date startDate,Date endDate){
		
		List<Smart> list= smartDao.queryNotUpgradeSmartByOnline(Constant.SmartOnline.BOOT.getOnline(),(short)259,startDate,endDate);
		Map<String, String> dictCodeMap=this.getDictCode();
		byte isDispalyPower=Byte.parseByte(dictCodeMap.get("isDispalyPower"));
		byte isDisplayRoom=Byte.parseByte(dictCodeMap.get("isDisplayRoom"));
		byte isDisplayHumidity=Byte.parseByte(dictCodeMap.get("isDisplayHumidity"));
		byte temperatureUp=Byte.parseByte(dictCodeMap.get("temperatureUp"));
		byte temperatureDown=Byte.parseByte(dictCodeMap.get("temperatureDown"));
		for(Smart smart:list){
			try {
				
				this.sendSystemConfigMessage(smart.getBxcode(),smart.getBxid(), 1035, isDispalyPower, isDisplayRoom ,isDisplayHumidity ,temperatureUp ,temperatureDown,smart.getcVersion() );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		SmartUpgradeLogServiceImpl s=new SmartUpgradeLogServiceImpl();
		s.sendSystemConfigMessage("507ccb189b0342f3b61f53a1055ad76d", "BH026001941995BD", 1035, (byte)1,(byte) 1, (byte)1, (byte)31, (byte)10,"1.3.8.11");
	}
	@Override
	public int upgradeSmart(Smart smart){
		
		try {
			//nick 就是bxid，查看sql
			this.sendMessage(smart.getBxcode(),smart.getBxid());
			this.addUpgradeLog(smart.getBxcode(),smart.getBxid(), smart.getcVersion());
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	@Override
	public int SendSystemConfigSmart(Smart smart) {
		try {
			Map<String, String> dictCodeMap=this.getDictCode();
			byte isDispalyPower=Byte.parseByte(dictCodeMap.get("isDispalyPower"));
			byte isDisplayRoom=Byte.parseByte(dictCodeMap.get("isDisplayRoom"));
			byte isDisplayHumidity=Byte.parseByte(dictCodeMap.get("isDisplayHumidity"));
			byte temperatureUp=Byte.parseByte(dictCodeMap.get("temperatureUp"));
			byte temperatureDown=Byte.parseByte(dictCodeMap.get("temperatureDown"));
//			s.sendSystemConfigMessage("507ccb189b0342f3b61f53a1055ad76d", "BH026001941995BD", 1035, (byte)1,(byte) 1, (byte)1, (byte)31, (byte)10,"1.3.8.11");
			this.sendSystemConfigMessage(smart.getBxcode(),smart.getBxid(), smart.getUserId().intValue(), isDispalyPower,isDisplayRoom, isDisplayHumidity, temperatureUp, temperatureDown,smart.getcVersion());
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	public void addUpgradeLog(String bxcode,String bxid,String cVersion){
		SmartUpgradeLog log=new SmartUpgradeLog();
		log.setBxcode(bxcode);
		log.setBxid(bxid);
		log.setcVersion(cVersion);
		log.setCreateTime(new Date());
		log.setInvalid((byte)0);
		log.setIp("");
		log.setStatus((byte)1);
		log.setType((short)192);
		log.setUserId(1035);
		logDao.insertSelective(log);
	}
	
	public void sendMessage(String bxcode,String bxid){
		ByteBuffer buffer = ByteBuffer.allocate(105);
		buffer.put(Message.tag);
		buffer.putShort(Message.version);
		buffer.put(Message.c_version.getBytes());
		buffer.putInt(Message.length);
		buffer.putShort(Message.messageType);
		buffer.put(bxid.getBytes());
		buffer.put(bxcode.getBytes());
		buffer.put("415dd20d782448b0b3ae7d7b9b8792a9".getBytes());
		buffer.putInt(Message.paramter);
		buffer.putInt(1035);
		this.client(buffer);
		buffer=null;
	}
	/**
	 * 发送系统配置信息
	 * @param bxcode
	 * @param bxid
	 */
	public void sendSystemConfigMessage(String bxcode,String bxid,int userId,byte isDispalyPower,byte isDisplayRoom,byte isDisplayHumidity,byte temperatureUp,byte temperatureDown,String c_version){
		short messageType = 0x102;
		ByteBuffer buffer = ByteBuffer.allocate(114);
		buffer.put(Message.tag);
		buffer.putShort((short)0x01);
		byte[] c_versionByte = c_version.getBytes();
		// 前面补0
		for (int i = 0; i < 8 - c_versionByte.length; i++) {
			buffer.put((byte)0x00);
		}
		buffer.put(c_versionByte);
		buffer.putInt(0x72);
		buffer.putShort(messageType);
		buffer.put(bxid.getBytes());
		buffer.put(bxcode.getBytes());
		buffer.put("415dd20d782448b0b3ae7d7b9b8792a9".getBytes());
		buffer.putInt(userId);
		buffer.put(isDispalyPower);//电量
		buffer.put(isDisplayRoom);//室内温度
		buffer.put(isDisplayHumidity);//湿度
		buffer.put(temperatureUp);//温度上限
		buffer.put(temperatureDown);//温度下限
		buffer.putInt(0);
		buffer.putInt(0);
		this.client(buffer);
		buffer=null;
	}
	/**
	 * 发送功率参数配置信息
	 * @param bxcode
	 * @param bxid
	 */
	public void sendPowerSizeMessage(String bxcode,String bxid,int userId,String c_version){
		short messageType = 0x100;
		ByteBuffer buffer = ByteBuffer.allocate(117);
		buffer.put(Message.tag);
		buffer.putShort((short)0x01);
		byte[] c_versionByte = c_version.getBytes();
		// 前面补0
		for (int i = 0; i < 8 - c_versionByte.length; i++) {
			buffer.put((byte)0x00);
		}
		buffer.put(c_versionByte);
		buffer.putInt(0x75);
		buffer.putShort(messageType);
		buffer.put(bxid.getBytes());
		buffer.put(bxcode.getBytes());
		buffer.put("526144632b8845e0b41dd49a0f1eba6e".getBytes());
		buffer.putInt(userId);
		buffer.putInt(800);
		buffer.put((byte)10);//温补值
		buffer.put((byte)0);//湿补值
		buffer.put((byte)0);//备用1
		buffer.put((byte)0);//备用2
		buffer.putInt(0);
		buffer.putInt(0);
		this.client(buffer);
		buffer=null;
	}
	/**
	 * 模拟读取智能终端配置信息
	 * @param bxcode
	 * @param bxid
	 * @param userId
	 * @param token
	 * @param c_version
	 */
	public void sendreadMessage(String bxcode,String bxid,int userId,String token,String c_version){
		short messageType = 0x104;
		ByteBuffer buffer = ByteBuffer.allocate(105);
		buffer.put(Message.tag);
		buffer.putShort((short)0x01);
		byte[] c_versionByte = c_version.getBytes();
		// 前面补0
		for (int i = 0; i < 8 - c_versionByte.length; i++) {
			buffer.put((byte)0x00);
		}
		buffer.put(c_versionByte);
		buffer.putInt(0x69);
		buffer.putShort(messageType);
		buffer.put(bxid.getBytes());
		buffer.put(bxcode.getBytes());
		buffer.put(token.getBytes());
		buffer.putInt(4);
		buffer.putInt(userId);
		this.client(buffer);
		buffer=null;
	}

	static class Message {
		public static byte tag = 0x11; // 消息标识符
		public static short version = 0x01;// 协议接口版本号
		public static String c_version = "1.3.8.11";// 客户端版本号 8byte
		public static int length = 0x69;// 协议整体长度
		public static short messageType = 0xC0;// 消息类型
		public static String bxid = "QZ01ACCF23B72908";// 白熊设备编码，8byte的长度，产品线(2byte)+批次(2byte)+MAC地址(6byte)
		public static String bxcode = "021a0d5b3d67418db53ecb406a46cbdc";// 白熊设备软编码，在设备向服务端注册激活时生成唯一编码，重写回智能终端，服务端根据此编码识别终端的唯一性，长度：32byte
		public static String token = "415dd20d782448b0b3ae7d7b9b8792a9";// 会话token，32byte
		public static int paramter = 0x04;// 参数长
		public static int userId = 1237;// 白熊注册用户ID

	}
	//发送升级消息
	public void client(ByteBuffer bf) {
		try {
			Socket s = new Socket("smartserver.ibaixiong.com", 8082);
			// 创建socket连接
			s.getOutputStream().write(bf.array());
			s.getOutputStream().flush();
			s.getOutputStream().close();
			if(!s.isClosed()){
				s.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<SmartUpgradeLog> querySmartUpgradeLogsByBxid(String bxid,Integer pageNo) {
		
		PageHelper.startPage(pageNo, 10, "id desc");
		return logDao.querySmartUpgradeLogsByBxid(bxid);
	}
	@Override
	public List<SmartUpgradeLog> queryListByType(Short type) {
		return logDao.queryListByType(type);
	}
	@Override
	public int updateSmartUpgradeLog(SmartUpgradeLog log) {
		return logDao.updateByPrimaryKeySelective(log);
	}



}
