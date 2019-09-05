package io.breezil.queryfiersamples.api;
 
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.breezil.queryfiersamples.api.filters.CityDto;
import io.breezil.queryfiersamples.api.filters.CityFilter;
import io.breezil.queryfiersamples.server.Dao;
 
@Path("cities")
public class CitiesApi {
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@BeanParam CityFilter filter) {
    	
    	List<CityDto> dados = Dao.instance().recuperarDTOs(filter);
    	
        return Response.ok(dados).build();
    }
 
}