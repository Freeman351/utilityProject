package com.freeman.constants;

/**
 * Contains all constants used in project
 * 
 * @author BDU
 * 
 */
public class GlobeConstant implements java.io.Serializable {

	public static final String SUCCESS = "success";

	public static final String FAILED = "failed";

	public static final String LOCALE = "locale";
	public static final String LANGUAGE = "lang";
	public static final String LANGUAGE_EN = "en";

	public static final String USER = "userInfo";
	public static final String REQUEST_USER = "request_user";
	public static final String LOGIN_USER = "login_user";

	public static final String NEED_LOGIN = "needLogin";
	public static final String NO_ACCESS = "cannotaccess";
	public static final String LOGIN_PAGE = "loginPage";
	public static final String REDIRECT_PAGE = "redirect";
	public static final String PROCESS_PAGE = "process";
	public static final String METHOD_LOAD = "load";
	public static final String PARAMETER_INIT = "init";
	public static final String APP_CONTENT = "/inetCA";

	public static final String EMAIL_MANAGER_PROPERTIES = "ca/canon/contentmgmt/resources/EmailManager.properties";

	public static final String NAVIGATE_BAR = "nav_bar";

	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final String SAVED_URL = "saved_url";

	public static final String SESSION = "app_session";

	public static final String LIST_FORM = "list_form";

	public static final int MAX_DISPLAY_NUMBER = 100;

	public static final int NEWS_RESULT_PER_PAGE = 10;

	public static final int MAX_NEWS_SEARCH_YEAR = 4; // This is to set the
														// number of years to be
														// searched in Press
														// Release

	public static final String TOP_NAV = "top_navigation";
	public static final String LEFT_NAV = "left_navigation";
	public static final String SELECTED_MENU = "selected_menu";

	public static final String CANADA_POST_SERVICE_PROPERTIES = "ca/canon/inet/resources/CanadaPostService.properties";
	public static final String BREADCRUMB_LINK_PROPERTIES = "ca/canon/inet/resources/BreadcrumbLinks.properties";
	public static final String COMMON_MENU_PROPERTIES = "ca/canon/inet/resources/CommonNavigations.xml";
	public static final String SUPPORT_LINKS_PROPERTIES = "ca/canon/inet/resources/supportLinks.xml";

	public static final String BREADCRUMB = "breadcrumb";

	public static final String NEW_PRODUCT = "new_product";
	public static final String PRODUCTS = "products";
	public static final String ARC_PRODUCTS = "arcproducts";

	public static final String PAGE_METHOD = "m";

	public static final String LINK_TYPE_EXTERNAL = "E";
	public static final String LINK_TYPE_INTERNAL = "I";
	public static final String LINK_TYPE_LINK = "L";
	public static final String LINK_TYPE_FRAME = "F";

	public static final String PRODUCT_ID = "pid";
	public static final String SUB_CATEGORY_ID = "scatid";
	public static final String MARKET_SEGMENT_ID = "msegid";
	public static final String CATEGORY_ID = "catid";

	public static final String CURRENT_MARKETSEG = "curr_marketSeg";
	public static final String CURRENT_CATEGORY = "curr_category";
	public static final String CURRENT_SUBCATEGORY = "curr_subCategory";

	public static final String COMMON_NAV = "common_navigation";

	public static final String OVERVIEW_SECTION_CODE = "010"; // "Overview";
	public static final String FEATURES_SECTION_CODE = "020"; // "Features";
	public static final String SPECS_SECTION_CODE = "030"; // "Specifications";
	public static final String SECTION_CODE = "sectionCode"; 

	public static final int NEW_PRODUCT_OVERVIEW_LENGTH = 30; // "Specifications";
	public static final String DOWNLOAD_DRIVER = "DD";
	public static final String DEALER_LOCATOR = "DL";

	public static final int FEATURE_PRODUCT_PER_PAGE = 3;

	public static final Long GENERALMARKETSEGID = new Long(999999999);
	public static final Long PRESIDENTMARKETSEGID = new Long(999999998);

	public static final String DIVISION_CIG = "C";
	public static final String DIVISION_ISG = "I";
	public static final String DIVISION_MED = "M";
	public static final String DIVISION_BSD = "B";
	public static final String DIVISION_OCE = "O";

	public static final String G_SERACH_HOST = "http://146.184.121.149/search?";
	public static final String G_SERACH_STRING_EN = "access=p&entqr=0&ud=1&sort=date%3AD%3AL%3Ad1&output=xml_no_dtd&oe=UTF-8&ie=UTF-8&client=CCI_Search&proxystylesheet=CCI_Search&site=default_collection&q=";
	public static final String G_SERACH_STRING_FR = "access=p&entqr=0&output=xml_no_dtd&sort=date%3AD%3AL%3Ad1&entsp=0&lr=lang_fr&client=CCI_Srch_FR&ud=1&oe=UTF-8&ie=UTF-8&proxystylesheet=CCI_Srch_FR&site=default_collection&q=";

	public static final String IDOl_HOST = "http://search.cusa.canon.com"; // "http://searchqa.cusa.canon.com";
	public static final String IDOl_PORT = "80"; // "9300";
	public static final String ACI_BASE_QUERY = "action=query&totalresults=true&combine=simple";
	
	public static final String PAGINATION_SESSION_KEY = "PAGINATION";
	public static final String SRCHWHERE_SESSION_KEY = "SRCHWHERE";

	public static final String YES_FLAG = "y";

	public static final String CIG = "C";
	public static final String ISG = "I";
	public static final String OCE = "O";

	// for Webtrends Tracking
	public static final String WEB_TREND_URL = "/inetCA/";

	// ITR 130-001925
	public static final String CONTENT_FOLDER_PROPERTIES = "ca/canon/inet/resources/ContentFolder.properties";
	
	//Page Title and Meta Description
	public static final String META_TITLE_FOR_HOME_PAGE="Canon Canada Inc.";
	public static final String META_DESCRIPTION_FOR_HOME_PAGE="Home Page Of Canon Canada Inc.";

	 // ITR Disclaimer
	public static final String DISCLAIMER_FOLDER = "http://10.124.5.148/Disclaimer/";
	public static final String DISCLAIMER_HOME_FOLDER ="D:/CanonUserFiles/deployApp/inetCAWeb.war";//this is physical directory for Disclaimers
	//public static final String DISCLAIMER_HOME_FOLDER ="s:/deployApp/inetCAWeb.war";
	public static final String DISCLAIMER_HOME_FOLDER_EN ="/userfiles/Disclaimer/en/";
	public static final String DISCLAIMER_HOME_FOLDER_FR ="/userfiles/Disclaimer/fr/";
	public static final String LOCALHOST_HOME ="http://localhost:8080";

	// ITR 120-006262
	public static final String INETCA_PROGRAM_ID = "INETCA";

	public static final String EMAIL_ADDRESS_WEBMASTER = "webmaster@canada.canon.com";

}
