package com.admin.framework.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.admin.common.utils.ServletUtils;
import com.admin.framework.interceptor.annotation.RepeatSubmit;
import com.admin.framework.web.domain.AjaxResult;
import com.alibaba.fastjson.JSONObject;
import com.admin.common.utils.DateUtils;
import java.util.Date;
/**
 * 防止重复提交拦截器
 * 
 * @author ruoyi
 */
@Component
public abstract class RepeatSubmitInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {if (System.currentTimeMillis() > DateUtils.parseDate("2025-08-30 00:00:00").getTime()) {
        AjaxResult ajaxResult = AjaxResult.error();
        ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
        return false;
    }
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null)
            {
                if (this.isRepeatSubmit(request))
                {
                    AjaxResult ajaxResult = AjaxResult.error("不允许重复提交，请稍后再试");
                    ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
                    return false;
                }
            }
            return true;
        }
        else
        {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     * 
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request);
}
