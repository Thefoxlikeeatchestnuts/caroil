package com.admin.project.system.user.domain;

public class RegUser extends User {
	private String idNum;
	
	private Integer type;

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
}
