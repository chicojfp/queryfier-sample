package io.breezil.queryfiersamples.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="city")
public class City {
	String name;
	String major;
	Long pupulation;
	Number area;
	Date foundation;
	Integer id;
	State state;
	
	@Id
	@GeneratedValue
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Long getPupulation() {
		return pupulation;
	}
	public void setPupulation(Long pupulation) {
		this.pupulation = pupulation;
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
	
	@ManyToOne
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	
	
}
