package io.breezil.queryfiersamples.controller;

import javax.inject.Named;
import javax.ws.rs.ext.Provider;

import io.breezil.queryfier.dao.GeneralDao;
import io.breezil.queryfier.patch.PatchJsonHelper;
import io.breezil.queryfier.serializer.ISerializer;
import io.breezil.queryfier.service.GeneralService;
import io.breezil.queryfiersamples.dao.Dao;

@Provider
@Named("GenericController")
public class GenericController<T> extends GeneralService<T> {
	private GeneralDao gDao;
	private PatchJsonHelper patch;
	private ISerializer serializer;

	public GenericController() {
		super();
		this.serializer = new ISerializer() { };
		this.gDao = Dao.instance();
		this.patch = new PatchJsonHelper(this.getDao(), this.getDeserializer());
	}

//	@Inject
	public void setGDao(GeneralDao gDao) {
		this.gDao = gDao;
	}

	public GeneralDao getDao() {
		return this.gDao;
	}

	public ISerializer getDeserializer() {
		return this.serializer;
	}

	@Override
	public PatchJsonHelper getPatchParser() {
		return this.patch;
	}

	public ISerializer getSerializer() {
		return serializer;
	}

	public void setSerializer(ISerializer serializer) {
		this.serializer = serializer;
		this.patch = new PatchJsonHelper(this.getDao(), this.getDeserializer());
	}
	
	

}