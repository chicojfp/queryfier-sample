package io.breezil.queryfiersamples.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="person")
public class Person {
	Integer id;
	String name;
	
	public Person() { }
	
	public Person(String name) {
		this.name = name;
	}

	@Id
	@GenericGenerator(name="person" , strategy="increment")
	@GeneratedValue(generator="person")
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
