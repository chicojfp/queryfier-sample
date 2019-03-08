package io.breezil.queryfiersamples.api.filters;

import javax.ws.rs.QueryParam;

import io.breezil.queryfier.engine.QBaseClass;
import io.breezil.queryfier.engine.annotations.QEntity;
import io.breezil.queryfier.engine.annotations.QField;
import io.breezil.queryfiersamples.entities.City;

@QEntity(name = City.class, alias = "c")
public class CityFilter extends QBaseClass {
	String name;
	
	@QueryParam(value="major")
	@QField(name="major.name")
	String major;
	
	@QueryParam(value="country")
	@QField(name="state.country.name")
	String country;
	
	@QueryParam(value="state")
	@QField(name="state.name")
	String state;
	
	public CityFilter() {
		super();
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return this.major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public void setState(String state) {
		this.state = state;
	}
}
