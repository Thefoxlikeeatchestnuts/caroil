package com.admin.project.co.cardlog.mapper;

import com.admin.project.co.cardlog.domain.CardLog;
import java.util.List;

/**
 * 充值记录Mapper接口
 * 
 * @author author
 * @date 2020-04-01
 */
public interface CardLogMapper 
{
    /**
     * 查询充值记录
     * 
     * @param cardLogId 充值记录ID
     * @return 充值记录
     */
    public CardLog selectCardLogById(Long cardLogId);

    /**
     * 查询充值记录列表
     * 
     * @param cardLog 充值记录
     * @return 充值记录集合
     */
    public List<CardLog> selectCardLogList(CardLog cardLog);

    /**
     * 新增充值记录
     * 
     * @param cardLog 充值记录
     * @return 结果
     */
    public int insertCardLog(CardLog cardLog);

    /**
     * 修改充值记录
     * 
     * @param cardLog 充值记录
     * @return 结果
     */
    public int updateCardLog(CardLog cardLog);

    /**
     * 删除充值记录
     * 
     * @param cardLogId 充值记录ID
     * @return 结果
     */
    public int deleteCardLogById(Long cardLogId);

    /**
     * 批量删除充值记录
     * 
     * @param cardLogIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCardLogByIds(String[] cardLogIds);
}
