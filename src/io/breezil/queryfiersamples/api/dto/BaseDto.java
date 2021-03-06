package io.breezil.queryfiersamples.api.dto;

import java.util.HashMap;
import java.util.Map;

import io.breezil.queryfier.engine.QStatisticsMap;

public class BaseDto implements QStatisticsMap {
	private static final long serialVersionUID = -5904239127543052808L;
	protected final Map<String, Object> aggregs;

	public BaseDto() {
		super();
		this.aggregs = new HashMap<>();
	}
	
	@Override
	public Map<String, Object> getStats() {
		return this.aggregs;
	}

}