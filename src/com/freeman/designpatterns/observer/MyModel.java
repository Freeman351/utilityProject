package com.freeman.designpatterns.observer;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MyModel {

	private List<Person> persons = new ArrayList<Person>();

	public List<Person> getPersons() {
		return persons;
	}

	public MyModel() {
		// Just for testing we hard-code the persons here:
		persons.add(new Person("Lars", "Vogel"));
		persons.add(new Person("Jim", "Knopf"));
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		for (Person person : this.persons){
			person.addChangeListener(listener);
		}
		
	}
}
