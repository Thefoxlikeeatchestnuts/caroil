package com.admin.project.co.carlog.service;

import com.admin.project.co.carlog.domain.CarLog;
import java.util.List;

/**
 * 加油记录Service接口
 * 
 * @author author
 * @date 2020-04-01
 */
public interface ICarLogService 
{
    /**
     * 查询加油记录
     * 
     * @param carLogId 加油记录ID
     * @return 加油记录
     */
    public CarLog selectCarLogById(Long carLogId);

    /**
     * 查询加油记录列表
     * 
     * @param carLog 加油记录
     * @return 加油记录集合
     */
    public List<CarLog> selectCarLogList(CarLog carLog);

    /**
     * 新增加油记录
     * 
     * @param carLog 加油记录
     * @return 结果
     */
    public int insertCarLog(CarLog carLog);

    /**
     * 修改加油记录
     * 
     * @param carLog 加油记录
     * @return 结果
     */
    public int updateCarLog(CarLog carLog);

    /**
     * 批量删除加油记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarLogByIds(String ids);

    /**
     * 删除加油记录信息
     * 
     * @param carLogId 加油记录ID
     * @return 结果
     */
    public int deleteCarLogById(Long carLogId);
}
