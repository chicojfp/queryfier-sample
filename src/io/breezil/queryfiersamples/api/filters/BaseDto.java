package io.breezil.queryfiersamples.api.filters;

import java.util.HashMap;
import java.util.Map;

import io.breezil.queryfier.engine.QStatisticsMap;

public class BaseDto implements QStatisticsMap {

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