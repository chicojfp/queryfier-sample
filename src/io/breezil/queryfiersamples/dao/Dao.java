package io.breezil.queryfiersamples.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import io.breezil.queryfier.dao.GeneralDao;
import io.breezil.queryfier.engine.QBase;
import io.breezil.queryfier.engine.QBaseClass;
import io.breezil.queryfier.engine.QQuery;
import io.breezil.queryfier.engine.QueryBuilder;
import io.breezil.queryfier.engine.transformer.QueryfierTransformer;
import io.breezil.queryfiersamples.api.filters.CityFilter;

public class Dao implements GeneralDao {
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
	
    private <T> T getSingleResultOrNull(Query q) {
    	List<T> dados = q.getResultList();
    	if (!dados.isEmpty()) {
    		return dados.get(0);
    	}
		return null;
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

	@Override
	public <E, T> List<T> searchDTOs(QBaseClass<E, T> filter) {
		QQuery query = criarQueryDTO(filter);
        System.out.println(query.toDTOQuery());
        
        Query q = this.entityManager.createQuery(query.toDTOQuery());
        q.unwrap(org.hibernate.Query.class).setResultTransformer(new QueryfierTransformer(filter.getDestinationType()));
        
        query.getParameters().forEach((name, value) -> q.setParameter(name, value));
        
        List<T> dados = q.getResultList();
        
        return dados;
	}

	@Override
	public <T, E> List<E> searchEntities(QBaseClass<E, T> filter) {
        QQuery query = criarQueryDTO(filter);
        System.out.println(query.toEntityQuery());
        
        TypedQuery<E> q = this.entityManager.createQuery(query.toEntityQuery(), filter.getSourceType());
        
        query.getParameters().forEach((name, value) -> q.setParameter(name, value));
        
        List<E> dados = q.getResultList();
        
        return dados;
	}

	@Override
	public <T, E> E searchEntity(QBaseClass<E, T> filter) {
		QQuery query = criarQueryDTO(filter);
        System.out.println(query.toEntityQuery());
        
        TypedQuery<E> q = this.entityManager.createQuery(query.toEntityQuery(), filter.getSourceType());
        
        query.getParameters().forEach((name, value) -> q.setParameter(name, value));
        
        return q.getSingleResult();
	}

	@Override
	public <T, E> T searchDTO(QBaseClass<E, T> filter) {
		QQuery query = criarQueryDTO(filter);
        System.out.println(query.toDTOQuery());
        
        Query q = this.entityManager.createQuery(query.toDTOQuery());
        q.unwrap(org.hibernate.Query.class).setResultTransformer(new QueryfierTransformer(filter.getDestinationType()));
        
        query.getParameters().forEach((name, value) -> q.setParameter(name, value));
        q.setMaxResults(1);
        
        return this.getSingleResultOrNull(q);
	}

	@Override
	public <T> T getReference(Class<? extends T> clazz, Object primaryKey) {
		return this.entityManager.getReference(clazz, primaryKey);
	}

	@Override
	public <T> void update(T entity) {
		beginTransation();
		this.entityManager.merge(entity);
		endTransaction();
	}

	@Override
	public <T> void persistOrUpdate(T entity) {
		beginTransation();
		this.entityManager.unwrap(Session.class).saveOrUpdate(entity);
		endTransaction();
	}

	@Override
	public <T> void persist(T entity) {
		beginTransation();
		this.entityManager.persist(entity);
		endTransaction();
	}

	private void endTransaction() {
		this.entityManager.flush();
		this.entityManager.getTransaction().commit();
	}

	private void beginTransation() {
		if (!this.entityManager.getTransaction().isActive()) {
			this.entityManager.getTransaction().begin();
		}
	}

	@Override
	public <T> void delete(T entity) {
		beginTransation();
		this.entityManager.remove(entity);
		endTransaction();
	}

	@Override
	public <T> void merge(T entity) {
		beginTransation();
		this.entityManager.merge(entity);
		endTransaction();
	}
	
}
