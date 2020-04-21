package io.breezil.queryfiersamples.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class StateDto extends BaseDto {
	private static final long serialVersionUID = 6309976973071999675L;
	String name;
	Number area;
	Date foundation;
	Integer id;
	Integer governor;
	String governor_name;
	Integer country;
	String country_name;
	Integer capital;
	String capital_name;

	public StateDto() {
		super();
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

}
