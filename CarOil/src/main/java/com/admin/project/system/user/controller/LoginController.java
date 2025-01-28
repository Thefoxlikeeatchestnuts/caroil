package com.admin.project.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.common.utils.ServletUtils;
import com.admin.common.utils.StringUtils;
import com.admin.framework.web.controller.BaseController;
import com.admin.framework.web.domain.AjaxResult;
import com.admin.project.system.user.domain.RegUser;
import com.admin.project.system.user.service.IUserService;

/**
 * 登录验证
 * 
 * 
 */
@Controller
public class LoginController extends BaseController
{
	
	@Autowired
	private IUserService userSrv;
	
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }
    
    @GetMapping("/reg")
	public String reg(ModelMap mmap) {
		return "reg";
	}
    
	@PostMapping("/reg")
	@ResponseBody
	public AjaxResult regsave(RegUser user) {
		return toAjax(userSrv.reg(user));
	}

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
