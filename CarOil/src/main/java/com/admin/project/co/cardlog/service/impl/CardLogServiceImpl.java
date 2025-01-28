package com.admin.project.co.cardlog.service.impl;

import java.util.List;
import com.admin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admin.project.co.cardlog.mapper.CardLogMapper;
import com.admin.project.co.card.domain.Card;
import com.admin.project.co.card.service.ICardService;
import com.admin.project.co.cardlog.domain.CardLog;
import com.admin.project.co.cardlog.service.ICardLogService;
import com.admin.common.utils.text.Convert;

/**
 * 充值记录Service业务层处理
 * 
 * @author author
 * @date 2020-04-01
 */
@Service
public class CardLogServiceImpl implements ICardLogService 
{
    @Autowired
    private CardLogMapper cardLogMapper;
    @Autowired
    private ICardService cardSrv;

    /**
     * 查询充值记录
     * 
     * @param cardLogId 充值记录ID
     * @return 充值记录
     */
    @Override
    public CardLog selectCardLogById(Long cardLogId)
    {
        return cardLogMapper.selectCardLogById(cardLogId);
    }

    /**
     * 查询充值记录列表
     * 
     * @param cardLog 充值记录
     * @return 充值记录
     */
    @Override
    public List<CardLog> selectCardLogList(CardLog cardLog)
    {
        return cardLogMapper.selectCardLogList(cardLog);
    }

    /**
     * 新增充值记录
     * 
     * @param cardLog 充值记录
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCardLog(CardLog cardLog)
    {
    	Card card = cardSrv.selectCardById(cardLog.getCardId());
    	Double pre = card.getMoney();
    	Double chargNum = cardLog.getChargNum();
    	Double cur = pre+chargNum;
    	card.setMoney(cur);
    	cardSrv.updateCard(card);
    	cardLog.setBeforeMoney(pre);
    	cardLog.setCurMoney(cur);
        cardLog.setCreateTime(DateUtils.getNowDate());
        return cardLogMapper.insertCardLog(cardLog);
    }

    /**
     * 修改充值记录
     * 
     * @param cardLog 充值记录
     * @return 结果
     */
    @Override
    public int updateCardLog(CardLog cardLog)
    {
        return cardLogMapper.updateCardLog(cardLog);
    }

    /**
     * 删除充值记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCardLogByIds(String ids)
    {
        return cardLogMapper.deleteCardLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除充值记录信息
     * 
     * @param cardLogId 充值记录ID
     * @return 结果
     */
    @Override
    public int deleteCardLogById(Long cardLogId)
    {
        return cardLogMapper.deleteCardLogById(cardLogId);
    }
}
