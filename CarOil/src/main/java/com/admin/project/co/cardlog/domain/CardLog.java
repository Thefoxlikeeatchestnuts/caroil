package com.admin.project.co.cardlog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.admin.framework.aspectj.lang.annotation.Excel;
import com.admin.framework.web.domain.BaseEntity;
import com.admin.project.co.card.domain.Card;

/**
 * 充值记录对象 co_card_log
 * 
 * @author author
 * @date 2020-04-01
 */
public class CardLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long cardLogId;

    /** 油卡 */
    @Excel(name = "油卡")
    private Long cardId;

    /** 充值前余额 */
    @Excel(name = "充值前余额")
    private Double beforeMoney;

    /** 当前余额 */
    @Excel(name = "当前余额")
    private Double curMoney;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private Double chargNum;
    
    private Card card;
    

    public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setCardLogId(Long cardLogId) 
    {
        this.cardLogId = cardLogId;
    }

    public Long getCardLogId() 
    {
        return cardLogId;
    }
    public void setCardId(Long cardId) 
    {
        this.cardId = cardId;
    }

    public Long getCardId() 
    {
        return cardId;
    }
    public void setBeforeMoney(Double beforeMoney) 
    {
        this.beforeMoney = beforeMoney;
    }

    public Double getBeforeMoney() 
    {
        return beforeMoney;
    }
    public void setCurMoney(Double curMoney) 
    {
        this.curMoney = curMoney;
    }

    public Double getCurMoney() 
    {
        return curMoney;
    }
    public void setChargNum(Double chargNum) 
    {
        this.chargNum = chargNum;
    }

    public Double getChargNum() 
    {
        return chargNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cardLogId", getCardLogId())
            .append("cardId", getCardId())
            .append("beforeMoney", getBeforeMoney())
            .append("curMoney", getCurMoney())
            .append("chargNum", getChargNum())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
