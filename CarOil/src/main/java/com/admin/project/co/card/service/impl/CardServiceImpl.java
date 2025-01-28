package com.admin.project.co.card.service.impl;

import java.util.List;
import com.admin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.project.co.card.mapper.CardMapper;
import com.admin.project.co.card.domain.Card;
import com.admin.project.co.card.service.ICardService;
import com.admin.common.utils.text.Convert;

/**
 * 油卡Service业务层处理
 * 
 * @author author
 * @date 2020-04-01
 */
@Service
public class CardServiceImpl implements ICardService 
{
    @Autowired
    private CardMapper cardMapper;

    /**
     * 查询油卡
     * 
     * @param cardId 油卡ID
     * @return 油卡
     */
    @Override
    public Card selectCardById(Long cardId)
    {
        return cardMapper.selectCardById(cardId);
    }

    /**
     * 查询油卡列表
     * 
     * @param card 油卡
     * @return 油卡
     */
    @Override
    public List<Card> selectCardList(Card card)
    {
        return cardMapper.selectCardList(card);
    }

    /**
     * 新增油卡
     * 
     * @param card 油卡
     * @return 结果
     */
    @Override
    public int insertCard(Card card)
    {
    	card.setNum("S"+System.currentTimeMillis());
    	card.setMoney(0.0);
        card.setCreateTime(DateUtils.getNowDate());
        return cardMapper.insertCard(card);
    }

    /**
     * 修改油卡
     * 
     * @param card 油卡
     * @return 结果
     */
    @Override
    public int updateCard(Card card)
    {
        return cardMapper.updateCard(card);
    }

    /**
     * 删除油卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCardByIds(String ids)
    {
        return cardMapper.deleteCardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除油卡信息
     * 
     * @param cardId 油卡ID
     * @return 结果
     */
    @Override
    public int deleteCardById(Long cardId)
    {
        return cardMapper.deleteCardById(cardId);
    }
}
