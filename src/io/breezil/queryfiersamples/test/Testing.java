package io.breezil.queryfiersamples.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import io.breezil.queryfiersamples.api.filters.CityFilter;
import io.breezil.queryfiersamples.api.filters.CityMultiFilter;
import io.breezil.queryfiersamples.dao.Dao;
import io.breezil.queryfiersamples.entities.City;

public class Testing {
	
	public static void main(String[] args) {
		multiFilter();
	}

	private static void singleFilter() {
		CityFilter filter = new CityFilter();
		filter.addColumns("name", "population");
		filter.addSortedColumns("name");

		filter.setState(1);
		filter.setCountry("Brazil");
		Dao.instance().searchDTOs(filter);
	}

	private static void multiFilter() {
		CityMultiFilter filter = new CityMultiFilter();
		filter.addColumns("name", "population");
		filter.addSortedColumns("name");

		Dao.instance().searchDTOs(filter);
	}

	private static void previousTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
        EntityManager theManager = factory.createEntityManager();

        City city = new City();
        city.setName("Surubim");
        theManager.persist(city);
        System.out.println(city.getId());
        
        city = new City();
        city.setName("Limoeiro");
        theManager.persist(city);
        System.out.println(city.getId());

        City p = (City)theManager.find(City.class, 1);
        System.out.println(city.getId());
	}

	public void test2(){

        

    }
}
