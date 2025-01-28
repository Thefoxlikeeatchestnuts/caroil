package com.admin.project.co.car.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.admin.framework.aspectj.lang.annotation.Excel;
import com.admin.framework.web.domain.BaseEntity;
import com.admin.project.system.user.domain.User;

/**
 * 车辆信息对象 co_car
 * 
 * @author author
 * @date 2020-04-01
 */
public class Car extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long carId;

    /** 车主 */
    @Excel(name = "车主")
    private Long userId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String num;

    /** 所属车队 */
    @Excel(name = "所属车队")
    private String group;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 核定载重 */
    @Excel(name = "核定载重")
    private String load;

    /** 油耗 */
    @Excel(name = "油耗")
    private String oilWea;

    /** 里程 */
    @Excel(name = "里程")
    private String haveRun;

    /** 发动机号 */
    @Excel(name = "发动机号")
    private String driverNo;

    /** 保险号 */
    @Excel(name = "保险号")
    private String insNum;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frameNum;

    /** 底牌号 */
    @Excel(name = "底牌号")
    private String chassisNum;
    
    private User user;
    
    

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCarId(Long carId) 
    {
        this.carId = carId;
    }

    public Long getCarId() 
    {
        return carId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setNum(String num) 
    {
        this.num = num;
    }

    public String getNum() 
    {
        return num;
    }
    public void setGroup(String group) 
    {
        this.group = group;
    }

    public String getGroup() 
    {
        return group;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setLoad(String load) 
    {
        this.load = load;
    }

    public String getLoad() 
    {
        return load;
    }
    public void setOilWea(String oilWea) 
    {
        this.oilWea = oilWea;
    }

    public String getOilWea() 
    {
        return oilWea;
    }
    public void setHaveRun(String haveRun) 
    {
        this.haveRun = haveRun;
    }

    public String getHaveRun() 
    {
        return haveRun;
    }
    public void setDriverNo(String driverNo) 
    {
        this.driverNo = driverNo;
    }

    public String getDriverNo() 
    {
        return driverNo;
    }
    public void setInsNum(String insNum) 
    {
        this.insNum = insNum;
    }

    public String getInsNum() 
    {
        return insNum;
    }
    public void setFrameNum(String frameNum) 
    {
        this.frameNum = frameNum;
    }

    public String getFrameNum() 
    {
        return frameNum;
    }
    public void setChassisNum(String chassisNum) 
    {
        this.chassisNum = chassisNum;
    }

    public String getChassisNum() 
    {
        return chassisNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("carId", getCarId())
            .append("userId", getUserId())
            .append("num", getNum())
            .append("group", getGroup())
            .append("brand", getBrand())
            .append("load", getLoad())
            .append("oilWea", getOilWea())
            .append("haveRun", getHaveRun())
            .append("driverNo", getDriverNo())
            .append("insNum", getInsNum())
            .append("frameNum", getFrameNum())
            .append("chassisNum", getChassisNum())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
