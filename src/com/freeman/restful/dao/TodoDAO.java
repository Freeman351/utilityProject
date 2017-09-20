package com.freeman.restful.dao;

import java.util.HashMap;
import java.util.Map;

import com.freeman.restful.model.TodoCRUD;

public enum TodoDAO {

	instance;
	  
	private Map<String, TodoCRUD> contentProvider = new HashMap<String, TodoCRUD>();
	  
	private void TodoDao() {
	    
		TodoCRUD todo = new TodoCRUD("1", "Learn REST");
	    todo.setDescription("Read http://www.vogella.com/tutorials/REST/article.html");
	    contentProvider.put("1", todo);
	    todo = new TodoCRUD("2", "Do something");
	    todo.setDescription("Read complete http://www.vogella.com");
	    contentProvider.put("2", todo);
	    
	}
	public Map<String, TodoCRUD> getModel(){
		return contentProvider;
	}
}
