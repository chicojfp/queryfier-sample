package io.breezil.queryfiersamples.dao;

import javax.persistence.EntityManager;

import io.breezil.queryfiersamples.entities.City;
import io.breezil.queryfiersamples.entities.Country;
import io.breezil.queryfiersamples.entities.Person;
import io.breezil.queryfiersamples.entities.State;

public class DataBaseBuilder {
	
	public static void createDataBase(EntityManager theManager) {

        Person chicojfp = new Person("Brasileiro da Silva");
        Person chico = new Person("Brasiliense Barbosa");
        theManager.persist(chico);
        theManager.persist(chicojfp);
        
        City brasilia = new City("Brasília", 1231l, 3000l);
        brasilia.setMajor(chicojfp);
        theManager.persist(brasilia);

        Country brasil =  new Country("Brasil", brasilia);
        theManager.persist(brasilia);
        theManager.persist(brasil);
        
        theManager.getTransaction().begin();
        State df = new State(1, "DF", brasilia);
        df.setCountry(brasil);
        theManager.persist(df);
        
        brasilia.setState(df);
        theManager.merge(df);

        
        City tagua = new City("Taquatinga", 1231l, 40000l);
        tagua.setMajor(chicojfp);
        tagua.setState(df);
        theManager.persist(tagua);
        
        City recife = new City("Recife", 445l, 500000l);
        recife.setMajor(chico);
        State pe = new State(2, "PE", recife);
        pe.setCountry(brasil);
        recife.setState(pe);
        theManager.persist(recife);
        theManager.persist(pe);
        
        City surubim = new City("Surubim", 10l, 200l);
        surubim.setMajor(chicojfp);
        surubim.setState(pe);
        theManager.persist(surubim);
        
        City lim = new City("Surubim", 11l, 10l);
        lim.setState(pe);
        lim.setMajor(chico);
        theManager.persist(lim);
        System.out.println(lim.getId());

        theManager.flush();
        theManager.getTransaction().commit();
	}

}
