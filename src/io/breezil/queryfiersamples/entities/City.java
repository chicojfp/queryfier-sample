package io.breezil.queryfiersamples.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="city")
public class City {
	String name;
	Person major;
	Long population;
	Number area;
	Date foundation;
	Integer id;
	State state;
	
	public City() {
		
	}
	
	public City(String name, Long area, Long population) {
		this.name = name;
		this.area = area;
		this.population = population;
	}
	
	@Id
	@GenericGenerator(name="city" , strategy="increment")
	@GeneratedValue(generator="city")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne()
	public Person getMajor() {
		return major;
	}
	public void setMajor(Person major) {
		this.major = major;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	public Number getArea() {
		return area;
	}
	public void setArea(Number area) {
		this.area = area;
	}
	public Date getFoundation() {
		return foundation;
	}
	public void setFoundation(Date foundation) {
		this.foundation = foundation;
	}
	
	@ManyToOne()
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	
	
}
