package com.admin.project.co.statistic.domain;

import com.admin.project.co.carlog.domain.CarLog;

public class SearchVo extends CarLog{
	Long[] cars;
	String startTime;
	String endTime;
	public Long[] getCars() {
		return cars;
	}
	public void setCars(Long[] cars) {
		this.cars = cars;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
