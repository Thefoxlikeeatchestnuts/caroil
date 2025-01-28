package com.admin.project.co.carlog.controller;

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
import com.admin.project.co.card.domain.Card;
import com.admin.project.co.card.service.ICardService;
import com.admin.project.co.carlog.domain.CarLog;
import com.admin.project.co.carlog.service.ICarLogService;
import com.admin.framework.web.controller.BaseController;
import com.admin.framework.web.domain.AjaxResult;
import com.admin.common.utils.poi.ExcelUtil;
import com.admin.framework.web.page.TableDataInfo;

/**
 * 加油记录Controller
 * 
 * @author author
 * @date 2020-04-01
 */
@Controller
@RequestMapping("/co/carlog")
public class CarLogController extends BaseController
{
    private String prefix = "co/carlog";

    @Autowired
    private ICarLogService carLogService;
    
    @Autowired
    private ICardService cardSrv;
    @Autowired
    private ICarService carSrv;

    @RequiresPermissions("co:carlog:view")
    @GetMapping()
    public String carlog()
    {
        return prefix + "/carlog";
    }

    /**
     * 查询加油记录列表
     */
    @RequiresPermissions("co:carlog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CarLog carLog)
    {
        startPage();
        if(getSysUser().getRoleId() == 103l) {
        	Car car = new Car();
        	car.setUser(getSysUser());
        	carLog.setCar(car);
        }
        List<CarLog> list = carLogService.selectCarLogList(carLog);
        return getDataTable(list);
    }

    /**
     * 导出加油记录列表
     */
    @RequiresPermissions("co:carlog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CarLog carLog)
    {
        List<CarLog> list = carLogService.selectCarLogList(carLog);
        ExcelUtil<CarLog> util = new ExcelUtil<CarLog>(CarLog.class);
        return util.exportExcel(list, "carlog");
    }

    /**
     * 新增加油记录
     */
    @GetMapping("/add")
    public String add(ModelMap mmp)
    {
    	List<Card> cards = cardSrv.selectCardList(new Card());
    	mmp.put("cards", cards);
    	List<Car> cars = carSrv.selectCarList(new Car());
    	mmp.put("cars", cars);
        return prefix + "/add";
    }

    /**
     * 新增保存加油记录
     */
    @RequiresPermissions("co:carlog:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CarLog carLog)
    {
        return toAjax(carLogService.insertCarLog(carLog));
    }

    /**
     * 修改加油记录
     */
    @GetMapping("/edit/{carLogId}")
    public String edit(@PathVariable("carLogId") Long carLogId, ModelMap mmap)
    {
        CarLog carLog = carLogService.selectCarLogById(carLogId);
        mmap.put("carLog", carLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存加油记录
     */
    @RequiresPermissions("co:carlog:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CarLog carLog)
    {
        return toAjax(carLogService.updateCarLog(carLog));
    }

    /**
     * 删除加油记录
     */
    @RequiresPermissions("co:carlog:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(carLogService.deleteCarLogByIds(ids));
    }
}
