package com.admin.project.co.car.service.impl;

import java.util.List;
import com.admin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.project.co.car.mapper.CarMapper;
import com.admin.project.co.car.domain.Car;
import com.admin.project.co.car.service.ICarService;
import com.admin.common.utils.text.Convert;

/**
 * 车辆信息Service业务层处理
 * 
 * @author author
 * @date 2020-04-01
 */
@Service
public class CarServiceImpl implements ICarService 
{
    @Autowired
    private CarMapper carMapper;

    /**
     * 查询车辆信息
     * 
     * @param carId 车辆信息ID
     * @return 车辆信息
     */
    @Override
    public Car selectCarById(Long carId)
    {
        return carMapper.selectCarById(carId);
    }

    /**
     * 查询车辆信息列表
     * 
     * @param car 车辆信息
     * @return 车辆信息
     */
    @Override
    public List<Car> selectCarList(Car car)
    {
        return carMapper.selectCarList(car);
    }

    /**
     * 新增车辆信息
     * 
     * @param car 车辆信息
     * @return 结果
     */
    @Override
    public int insertCar(Car car)
    {
        car.setCreateTime(DateUtils.getNowDate());
        return carMapper.insertCar(car);
    }

    /**
     * 修改车辆信息
     * 
     * @param car 车辆信息
     * @return 结果
     */
    @Override
    public int updateCar(Car car)
    {
        return carMapper.updateCar(car);
    }

    /**
     * 删除车辆信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarByIds(String ids)
    {
        return carMapper.deleteCarByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除车辆信息信息
     * 
     * @param carId 车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteCarById(Long carId)
    {
        return carMapper.deleteCarById(carId);
    }
}
