package io.breezil.queryfiersamples.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import io.breezil.queryfiersamples.api.filters.CityFilter;
import io.breezil.queryfiersamples.api.filters.CityMultiFilter;
import io.breezil.queryfiersamples.entities.City;
import io.breezil.queryfiersamples.server.Dao;

public class Testing {
	
	public static void main(String[] args) {
		multiFilter();
		
//		singleFilter();
	}

	private static void singleFilter() {
		CityFilter filter = new CityFilter();
		filter.addColumns("name", "population");
		filter.addSortedColumns("name");
//		filter.addSortedColumns("country");
		filter.setState("PE");
		filter.setCountry("Brazil");
		Dao.instance().recuperarLista(filter);
	}

	private static void multiFilter() {
		CityMultiFilter filter = new CityMultiFilter();
		filter.addColumns("name", "population");
		filter.addSortedColumns("name");
//		filter.addSortedColumns("country");
//		filter.getCountry().add("Brazil");
//		filter.getCountry().add("Peru");
		Dao.instance().recuperarLista(filter);
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
