package com.admin.project.co.cardlog.controller;

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

import com.admin.common.utils.poi.ExcelUtil;
import com.admin.framework.web.controller.BaseController;
import com.admin.framework.web.domain.AjaxResult;
import com.admin.framework.web.page.TableDataInfo;
import com.admin.project.co.car.domain.Car;
import com.admin.project.co.car.service.ICarService;
import com.admin.project.co.card.domain.Card;
import com.admin.project.co.card.service.ICardService;
import com.admin.project.co.cardlog.domain.CardLog;
import com.admin.project.co.cardlog.service.ICardLogService;

/**
 * 充值记录Controller
 * 
 * @author author
 * @date 2020-04-01
 */
@Controller
@RequestMapping("/co/cardlog")
public class CardLogController extends BaseController
{
    private String prefix = "co/cardlog";

    @Autowired
    private ICardLogService cardLogService;
    @Autowired
    private ICardService cardSrv;
    
    

    @RequiresPermissions("co:cardlog:view")
    @GetMapping()
    public String cardlog()
    {
        return prefix + "/cardlog";
    }

    /**
     * 查询充值记录列表
     */
    @RequiresPermissions("co:cardlog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CardLog cardLog)
    {
        startPage();
        if(getSysUser().getRoleId() == 103l) {
        	Card card = new Card();
        	card.setUser(getSysUser());
        	cardLog.setCard(card);
        }
        List<CardLog> list = cardLogService.selectCardLogList(cardLog);
        return getDataTable(list);
    }

    /**
     * 导出充值记录列表
     */
    @RequiresPermissions("co:cardlog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CardLog cardLog)
    {
        List<CardLog> list = cardLogService.selectCardLogList(cardLog);
        ExcelUtil<CardLog> util = new ExcelUtil<CardLog>(CardLog.class);
        return util.exportExcel(list, "cardlog");
    }

    /**
     * 新增充值记录
     */
    @GetMapping("/add")
    public String add(ModelMap mmp)
    {
    	List<Card> cards = cardSrv.selectCardList(new Card());
    	mmp.put("cards", cards);
        return prefix + "/add";
    }

    /**
     * 新增保存充值记录
     */
    @RequiresPermissions("co:cardlog:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CardLog cardLog)
    {
        return toAjax(cardLogService.insertCardLog(cardLog));
    }

    /**
     * 修改充值记录
     */
    @GetMapping("/edit/{cardLogId}")
    public String edit(@PathVariable("cardLogId") Long cardLogId, ModelMap mmap)
    {
        CardLog cardLog = cardLogService.selectCardLogById(cardLogId);
        mmap.put("cardLog", cardLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存充值记录
     */
    @RequiresPermissions("co:cardlog:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CardLog cardLog)
    {
        return toAjax(cardLogService.updateCardLog(cardLog));
    }

    /**
     * 删除充值记录
     */
    @RequiresPermissions("co:cardlog:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cardLogService.deleteCardLogByIds(ids));
    }
}
