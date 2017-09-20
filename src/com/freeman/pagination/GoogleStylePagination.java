package com.freeman.pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GoogleStylePagination {
	
	public static final long DEFAULT_RECORDS_NUMBER_PER_PAGE = 10l;
	public static final long MAX_DISPALY_PAGE_NUMBER = 10l;
	public static final long MAX_BACKWARD_NUMBER = 5l;
	public static final long MAX_FORWARD_NUMBER = 4l;
	
	private long start;
	private long perPage;
	private long total;
	
	public GoogleStylePagination(long start, long perPage, long total) {
		super();
		if (perPage > 0){
			this.perPage = perPage;			
		}else{
			this.perPage = DEFAULT_RECORDS_NUMBER_PER_PAGE; 
		}

		if (total > 0){
			this.total = total;			
		}else{
			this.total = 0;
		}

		if (start < 0){
			this.start = 0;
		}
		if(start > this.total){
			this.start = this.total / this.perPage * this.perPage;
		}else{
			this.start = start / this.perPage * this.perPage;
		}		
	}

	public List<Long> getDisplayPages(){
		if (getTotalPage() <= MAX_DISPALY_PAGE_NUMBER){ 
			return getAllPages();
		}
		
		if (getTotalPage() - getCurrentPage() < MAX_FORWARD_NUMBER){
			return getDisplayPagesFromForwad();
		}

		return getDisplayPagesFromBackward();
	}
	
	private List<Long> getAllPages(){
		List<Long> pages = new ArrayList<Long>();
		for (long i = 1; i <= getTotalPage(); i++){
			pages.add(i);
		}
		return pages;
	}
	
	private List<Long> getDisplayPagesFromForwad(){
		List<Long> displayPages = getForwardPages(MAX_FORWARD_NUMBER);
		displayPages.add(getCurrentPage());
		displayPages.addAll(getBackwardPages(MAX_DISPALY_PAGE_NUMBER - displayPages.size()));
		Collections.sort(displayPages);
		return displayPages;
		
	}

	private List<Long> getDisplayPagesFromBackward(){
		List<Long> displayPages = getBackwardPages(MAX_BACKWARD_NUMBER);
		displayPages.add(getCurrentPage());
		displayPages.addAll(getForwardPages(MAX_DISPALY_PAGE_NUMBER - displayPages.size()));
		Collections.sort(displayPages);
		return displayPages;
	}
	
	private List<Long> getBackwardPages(long maxBackwardNumber){
		List<Long> pages = new ArrayList<Long>();
		for (long i = 1; i <= maxBackwardNumber  && this.start - (i * this.perPage) >= 0 ; i++){
			pages.add((this.getCurrentPage() - i));
		}		
		return pages;
	}
	
	public List<Long> getForwardPages(Long forwardNumber){
		List<Long> pages = new ArrayList<Long>();
		for (long i = 1; i <= forwardNumber && this.start + (i * this.perPage) < this.total ; i++){
			pages.add((this.getCurrentPage() + i));
		}		
		return pages;
	}

	public long getTotalPage(){
		return this.total/this.perPage + (this.total % this.perPage == 0 ? 0 : 1 );
	}
	
	public long getCurrentPage(){
		if (this.start < this.total){
			return this.start/this.perPage + 1;			
		}
		return this.getTotalPage();
	}

	public List<String> generateSearchResultComment(String searchText){
		//Results 1 - 10 of about 2001 for eos. // start - end about total for searchText
		
		List<String> comments = new ArrayList<String>();
		if (this.getStart() + 1 >= this.getTotal()){
			comments.add("" + (this.getTotal()));
		}else{
			comments.add("" + (this.getStart() + 1));
		}

		if ((this.getStart() + this.getPerPage()) <= this.getTotal()){
			comments.add("" + (this.getStart() + this.getPerPage()));
		}else{
			comments.add("" + this.getTotal());			
		}
		
		comments.add("" + this.getTotal());
		comments.add(searchText);
		return comments;
	}

	public String generateSearchResultPagination(String searchText){
		StringBuffer paginationBuffer = new StringBuffer();
		if (this.getStart() > 0){
			paginationBuffer.append(generatePreviousLink(searchText));
		}
		paginationBuffer.append(generateNumberPagesLink(searchText));
		if (this.getTotal() > this.getStart() + this.getPerPage()){
			paginationBuffer.append(generateNextLink(searchText));
		}
		return paginationBuffer.toString();		
	}

	private String generatePreviousLink(String searchText){
		StringBuffer previousLink = new StringBuffer("<td nowrap=\"1\"><span class=\"b\"><a href=\"");
		previousLink.append(getAciSearchActionUrl((this.getStart() - this.getPerPage()), this.getPerPage(), searchText));
		previousLink.append("\">Previous</a></span>&nbsp;</td>");
		return previousLink.toString();		
	}
	
	private String generateNumberPagesLink(String searchText){
		List<Long> displayPages = this.getDisplayPages();
		StringBuffer pageNumberLinkBuffer = new StringBuffer();
		for (long pageNumber : displayPages){
			if (pageNumber == this.getCurrentPage()){
				pageNumberLinkBuffer.append("<td>&nbsp;<span class=\"i\">" +  pageNumber + "</span>&nbsp;</td>");
			}else{
				pageNumberLinkBuffer.append("<td>&nbsp;<a href=\"");
				pageNumberLinkBuffer.append(generatePageNumberLink(pageNumber, this.getPerPage(), searchText));
				pageNumberLinkBuffer.append("\">" + pageNumber + "</a>&nbsp;</td>");
			}			
		}
		return pageNumberLinkBuffer.toString();		
	}

	private String generatePageNumberLink(long pageNumber, long perPage, String searchText){
		return getAciSearchActionUrl(((pageNumber - 1) * perPage), perPage, searchText);
	}
	
	private String generateNextLink(String searchText){
		StringBuffer nextLink = new StringBuffer("<td nowrap=\"1\">&nbsp;<span class=\"b\"><a href=\"");
		nextLink.append(getAciSearchActionUrl((this.getStart() + this.getPerPage()), this.getPerPage(), searchText));
		nextLink.append("\">Next</a></span></td>");
		return nextLink.toString();		
	}
	
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getPerPage() {
		return perPage;
	}
	public void setPerPage(long perPage) {
		this.perPage = perPage;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public abstract String getAciSearchActionUrl(long start, long perPage, String searchText);

}
