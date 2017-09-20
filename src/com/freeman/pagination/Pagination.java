package com.freeman.pagination;

public class Pagination implements java.io.Serializable{
   private boolean hasPrevious; 
   private boolean hasNext; 
   private int rowsPerPage; // = GlobeConstant.NEWS_RESULT_PER_PAGE; 
   private int maxPage=0; 
   private int curPage=1; 
   private int maxRows=0; 
   
   public void init(int maxRows, int rowsPerPage) { 
	   this.maxRows=maxRows; 
	   this.rowsPerPage=rowsPerPage; 
	   if (maxRows==0) { 
		   maxPage=1; 
	   }
	   else { 
		   maxPage=(maxRows+rowsPerPage-1)/rowsPerPage; 
	   }
   } 
   
   public void first() { 
	   curPage=1; 
	   this.setHasPrevious(false); 
	   refresh(); 
   } 
   
   public void previous() { 
	   curPage--; 
	   refresh(); 
   } 
   
   public void next() { 
	   if (curPage<maxPage) { 
		   curPage++; 
	   } 
	   refresh(); 
   } 
   
   public void last() { 
	   curPage=maxPage; 
	   this.setHasNext(false); 
	   refresh(); 
   } 
   
   public int getCurPage() { 
	   return curPage; 
   } 
   
   public void setCurPage(int curPage) { 
	   this.curPage = curPage; 
	   refresh(); 
   } 
   
   public boolean isHasNext() { 
	   return hasNext; 
   } 
   
   public void setHasNext(boolean hasNext) { 
	   this.hasNext = hasNext; 
   } 
   
   public boolean isHasPrevious() { 
	   return hasPrevious; 
   } 
   
   public void setHasPrevious(boolean hasPrevious) { 
	   this.hasPrevious = hasPrevious; 
   } 
   
   public int getMaxPage() { 
	   return maxPage; 
   } 
   
   public void setMaxPage(int maxPage) { 
	   this.maxPage = maxPage; 
	   refresh(); 
   } 
   
   public int getMaxRows() { 
	   return maxRows; 
   } 
   
   public void setMaxRows(int maxRows) { 
	   this.maxRows = maxRows; 
	   refresh(); 
   } 
   
   public int getRowsPerPage() { 
	   return rowsPerPage; 
   } 
   
   public void setRowsPerPage(int rowsPerPage) { 
	   this.rowsPerPage = rowsPerPage; 
	   refresh(); 
   } 
   
   public void refresh() { 
	   if (maxPage<=1) { 
		   hasPrevious=false; 
		   hasNext=true; 
	   } 
	   else if (curPage==1) { 
		   hasPrevious=false; 
		   hasNext=true; 
	   } 
	   else if (curPage==maxPage) { 
		   hasPrevious=true; 
		   hasNext=false; 
	   } 
	   else { 
		   hasPrevious=true; 
		   hasNext=true; 
	   } 
	} 
   public void reset() { 
	   maxPage=0; 
	   curPage=1; 
	   maxRows=0; 
   }
}