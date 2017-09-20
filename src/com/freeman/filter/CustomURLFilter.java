package com.freeman.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.freeman.utilities.SEOUtil;


public class CustomURLFilter implements Filter {

	protected static Logger logger = Logger.getLogger(CustomURLFilter.class);
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (!SEOUtil.isRealActionRequest(req)){
			chain.doFilter(req, resp);
			return;
		}

		if (SEOUtil.isOtherRelatedApplication(req)){
			chain.doFilter(req, resp);
			return;			
		}
		//check url contains language -- "en" or "fr"
		if (!SEOUtil.isURLConstaintLanguage(req)){
			String newURL = SEOUtil.getURLWithLanguage(req);
			if ( newURL != null && !newURL.isEmpty()){
				resp.sendRedirect(newURL);
				return;
			}
		}
		
		//check queryString contains language parameter -- "en" or "fr"
		if (SEOUtil.isQueryStringConstaintLanguageParameter(req)){
			String newURL = SEOUtil.getURLWithoutLanguageParameter(req);
			if ( newURL != null && !newURL.isEmpty()){
				resp.sendRedirect(newURL);
				return;
			}
		}

		chain.doFilter(req, resp);
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
