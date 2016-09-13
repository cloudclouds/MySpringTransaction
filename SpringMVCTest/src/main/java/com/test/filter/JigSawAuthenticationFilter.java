package com.test.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.util.AbstractConfigurationFilter;

import com.test.utils.Constants;

public class JigSawAuthenticationFilter extends AbstractConfigurationFilter{

	private AuthenticationFilter filter = new AuthenticationFilter();
	
	protected final Log logger = LogFactory.getLog(getClass());
	private boolean mockCAS = false;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("init JigSawAuthenticationFilter ");
		String mock_cas = getPropertyFromInitParams(filterConfig, Constants.MOCK_CAS, null);		
		mockCAS = BooleanUtils.toBoolean(mock_cas);
		logger.info("init JigSawAuthenticationFilter found this env mock_cas value is " + mockCAS);
		if(!mockCAS){
			filter.init(filterConfig); 
		}
		/*String mock_cas_user = getPropertyFromInitParams(filterConfig, Constants.MOCK_CAS_USER, null);
		if(mock_cas_user!=null&&mock_cas_user.startsWith("${")){
			String sn = getPropertyFromInitParams(filterConfig, "serverName", null);
			if(sn!=null&&sn.startsWith("${")){
				filter.setServerName("http://localhost:8080");
			}
			
		}*/
	}



	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
//		final HttpServletRequest request = (HttpServletRequest) servletRequest;
//	    final HttpServletResponse response = (HttpServletResponse) servletResponse; 
//		String mock_cas = request.getSession().getServletContext().getInitParameter("mock_cas");
//	    boolean mock = BooleanUtils.toBoolean(mock_cas);
	    if(!mockCAS){
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpSession session = request.getSession(false);
			if (session == null && request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equals("XMLHttpRequest") ) {
				response.setHeader("sessionstatus", "timeout");
			}else{
//				logger.info("JigSawAuthenticationFilter found this env is use AuthenticationFilter");
				filter.doFilter(servletRequest, servletResponse, chain);
			}

	     }else{
	    	 logger.info("JigSawAuthenticationFilter found this env is mock_cas");
	    	 chain.doFilter(servletRequest, servletResponse);
	     }
	    
	}



	@Override
	public void destroy() {
		filter.destroy();
	}

}