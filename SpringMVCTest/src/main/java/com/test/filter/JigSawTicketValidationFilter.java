package com.test.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.jasig.cas.client.util.AbstractConfigurationFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;

import com.test.utils.Constants;

public class JigSawTicketValidationFilter extends AbstractConfigurationFilter {

	private final Cas20ProxyReceivingTicketValidationFilter filter = new Cas20ProxyReceivingTicketValidationFilter();
	private boolean mockCAS = false;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init JigSawTicketValidationFilter ");
		String mock_cas = getPropertyFromInitParams(filterConfig, Constants.MOCK_CAS, null);
		mockCAS = BooleanUtils.toBoolean(mock_cas);
		if(!mockCAS){
			log.info("init Cas20ProxyReceivingTicketValidationFilter");
			filter.init(filterConfig);
		}else{
			log.info("init JigSawTicketValidationFilter found this env is mock_cas login");
		}
		
		if(mock_cas!=null&&mock_cas.startsWith("${")){
			String sn = getPropertyFromInitParams(filterConfig, "serverName", null);
			if(sn!=null&&sn.startsWith("${")){
				filter.setServerName("http://localhost:8080");
//				filter.setServerName("http://10.1.241.27:8081");
			}
			
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(mockCAS){
			log.info("this is mock_cas login filter");
			chain.doFilter(request, response);
		}else{
			log.info("execute JigSawTicketValidationFilter chain ");
			filter.doFilter(request, response, chain);
		}
	}

	@Override
	public void destroy() {
		filter.destroy();
	}

}
