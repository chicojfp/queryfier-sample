package io.breezil.queryfiersamples.server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import io.breezil.queryfier.engine.QBase;
import io.breezil.queryfier.engine.QQuery;
import io.breezil.queryfier.engine.QueryBuilder;
import io.breezil.queryfier.engine.transformer.QueryfierTransformer;
import io.breezil.queryfiersamples.api.filters.CityFilter;
import io.breezil.queryfiersamples.dao.DataBaseBuilder;

public class Dao {
	EntityManagerFactory factory = null;
    EntityManager entityManager = null;
    
    private static Dao instance;
	
	private Dao() {
		factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
		entityManager = factory.createEntityManager();
		DataBaseBuilder.createDataBase(entityManager);
	}
	
	public static Dao instance() {
		if (instance == null) {
			instance = new Dao();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	private <T> QQuery convertDTO2Query(T sf) {
		QQuery q = null;
		try {
			q = new QueryBuilder().parseQuery((QBase<? extends Object, ? extends Object>) sf);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return q;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public <T> List<T> recuperarLista(T filtro) {
		QQuery query = convertDTO2Query(filtro);
		String sql = query.toDTOQuery();
		Query q = entityManager.createQuery(sql);

		System.out.println(sql);
		
	     q.unwrap(org.hibernate.Query.class).setResultTransformer(new QueryfierTransformer(CityFilter.class));
	     
	     query.getParameters().forEach((k, v) -> {
	    	 q.setParameter(k, v);
	     });
	     
	     List<T> dados = q.getResultList();
	     
		return dados;
	}

}
