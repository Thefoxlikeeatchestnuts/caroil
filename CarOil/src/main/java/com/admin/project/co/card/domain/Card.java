package com.admin.project.co.card.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.admin.framework.aspectj.lang.annotation.Excel;
import com.admin.framework.web.domain.BaseEntity;
import com.admin.project.system.user.domain.User;

/**
 * 油卡对象 co_card
 * 
 * @author author
 * @date 2020-04-01
 */
public class Card extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long cardId;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long userId;

    /** 余额 */
    @Excel(name = "余额")
    private Double money;

    private String num;
    
    public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setCardId(Long cardId) 
    {
        this.cardId = cardId;
    }

    public Long getCardId() 
    {
        return cardId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
  public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
private User user;
    
    

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cardId", getCardId())
            .append("userId", getUserId())
            .append("money", getMoney())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
