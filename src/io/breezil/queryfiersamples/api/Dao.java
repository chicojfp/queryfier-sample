package io.breezil.queryfiersamples.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.transform.Transformers;

import io.breezil.queryfier.engine.QBase;
import io.breezil.queryfier.engine.QQuery;
import io.breezil.queryfier.engine.QueryBuilder;
import io.breezil.queryfiersamples.entities.City;

public class Dao {
	
	public void createDataBase() {
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
	
	private <T> QQuery convertDTO2Query(T sf) {
		QQuery q = null;
		try {
			q = new QueryBuilder().parseQuery((QBase) sf);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return q;
	}
	
	public <T> List<T> recuperarListaI(T filtro) {
		QQuery query = convertDTO2Query(filtro);
		System.out.println(query.toHql());
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
        EntityManager theManager = factory.createEntityManager();
		Query q = theManager.createQuery(query.toHql());
		
		query.getParameters().forEach((name, value) -> {
			q.setParameter(name, value);
		});

	     q.unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.aliasToBean(filtro.getClass()));
	     
	     List<T> dados = q.getResultList();
		
		return dados;
	}

}
