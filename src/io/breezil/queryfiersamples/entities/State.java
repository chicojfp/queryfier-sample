package io.breezil.queryfiersamples.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "state")
public class State {
	List<City> cities;
	City capital;
	String name;
	Person governor;
	Number area;
	Date foundation;
	Integer id;
	Country country;
	
	public State() {
		
	}

	public State(Integer id, String name, City capital) {
//		this.id = id;
		this.name = name;
		this.capital = capital;
	}

	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "state")
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne()
	public Person getGovernor() {
		return governor;
	}

	public void setGovernor(Person governor) {
		this.governor = governor;
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

	@OneToOne
	public City getCapital() {
		return capital;
	}

	public void setCapital(City capital) {
		this.capital = capital;
	}
	
	@ManyToOne()
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
