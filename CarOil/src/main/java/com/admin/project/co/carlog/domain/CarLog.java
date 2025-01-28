package com.admin.project.co.carlog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.admin.framework.aspectj.lang.annotation.Excel;
import com.admin.framework.web.domain.BaseEntity;
import com.admin.project.co.car.domain.Car;
import com.admin.project.co.card.domain.Card;

/**
 * 加油记录对象 co_car_log
 * 
 * @author author
 * @date 2020-04-01
 */
public class CarLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long carLogId;

    /** 业务单号 */
    @Excel(name = "业务单号")
    private Long no;

    /** 车辆 */
    @Excel(name = "车辆")
    private Long carId;

    /** 油卡 */
    @Excel(name = "油卡")
    private Long cardId;

    /** 加油站 */
    @Excel(name = "加油站")
    private String oilStation;

    /** 燃油类型 */
    @Excel(name = "燃油类型")
    private String oilType;

    /** 每升单价 */
    @Excel(name = "每升单价")
    private Double perPrice;

    /** 加油量 */
    @Excel(name = "加油量")
    private Double sum;

    /** 加油金额 */
    @Excel(name = "加油金额")
    private Double cost;
    
    
    private Car car;
    private Card card;
    
    private Long[] cars;
    
    

    public Long[] getCars() {
		return cars;
	}

	public void setCars(Long[] cars) {
		this.cars = cars;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setCarLogId(Long carLogId) 
    {
        this.carLogId = carLogId;
    }

    public Long getCarLogId() 
    {
        return carLogId;
    }
    public void setNo(Long no) 
    {
        this.no = no;
    }

    public Long getNo() 
    {
        return no;
    }
    public void setCarId(Long carId) 
    {
        this.carId = carId;
    }

    public Long getCarId() 
    {
        return carId;
    }
    public void setCardId(Long cardId) 
    {
        this.cardId = cardId;
    }

    public Long getCardId() 
    {
        return cardId;
    }
    public void setOilStation(String oilStation) 
    {
        this.oilStation = oilStation;
    }

    public String getOilStation() 
    {
        return oilStation;
    }
    public void setOilType(String oilType) 
    {
        this.oilType = oilType;
    }

    public String getOilType() 
    {
        return oilType;
    }
    public void setPerPrice(Double perPrice) 
    {
        this.perPrice = perPrice;
    }

    public Double getPerPrice() 
    {
        return perPrice;
    }
    public void setSum(Double sum) 
    {
        this.sum = sum;
    }

    public Double getSum() 
    {
        return sum;
    }
    public void setCost(Double cost) 
    {
        this.cost = cost;
    }

    public Double getCost() 
    {
        return cost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("carLogId", getCarLogId())
            .append("no", getNo())
            .append("carId", getCarId())
            .append("cardId", getCardId())
            .append("oilStation", getOilStation())
            .append("oilType", getOilType())
            .append("perPrice", getPerPrice())
            .append("sum", getSum())
            .append("cost", getCost())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
