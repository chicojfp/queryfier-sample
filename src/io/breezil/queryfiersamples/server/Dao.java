package io.breezil.queryfiersamples.server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.transform.Transformers;

import io.breezil.queryfier.engine.QBase;
import io.breezil.queryfier.engine.QBaseClass;
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
	
	public <T, E> List<T> recuperarDTOs(QBase<E, T> filtro) {
        QQuery query = criarQueryDTO(filtro);
        System.out.println(query.toDTOQuery());
        
        Query q = this.entityManager.createQuery(query.toDTOQuery());
        q.unwrap(org.hibernate.Query.class).setResultTransformer(new QueryfierTransformer(filtro.recuperarTipoDTO()));
        
        query.getParameters().forEach((name, value) -> q.setParameter(name, value));
        
        List<T> dados = q.getResultList();
        
        return dados;
    }
    
    public <T, E> T recuperarDTO(QBase<E, T> filtro) {
        QQuery query = criarQueryDTO(filtro);
        System.out.println(query.toDTOQuery());
        
        Query q = this.entityManager.createQuery(query.toDTOQuery());
        q.unwrap(org.hibernate.Query.class).setResultTransformer(new QueryfierTransformer(filtro.recuperarTipoDTO()));
        
        query.getParameters().forEach((name, value) -> q.setParameter(name, value));
        q.setMaxResults(1);
        
        return this.getSingleResultOrNull(q);
    }
    
    private <T> T getSingleResultOrNull(Query q) {
    	List<T> dados = q.getResultList();
    	if (!dados.isEmpty()) {
    		return dados.get(0);
    	}
		return null;
	}

	public <T, E> List<E> recuperarEntidades(QBase<E, T> filtro) {
        QQuery query = criarQueryDTO(filtro);
        System.out.println(query.toEntityQuery());
        
        TypedQuery<E> q = this.entityManager.createQuery(query.toEntityQuery(), filtro.recuperarTipoEntidade());
        
        query.getParameters().forEach((name, value) -> q.setParameter(name, value));
        
        List<E> dados = q.getResultList();
        
        return dados;
    }
    
    public <T, E> E recuperarEntidade(QBase<E, T> filtro) {
        QQuery query = criarQueryDTO(filtro);
        System.out.println(query.toEntityQuery());
        
        TypedQuery<E> q = this.entityManager.createQuery(query.toEntityQuery(), filtro.recuperarTipoEntidade());
        
        query.getParameters().forEach((name, value) -> q.setParameter(name, value));
        
        return q.getSingleResult();
    }
    
    private <T> QQuery criarQueryDTO(T sf) {
        QQuery q = null;
        try {
            q = new QueryBuilder().parseQuery((QBase) sf);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return q;
    }
	
}
