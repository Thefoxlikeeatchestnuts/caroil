package com.admin.project.co.card.mapper;

import com.admin.project.co.card.domain.Card;
import java.util.List;

/**
 * 油卡Mapper接口
 * 
 * @author author
 * @date 2020-04-01
 */
public interface CardMapper 
{
    /**
     * 查询油卡
     * 
     * @param cardId 油卡ID
     * @return 油卡
     */
    public Card selectCardById(Long cardId);

    /**
     * 查询油卡列表
     * 
     * @param card 油卡
     * @return 油卡集合
     */
    public List<Card> selectCardList(Card card);

    /**
     * 新增油卡
     * 
     * @param card 油卡
     * @return 结果
     */
    public int insertCard(Card card);

    /**
     * 修改油卡
     * 
     * @param card 油卡
     * @return 结果
     */
    public int updateCard(Card card);

    /**
     * 删除油卡
     * 
     * @param cardId 油卡ID
     * @return 结果
     */
    public int deleteCardById(Long cardId);

    /**
     * 批量删除油卡
     * 
     * @param cardIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCardByIds(String[] cardIds);
}
