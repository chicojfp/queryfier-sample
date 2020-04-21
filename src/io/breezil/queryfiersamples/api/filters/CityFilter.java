package io.breezil.queryfiersamples.api.filters;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import io.breezil.queryfier.engine.annotations.QField;
import io.breezil.queryfier.engine.enums.CompType;
import io.breezil.queryfier.engine.enums.JoinType;
import io.breezil.queryfiersamples.api.dto.CityDto;
import io.breezil.queryfiersamples.entities.City;

//@QEntity(name = City.class, alias = "c")
public class CityFilter extends BaseFilter<City, CityDto> {
	@QueryParam(value = "name")
	List<String> name;

	@QueryParam(value = "major")
	@QField(name = "major.id", comparator = CompType.IN)
	List<Integer> major;
	
	@QueryParam(value = "major_name")
	@QField(name = "major.name", comparator = CompType.IN)
	List<Integer> major_name;

	@QueryParam(value = "population")
	List<Long> population;
	
	@PathParam(value = "id")
	@QField(name="id", comparator = CompType.IN)
	List<Integer> id;

	@QueryParam(value = "country")
	@QField(name = "state.country.name", join = JoinType.LEFT_JOIN, comparator = CompType.IN)
	List<String> country;

	@QueryParam(value = "state")
	@QField(name = "state.id", comparator = CompType.IN)
	List<Integer> state;
	
	@QueryParam(value = "state_name")
	@QField(name = "state.name", comparator = CompType.IN)
	List<Integer> state_name;

	public CityFilter() {
		super();
		this.name = new ArrayList<>();
		this.major = new ArrayList<>();
		this.id = new ArrayList<>();
		this.country = new ArrayList<>();
		this.state = new ArrayList<>();
	}
	
	public CityFilter(Integer id) {
		this();
		this.id.add(id);
	}

	public void setState(Integer state) {
		this.state.add(state);
	}

	public void setCountry(String country) {
		this.country.add(country);		
	}
	
}
