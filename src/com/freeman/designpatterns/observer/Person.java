package com.freeman.designpatterns.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Person {
	public static final String FIRSTNAME = "firstName";
	public static final String LASTNAME = "lastName";

	private String firstName;
    private String lastName;

    private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

    public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      notifyListeners(this, FIRSTNAME, this.firstName, this.firstName = firstName);
      
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      notifyListeners(this, LASTNAME, this.lastName, this.lastName = lastName);
    }
    
    private void notifyListeners(Object object, String property, String oldValue, String newValue) {
        for (PropertyChangeListener name : listener) {
        	name.propertyChange(new PropertyChangeEvent(this, "firstName", oldValue, newValue));
        }
    }
    
	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}
}
