package com.df4j.module.admin.sesurity.shiro;

import com.df4j.base.constants.ErrorCode;
import com.df4j.base.exception.BusinessException;
import com.df4j.module.admin.properties.DfAdminProperties;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(10)
public class ShiroAspect {

    private Logger logger = LoggerFactory.getLogger(ShiroAspect.class);

    @Autowired
    private DfAdminProperties dfAdminProperties;

    @Pointcut("execution(public * com..module.*.controller.*.*(..))")
    public void doShiro(){
    }

    @Before("doShiro()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 只校验Post调用
        String method = request.getMethod();
        if(!"post".equals(method.toLowerCase())){
            logger.debug("非post请求，允许,method:{},url:{}",request.getMethod(), request.getRequestURI());
            return;
        }
        String requestRUI = request.getRequestURI();
        String urlCode = requestRUI.replaceAll("/api/","");
        //白名单
        if(dfAdminProperties.getSecurity().getExcludes().contains(urlCode)){
            return;
        }
        //校验登录
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            throw BusinessException.build(ErrorCode.UNLOGIN, "未登录");
        }
        //对于所有管理员组用户，不校验权限
        if(currentUser.hasRole("admin")){
            return;
        }
        //校验权限
        currentUser.checkPermission(urlCode);
    }
}
