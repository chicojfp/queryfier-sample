package io.breezil.queryfiersamples.api;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.print.attribute.standard.DateTimeAtCompleted;

import org.hibernate.transform.Transformers;

import io.breezil.queryfier.engine.QBase;
import io.breezil.queryfier.engine.QQuery;
import io.breezil.queryfier.engine.QueryBuilder;
import io.breezil.queryfiersamples.api.filters.CityFilter;
import io.breezil.queryfiersamples.api.filters.CityMultiFilter;
import io.breezil.queryfiersamples.entities.City;
import io.breezil.queryfiersamples.entities.Country;
import io.breezil.queryfiersamples.entities.State;

public class Dao {
	EntityManagerFactory factory = null;
    EntityManager theManager = null;
	
	public Dao() {
		createDataBase();
	}
	
	public void createDataBase() {
		factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
        theManager = factory.createEntityManager();

        City city = new City("Brasilia", 1231l, 500000l);
        theManager.persist(city);
        
        Country c =  new Country("Brasil", city);
        theManager.persist(city);
        theManager.persist(c);

        State s = new State("DF", city);
        s.setCountry(c);
        theManager.persist(s);
        
        city = new City("Recife", 445l, 990000l);
        s = new State("PE", city);
        s.setCountry(c);
        city.setState(s);
        theManager.persist(city);
        theManager.persist(s);
        

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
	
	public <T> List<T> recuperarLista(T filtro) {
		QQuery query = convertDTO2Query(filtro);
		System.out.println(query.toHql());
		String sql = query.toHql();
		sql = sql.replace("JOIN state.country", "JOIN c.state.country");
		Query q = theManager.createQuery(sql);
		
		
		
	     q.unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.aliasToBean(CityFilter.class));
	     
	     query.getParameters().forEach(p -> {
	    	 q.unwrap(org.hibernate.Query.class).setParameterList(p.getName(), (Collection<?>)p.getValue());
//	    	 q.setParameter(p.getName(), "chicojfp");
	     });
	     
	     List<CityFilter> dados = q.getResultList();
	     
	     System.out.println(dados.size());
		
		return null;
	}

}
