package io.breezil.queryfiersamples.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "country")
public class Country {
	List<State> state;
	City capital;
	Integer id;
	String name;
	Person presidente;

	public Country() {

	}

	public Country(String name, City capital) {
		this.name = name;
		this.capital = capital;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "country")
	public List<State> getState() {
		return state;
	}

	public void setState(List<State> state) {
		this.state = state;
	}

	@OneToOne()
	public City getCapital() {
		return capital;
	}

	public void setCapital(City capital) {
		this.capital = capital;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne()
	public Person getPresidente() {
		return presidente;
	}

	public void setPresidente(Person presidente) {
		this.presidente = presidente;
	}
	
}
