package com.admin.project.co.car.service;

import com.admin.project.co.car.domain.Car;
import java.util.List;

/**
 * 车辆信息Service接口
 * 
 * @author author
 * @date 2020-04-01
 */
public interface ICarService 
{
    /**
     * 查询车辆信息
     * 
     * @param carId 车辆信息ID
     * @return 车辆信息
     */
    public Car selectCarById(Long carId);

    /**
     * 查询车辆信息列表
     * 
     * @param car 车辆信息
     * @return 车辆信息集合
     */
    public List<Car> selectCarList(Car car);

    /**
     * 新增车辆信息
     * 
     * @param car 车辆信息
     * @return 结果
     */
    public int insertCar(Car car);

    /**
     * 修改车辆信息
     * 
     * @param car 车辆信息
     * @return 结果
     */
    public int updateCar(Car car);

    /**
     * 批量删除车辆信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarByIds(String ids);

    /**
     * 删除车辆信息信息
     * 
     * @param carId 车辆信息ID
     * @return 结果
     */
    public int deleteCarById(Long carId);
}
