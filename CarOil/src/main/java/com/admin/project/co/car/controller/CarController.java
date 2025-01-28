package com.admin.project.co.car.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.admin.project.co.car.domain.Car;
import com.admin.project.co.car.service.ICarService;
import com.admin.project.system.user.domain.User;
import com.admin.project.system.user.service.IUserService;
import com.admin.framework.web.controller.BaseController;
import com.admin.framework.web.domain.AjaxResult;
import com.admin.common.utils.poi.ExcelUtil;
import com.admin.framework.web.page.TableDataInfo;

/**
 * 车辆信息Controller
 * 
 * @author author
 * @date 2020-04-01
 */
@Controller
@RequestMapping("/co/car")
public class CarController extends BaseController
{
    private String prefix = "co/car";

    @Autowired
    private ICarService carService;
    @Autowired
    private IUserService userSrv;

    @RequiresPermissions("co:car:view")
    @GetMapping()
    public String car()
    {
        return prefix + "/car";
    }

    /**
     * 查询车辆信息列表
     */
    @RequiresPermissions("co:car:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Car car)
    {
        startPage();
        if(getSysUser().getRoleId() == 103l) {
        	car.setUser(getSysUser());
        }
        List<Car> list = carService.selectCarList(car);
        return getDataTable(list);
    }

    /**
     * 导出车辆信息列表
     */
    @RequiresPermissions("co:car:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Car car)
    {
        List<Car> list = carService.selectCarList(car);
        ExcelUtil<Car> util = new ExcelUtil<Car>(Car.class);
        return util.exportExcel(list, "car");
    }

    /**
     * 新增车辆信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmp)
    {
    	List<User> users = userSrv.selectUserListByRoleId(103l);
    	mmp.put("users", users);
        return prefix + "/add";
    }

    /**
     * 新增保存车辆信息
     */
    @RequiresPermissions("co:car:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Car car)
    {
        return toAjax(carService.insertCar(car));
    }

    /**
     * 修改车辆信息
     */
    @GetMapping("/edit/{carId}")
    public String edit(@PathVariable("carId") Long carId, ModelMap mmap)
    {
        Car car = carService.selectCarById(carId);
        mmap.put("car", car);
        return prefix + "/edit";
    }

    /**
     * 修改保存车辆信息
     */
    @RequiresPermissions("co:car:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Car car)
    {
        return toAjax(carService.updateCar(car));
    }

    /**
     * 删除车辆信息
     */
    @RequiresPermissions("co:car:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(carService.deleteCarByIds(ids));
    }
}
