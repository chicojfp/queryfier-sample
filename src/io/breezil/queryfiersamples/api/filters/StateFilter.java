package io.breezil.queryfiersamples.api.filters;

import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import io.breezil.queryfier.engine.annotations.QEntity;
import io.breezil.queryfier.engine.annotations.QField;
import io.breezil.queryfier.engine.enums.JoinType;
import io.breezil.queryfiersamples.api.dto.StateDto;
import io.breezil.queryfiersamples.entities.State;

@QEntity(name = State.class, alias = "s")
public class StateFilter extends BaseFilter<State, StateDto> {
	@QueryParam("name")
	String name;
	@QueryParam("area")
	Number area;
	@QueryParam("foundation")
	Date foundation;
	@QueryParam("id")
	Integer id;
	
	@QueryParam("governor")
	@QField(name="governor.id", join = JoinType.LEFT_JOIN)
	Integer governor;
	@QField(name="governor.name", join = JoinType.LEFT_JOIN)
	String governor_name;
	
	@QueryParam("country")
	@QField(name="country.id")
	Integer country;
	@QField(name="country.name")
	String country_name;
	
	@QueryParam("capital")
	@QField(name="capital.id")
	Integer capital;
	@QField(name="capital.name")
	String capital_name;
	
	@QueryParam("c")
	@Override
	public void setColumns(List<String> columnNames) {
		if (columnNames == null) return;
		super.setColumns(columnNames);
	}
	
	@QueryParam("sc")
	public void addSortedColumns(List<String> columnName) {
		if (columnName == null) return;
		columnName.forEach(c -> super.addSortedColumns(c));
	}
	
	public StateFilter() {
		super();
	}
	
	public StateFilter(Integer id) {
		super();
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGovernor() {
		return governor;
	}

	public void setGovernor(Integer governor) {
		this.governor = governor;
	}

	public String getGovernor_name() {
		return governor_name;
	}

	public void setGovernor_name(String governor_name) {
		this.governor_name = governor_name;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public Integer getCapital() {
		return capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public String getCapital_name() {
		return capital_name;
	}

	public void setCapital_name(String capital_name) {
		this.capital_name = capital_name;
	}
	
	@Override
	public void postUpdateEntity(State entity) {
		this.setId(entity.getId());
	}
	
	@Override
	public void postPersistEntity(State entity) {
		this.setId(entity.getId());
	}
	
}
