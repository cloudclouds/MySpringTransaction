package com.test.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.test.model.User;
import com.test.service.UserService;
import com.test.utils.Constants;

public class AutoSetUserAdapterFilter extends OncePerRequestFilter { 

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain)
            throws IOException, ServletException {

    	logger.info("enter auto filter.");

        String loginName = null;
        
        
        boolean isError = true;
        try {
            loginName = getSSOLoginName(request);
            if (StringUtils.isNotBlank(loginName)) {
                isError = false;
            }
        } catch (Exception e) {
            logger.warn("sso filter get user login name error");
        }
 
        if (isError) {
            request.getRequestDispatcher("/common/sso_redirect.jsp").forward(request, response);
        } else {
        	
        	logger.info("current login user is "+ loginName);
        	
        	loadUserInfo(request ,loginName);
            
            try {
            	chain.doFilter(request, response);
			} catch (Exception e) {
				logger.error("doFilter" , e);
//			}finally{
//				logger.info("do filter end .");
			}
        }

       // HttpSession session = request.getSession(false);

        //if (session != null) {
             
        //}

        //chain.doFilter(request, response);

         
    }
    
    
    
     
    
    public static String getSSOLoginName(HttpServletRequest httpRequest) {
    	//AssertionHolder.getAssertion().getPrincipal().getName();
        // _const_cas_assertion_是CAS中存放登录用户名的session标志
        Object object = httpRequest.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if (object != null) {
            // 转换对象
            Assertion assertion = (Assertion) object;
            // 登录名
            String loginName = assertion.getPrincipal().getName();
            return loginName;
        }
        String mock_cas = httpRequest.getSession().getServletContext().getInitParameter(Constants.MOCK_CAS);
        boolean mock = BooleanUtils.toBoolean(mock_cas);
        if(mock){
        	return httpRequest.getSession().getServletContext().getInitParameter(Constants.MOCK_CAS_USER);
        }
        return null;
    }
    
    
    public static void loadUserInfo(HttpServletRequest request ,String loginName){
    	ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        UserService userService = ctx.getBean(UserService.class); 
//    	UserService userService=new UserService();
        
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        if (user == null) {
            try {
            	user = userService.getByEmail(loginName);
            } catch (Exception e) { 
            }
            if(user == null){
            	user = new User();
            	//user.setUid(RequestUtil.getUidFromPassport(loginName));
            	user.setEmail(loginName);
            	user.setPhone("");
            	user.setType((byte)1);
            	user.setUserName(loginName);
            	user.setAdmin(0);
            	userService.create(user);
            }
            
        }
        if (user != null) {
        	request.getSession().setAttribute(Constants.USER_IN_SESSION, user);
        }
        
       // SysMenuService sysMenuService = ctx.getBean(SysMenuService.class); 
       // List<SysMenu> sysMenus = sysMenuService.queryByUid(user.getUid());        
       // request.getSession().setAttribute(Constants.MENU_IN_SESSION, sysMenus);

    }
}