package com.admin.project.co.card.controller;

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
import com.admin.project.co.card.domain.Card;
import com.admin.project.co.card.service.ICardService;
import com.admin.project.system.user.domain.User;
import com.admin.project.system.user.service.IUserService;
import com.admin.framework.web.controller.BaseController;
import com.admin.framework.web.domain.AjaxResult;
import com.admin.common.utils.poi.ExcelUtil;
import com.admin.framework.web.page.TableDataInfo;

/**
 * 油卡Controller
 * 
 * @author author
 * @date 2020-04-01
 */
@Controller
@RequestMapping("/co/card")
public class CardController extends BaseController
{
    private String prefix = "co/card";

    @Autowired
    private ICardService cardService;
    
    @Autowired
    private IUserService userSrv;

    @RequiresPermissions("co:card:view")
    @GetMapping()
    public String card()
    {
        return prefix + "/card";
    }

    /**
     * 查询油卡列表
     */
    @RequiresPermissions("co:card:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Card card)
    {
        startPage();
        if(getSysUser().getRoleId() == 103l) {
        	card.setUser(getSysUser());
        }
        List<Card> list = cardService.selectCardList(card);
        return getDataTable(list);
    }

    /**
     * 导出油卡列表
     */
    @RequiresPermissions("co:card:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Card card)
    {
        List<Card> list = cardService.selectCardList(card);
        ExcelUtil<Card> util = new ExcelUtil<Card>(Card.class);
        return util.exportExcel(list, "card");
    }

    /**
     * 新增油卡
     */
    @GetMapping("/add")
    public String add(ModelMap mmp)
    {
    	List<User> users = userSrv.selectUserListByRoleId(103l);
    	mmp.put("users", users);
        return prefix + "/add";
    }

    /**
     * 新增保存油卡
     */
    @RequiresPermissions("co:card:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Card card)
    {
        return toAjax(cardService.insertCard(card));
    }

    /**
     * 修改油卡
     */
    @GetMapping("/edit/{cardId}")
    public String edit(@PathVariable("cardId") Long cardId, ModelMap mmap)
    {
        Card card = cardService.selectCardById(cardId);
        mmap.put("card", card);
        return prefix + "/edit";
    }

    /**
     * 修改保存油卡
     */
    @RequiresPermissions("co:card:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Card card)
    {
        return toAjax(cardService.updateCard(card));
    }

    /**
     * 删除油卡
     */
    @RequiresPermissions("co:card:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cardService.deleteCardByIds(ids));
    }
}
