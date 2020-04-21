package io.breezil.queryfiersamples.api.filters;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import io.breezil.queryfier.engine.annotations.QField;
import io.breezil.queryfier.engine.enums.CompType;
import io.breezil.queryfiersamples.entities.Person;

public class PersonFilter extends BaseFilter<Person, Person> {
	@QueryParam(value = "name")
	List<String> name;

	@QueryParam(value = "id")
	@QField(name="id", comparator = CompType.IN)
	List<Integer> id;

	public PersonFilter() {
		super();
		this.name = new ArrayList<String>();
		this.id = new ArrayList<Integer>();
	}
	
	public PersonFilter(Integer id) {
		this();
		this.id.add(id);
	}
	
	@Override
	public void postPersistEntity(Person entity) {
		this.getId().add(entity.getId());
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<Integer> getId() {
		return id;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}
	
	

	
}
