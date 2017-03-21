package com.ibaixiong.manage.util.ding.entity;

public class Department {

	public String id;
	public String name;
	public String parentid;
	
	@Override
	public String toString() {
		return "Department[id:" + id + ", name:" + name + ", parentId:" + parentid + "]";
	}
}
