package io.breezil.queryfiersamples.api.filters;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import io.breezil.queryfier.engine.QBaseClass;
import io.breezil.queryfier.engine.annotations.QEntity;
import io.breezil.queryfier.engine.annotations.QField;
import io.breezil.queryfier.engine.enums.CompType;
import io.breezil.queryfier.engine.enums.JoinType;
import io.breezil.queryfiersamples.entities.City;

@QEntity(name = City.class, alias = "c")
public class CityMultiFilter extends QBaseClass {
	@QueryParam(value="name")
	List<String> name = new ArrayList<>();
	
	@QueryParam(value="major")
	@QField(name="major.name", comparator=CompType.EQUALS)
	List<String> major = new ArrayList<>();
	
	Long population;
	
	@QueryParam(value="country")
	@QField(name="state.country.name", join=JoinType.LEFT_JOIN, comparator=CompType.IN)
	List<String> country = new ArrayList<>();
	
	@QueryParam(value="state")
	@QField(name="state.name")
	List<String> state = new ArrayList<>();
	
	@Override
	@QueryParam(value="column")
	public void addColumn(String columnNames) {
		super.addColumn(columnNames);
	}
	
	public CityMultiFilter() {
		super();
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getMajor() {
		return major;
	}

	public void setMajor(List<String> major) {
		this.major = major;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public List<String> getCountry() {
		return country;
	}

	public void setCountry(List<String> country) {
		this.country = country;
	}

	public List<String> getState() {
		return state;
	}

	public void setState(List<String> state) {
		this.state = state;
	}
}