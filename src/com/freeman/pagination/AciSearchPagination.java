package com.freeman.pagination;

import java.util.List;

public class AciSearchPagination extends GoogleStylePagination {

	public static final String ACI_ACTION_NAME = "aciSearch";
	public static final String ACI_ACTION_PARAMETER_SEARCH_TEXT = "q";
	public static final String ACI_ACTION_PARAMETER_START = "start";
	public static final String ACI_ACTION_PARAMETER_RECORDS_PER_PAGE = "perPage";
	
	private String applicationURL;
	private String language;

	public static void main(String[] args){
		String searchText = "EOS";
		AciSearchPagination pagination = new AciSearchPagination(0, 10, 101, "host:port/inetCA/", "en");
		System.out.print("ApplicaitonURL==");
		System.out.print(pagination.getAciSearchActionUrl(pagination.getStart(), pagination.getPerPage(), searchText));
		System.out.println("");
		System.out.print("DisplayPages: ");		
		List<Long> displayPages = pagination.getDisplayPages();
		for (Long i : displayPages){
			System.out.print(i);
		}
		System.out.println("");
		System.out.print("Result Comments: ");
		List<String> comments = pagination.generateSearchResultComment(searchText);
		for (String comment : comments){
			System.out.print(comment + ",");
		}
		System.out.println("");
		System.out.print("Result Pagination: " + pagination.generateSearchResultPagination(searchText));
		
	}
		
	public AciSearchPagination(long start, long perPage, long total, String applicationURL, String language) {
		super(start, perPage, total);
		this.applicationURL = applicationURL;
		this.language = language;
	}


	public String getAciSearchActionUrl(long start, long perPage, String searchText){
		return this.applicationURL 
				+ this.language
				+ "/" + ACI_ACTION_NAME 
				+ "/" + ACI_ACTION_PARAMETER_SEARCH_TEXT + "/" + searchText
				+ "/" + ACI_ACTION_PARAMETER_START + "/" + start 
				+ "/" + ACI_ACTION_PARAMETER_RECORDS_PER_PAGE + "/" + perPage;
	}

	public String getApplicationURL() {
		return applicationURL;
	}

	public void setApplicationURL(String applicationURL) {
		this.applicationURL = applicationURL;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
}
