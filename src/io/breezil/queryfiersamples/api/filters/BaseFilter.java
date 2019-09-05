package io.breezil.queryfiersamples.api.filters;

import java.util.List;

import javax.ws.rs.QueryParam;

import io.breezil.queryfier.engine.QBaseClass;
import io.breezil.queryfiersamples.entities.City;

public class BaseFilter<E, D> extends QBaseClass<E, D> {

	public BaseFilter() {
		super();
	}

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

}