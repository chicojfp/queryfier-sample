package io.breezil.queryfiersamples.api.filters;

import java.util.List;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.breezil.queryfier.engine.QBaseClass;
import io.breezil.queryfier.engine.annotations.QEntity;
import io.breezil.queryfier.engine.annotations.QField;
import io.breezil.queryfier.engine.enums.JoinType;
import io.breezil.queryfiersamples.entities.City;

//@QEntity(name = City.class, alias = "c")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(value = { "columns", "sortedColumns", "distinct" })
public class CityFilter extends QBaseClass<City, CityFilter> {
	@QueryParam(value = "name")
	String name;

	@QueryParam(value = "major")
	@QField(name = "major.name")
	String major;

	@QueryParam(value = "population")
	Long population;
	@QueryParam(value = "id")
	Integer id;

	@QueryParam(value = "country")
	@QField(name = "state.country.name", join = JoinType.LEFT_JOIN)
	String country;

	@QueryParam(value = "state")
	@QField(name = "state.name")
	String state;

	@Override
	@QueryParam(value = "d")
	public void setDistinct(boolean distinct) {
		super.setDistinct(distinct);
	}

	@QueryParam(value = "c")
	public void setColumn(List<String> columnNames) {
		if (columnNames != null)
			columnNames.forEach(c -> this.addColumn(c));
	}
	
	@QueryParam(value = "sc")
	public void setSortedColumns(List<String> columnNames) {
		if (columnNames != null)
			columnNames.forEach(c -> this.addSortedColumns(c));
	}

	public CityFilter() {
		super();
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

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
