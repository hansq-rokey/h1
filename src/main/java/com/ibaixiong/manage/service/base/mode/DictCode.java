package com.ibaixiong.manage.service.base.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.papabear.order.entity.MallOrderHistory;



public class DictCode{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(name="update_time")
	private Date updateTime;

	/* 标明记录的业务状态 */
    private Byte status;

	/* 标明该记录是否有效 */
	private Boolean invalid = false;


    private String dictType;

    private String dictName;

    private String dictCodeName;

    private String dictCodeValue;

    private Integer sort;

    private Byte type;
    
    private Byte isDisplay;

    private Long adminId;
    //商城详情显示，流程节点是否结束,默认false
    private Boolean flow=false;
    //商城详情展示
    private MallOrderHistory orderHistory;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictCodeName() {
        return dictCodeName;
    }

    public void setDictCodeName(String dictCodeName) {
        this.dictCodeName = dictCodeName == null ? null : dictCodeName.trim();
    }

    public String getDictCodeValue() {
        return dictCodeValue;
    }

    public void setDictCodeValue(String dictCodeValue) {
        this.dictCodeValue = dictCodeValue == null ? null : dictCodeValue.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

	public Boolean getFlow() {
		return flow;
	}

	public void setFlow(Boolean flow) {
		this.flow = flow;
	}

	public MallOrderHistory getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(MallOrderHistory orderHistory) {
		this.orderHistory = orderHistory;
	}
	
	public Byte getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Byte isDisplay) {
		this.isDisplay = isDisplay;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Boolean getInvalid() {
		return invalid;
	}

	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}
	
	public void isInvalid(Boolean invalid) {
		this.invalid = invalid;
	}
    
}