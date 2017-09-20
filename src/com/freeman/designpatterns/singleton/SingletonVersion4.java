package com.freeman.designpatterns.singleton;

public class SingletonVersion4 {

	  private static SingletonVersion4 uniqInstance;

	  private SingletonVersion4() {
	  }

	  public static synchronized SingletonVersion4 getInstance() {
	    if (uniqInstance == null) {
	      uniqInstance = new SingletonVersion4();
	    }
	    return uniqInstance;
	  }	
}
