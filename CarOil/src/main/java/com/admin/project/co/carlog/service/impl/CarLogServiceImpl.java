package com.admin.project.co.carlog.service.impl;

import java.util.List;

import com.admin.common.exception.BusinessException;
import com.admin.common.utils.DateUtils;
import com.admin.common.utils.security.ShiroUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.project.co.carlog.mapper.CarLogMapper;
import com.admin.project.co.card.domain.Card;
import com.admin.project.co.card.service.ICardService;
import com.admin.project.co.carlog.domain.CarLog;
import com.admin.project.co.carlog.service.ICarLogService;
import com.admin.common.utils.text.Convert;

/**
 * 加油记录Service业务层处理
 * 
 * @author author
 * @date 2020-04-01
 */
@Service
public class CarLogServiceImpl implements ICarLogService 
{
    @Autowired
    private CarLogMapper carLogMapper;
    @Autowired
    private ICardService cardSrv;

    /**
     * 查询加油记录
     * 
     * @param carLogId 加油记录ID
     * @return 加油记录
     */
    @Override
    public CarLog selectCarLogById(Long carLogId)
    {
        return carLogMapper.selectCarLogById(carLogId);
    }

    /**
     * 查询加油记录列表
     * 
     * @param carLog 加油记录
     * @return 加油记录
     */
    @Override
    public List<CarLog> selectCarLogList(CarLog carLog)
    {
        return carLogMapper.selectCarLogList(carLog);
    }

    /**
     * 新增加油记录
     * 
     * @param carLog 加油记录
     * @return 结果
     */
    @Override
    public int insertCarLog(CarLog carLog)
    {
    	Card card = cardSrv.selectCardById(carLog.getCardId());
    	Double pre = card.getMoney();
    	Double cost = carLog.getCost();
    	Double cur = pre-cost;
    	if(cur <0) {
    		throw new BusinessException("余额不足");
    	}
    	card.setMoney(cur);
    	cardSrv.updateCard(card);
    	carLog.setNo(System.currentTimeMillis());
        carLog.setCreateTime(DateUtils.getNowDate());
        carLog.setCreateBy(ShiroUtils.getLoginName());
        return carLogMapper.insertCarLog(carLog);
    }

    /**
     * 修改加油记录
     * 
     * @param carLog 加油记录
     * @return 结果
     */
    @Override
    public int updateCarLog(CarLog carLog)
    {
        return carLogMapper.updateCarLog(carLog);
    }

    /**
     * 删除加油记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarLogByIds(String ids)
    {
        return carLogMapper.deleteCarLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除加油记录信息
     * 
     * @param carLogId 加油记录ID
     * @return 结果
     */
    @Override
    public int deleteCarLogById(Long carLogId)
    {
        return carLogMapper.deleteCarLogById(carLogId);
    }
}
