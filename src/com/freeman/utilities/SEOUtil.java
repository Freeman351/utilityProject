package com.freeman.utilities;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.interceptor.I18nInterceptor;

public class SEOUtil {

    /**
     * String frenchCode = Locale.CANADA_FRENCH.getLanguage();
     * String englishCode = Locale.CANADA.getLanguage();
     * 
     * prerequisite: leading '/' has been removed
     * @param request
     * @param uri
     */
	public static final String URI_FROM_HTTP_SERVLET_REQUEST = "UriFromHttpServletRequest";
	public static final String LANGUAGE_PARAMETER_IN_REQUEST_URL = "lang=";
	public static final String REQ_LOCALE_PARAM_EN = "en";
	public static final String DEFAULT_ACTION_SUFFIX = ".action";
	public static final String DEFAULT_LOCALE = "locale";
	
	//Other application relate to inetCA such as: call-center, content management
	public static final ArrayList<String> otherRelatedApplicationNameSpaces = new ArrayList<String>
			(Arrays.asList("banners", "category", "commons", "content", "events", "isg", "linkBuilder", "news", "nonProduct",
					"onlineHelp", "product", "productCategory", "sync", "userMgmt", "uploadMgmt", "upload"));
	//Other resource suffix for not real application request
	public static final ArrayList<String> suffixForOtherResourceRequestURL = new ArrayList<String>
			(Arrays.asList("css", "js","jpeg", "jpg", "xml", "png", "gif", "php", "json", "htm", "bmp", "ws", "pdf"));

	
	public static void setLocaleFromUri(HttpServletRequest request, String lang) {
		Locale locale = Locale.CANADA;
		if(lang !=null && lang.equalsIgnoreCase(Locale.CANADA_FRENCH.getLanguage())){
			locale = Locale.CANADA_FRENCH;
		}
		request.getSession().setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, locale);
		request.getSession().setAttribute(DEFAULT_LOCALE, locale);		
	}

	public static String getCurrentLanguage(HttpServletRequest request){
		return getCurrentLocale(request).getLanguage();		
	}

	public static Locale getCurrentLocale(HttpServletRequest request){
		Locale locale = (Locale) (request.getSession().getAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE));
		if (null == locale) {
			locale = Locale.CANADA;
		}
		return locale;		
	}
	
	public static String  getURLWithLanguage(HttpServletRequest request){
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();   // /eStore
	    String pathInfo = request.getPathInfo();         // /a/b;c=123?
	    String queryString = request.getQueryString();          // store=employee
	    
	    StringBuffer url =  new StringBuffer();
	    String subUri = uri.substring(contextPath.length() + 1);
	    String languageSignInURL = getCurrentLanguage(request);
	    
	    if (queryString != null && queryString.contains(LANGUAGE_PARAMETER_IN_REQUEST_URL)){
	    	if (queryString.contains(LANGUAGE_PARAMETER_IN_REQUEST_URL + REQ_LOCALE_PARAM_EN)){
	    		languageSignInURL = Locale.CANADA.getLanguage();
	    	}else{
	    		languageSignInURL = Locale.CANADA_FRENCH.getLanguage();	    		
	    	}
		    queryString = getQueryStringWithoutLanguageParameter(request.getQueryString(), LANGUAGE_PARAMETER_IN_REQUEST_URL, "&");
	    }
	    
	    url.append(contextPath).append("/" + languageSignInURL);
	    if (subUri != null && subUri.trim().length() > 0){
	    	url.append("/").append(subUri.trim());
	    }
	    if (pathInfo != null && !pathInfo.trim().isEmpty()) {
	        url.append(pathInfo.trim());
	    }
	    if (queryString != null && !queryString.trim().isEmpty()) {
	        url.append("?").append(queryString);
	    }
	    return url.toString();
	}

	public static String  getURLWithoutLanguageParameter(HttpServletRequest request){
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();   // /eStore
	    String pathInfo = request.getPathInfo();         // /a/b;c=123?
	    
	    StringBuffer url =  new StringBuffer();
	    String subUri = uri.substring(contextPath.length() + 1);
	    
	    url.append(contextPath);
	    if (subUri != null && subUri.trim().length() > 0){
	    	url.append("/").append(subUri.trim());
	    }
	    if (pathInfo != null && !pathInfo.trim().isEmpty()) {
	        url.append(pathInfo.trim());
	    }
	    
	    String queryString = getQueryStringWithoutLanguageParameter(request.getQueryString(), LANGUAGE_PARAMETER_IN_REQUEST_URL, "&"); 	    
	    if (queryString != null && !queryString.trim().isEmpty()) {
	        url.append("?").append(queryString);
	    }
	    return url.toString();
	}

	public static boolean isURLConstaintLanguage(HttpServletRequest request){
	    String uri = request.getServletPath();;
		if(uri.startsWith("/"))uri = uri.substring(1);
        String [] pathArray = uri.split("\\/");
        if (pathArray == null || pathArray.length == 0) return false;
		if (pathArray[0].equalsIgnoreCase(Locale.CANADA.getLanguage())
						|| pathArray[0].equalsIgnoreCase(Locale.CANADA_FRENCH.getLanguage())){
			return true;
		}
		return false;		
	}

	public static boolean isQueryStringConstaintLanguageParameter(HttpServletRequest request){
	    String queryString = request.getQueryString();          // store=employee
	    if (queryString != null && queryString.contains(LANGUAGE_PARAMETER_IN_REQUEST_URL)){
			return true;
		}
		return false;		
	}

	public static boolean isRealActionRequest(HttpServletRequest request){
	    String uri = request.getRequestURI();
	    String servletPath = request.getServletPath();
	    if(servletPath.endsWith(".jsp") || servletPath.endsWith(".html")){
	    	return false;
	    }
		if(uri.startsWith(request.getServletPath())){ 
			uri = uri.substring(request.getServletPath().length());
		}
        String [] uriArray = uri.split("\\/");
        if (uriArray == null || uriArray.length == 0){
        	return true;
        }
//        if (uriArray != null && uriArray[uriArray.length-1].endsWith(DEFAULT_ACTION_SUFFIX)){
//        	return true;
//        }
        String[] uriSuffixArray = uriArray[uriArray.length - 1].split("\\.");
        String uriSuffix = uriSuffixArray[uriSuffixArray.length - 1];
        if (uriSuffix == null || uriSuffix.isEmpty())
        	return  true;
        if (suffixForOtherResourceRequestURL.contains(uriSuffix.toLowerCase())){
        	return false;
        }
		return true;
	}
	
	public static boolean isOtherRelatedApplication(HttpServletRequest request){
	    String uri = request.getServletPath();
		if(uri.startsWith("/"))uri = uri.substring(1);
        String [] pathArray = uri.split("\\/");
        if (pathArray == null || pathArray.length == 0) return false;
		if (otherRelatedApplicationNameSpaces.contains(pathArray[0])){
			return true;
		}
		return false;				
	}
	
	public static String getApplicationURL(HttpServletRequest request){
		return request.getScheme() + "://" + request.getServerName() + ":" 
				+ request.getServerPort()+ request.getContextPath() + "/";
	}

	public static String getRequestParameter(HttpServletRequest request, String queryIdName){
		if (request.getParameter(queryIdName) != null)
			return request.getParameter(queryIdName);
		
		String queryId = "/" + queryIdName + "/";
		String requestUrl = request.getRequestURI();
		int positionOfQueryIdInUrl = requestUrl.indexOf(queryId);
		if (positionOfQueryIdInUrl > 0){
			String parameterString =  requestUrl.substring(positionOfQueryIdInUrl + queryId.length());
			if (parameterString.indexOf("/") >= 0){
				return parameterString.substring(0, parameterString.indexOf("/"));
			}else{
				return parameterString;
			}
			
		}
		return null;		
	}
	
	public static String getQueryStringWithoutLanguageParameter(String queryString, String languageSign, String splitSign){
		if (queryString == null || !queryString.contains(languageSign))
			return queryString;
			
	    	String firstHalfQueryString = queryString.substring(0, queryString.indexOf(languageSign));
	    	String secondHalfQueryString = queryString.substring(queryString.indexOf(languageSign));
	    	int posOfParameterSign = secondHalfQueryString.indexOf(splitSign);
		if (firstHalfQueryString == null || firstHalfQueryString.isEmpty()){
		 	if (posOfParameterSign > 0){
		    		queryString = secondHalfQueryString.substring(posOfParameterSign + 1);
		    	}else{
		    		queryString = new String();
		    	}
		}else{    	
		    	if (posOfParameterSign > 0){
		    		queryString = firstHalfQueryString + secondHalfQueryString.substring(posOfParameterSign + 1);
		    	}else{
				queryString = firstHalfQueryString.substring(0, firstHalfQueryString.length()-1);
		    	}
		}
		return queryString;
		
	}
}
