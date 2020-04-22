package io.breezil.queryfiersamples.api;
 
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.breezil.queryfier.patch.JSonPatchOp;
import io.breezil.queryfiersamples.api.dto.CityDto;
import io.breezil.queryfiersamples.api.filters.CityFilter;
import io.breezil.queryfiersamples.controller.GenericController;
import io.breezil.queryfiersamples.entities.City;
import io.breezil.queryfiersamples.infra.SampleSerializer;
 
@Path("cities")
public class CitiesApi {
	private GenericController<City> controller;
 
    public void setcontroller(GenericController<City> controller) {
        this.controller = controller;
    }
    
    public CitiesApi() {
    	this.controller = new GenericController<City>();
    	this.controller.setSerializer(new SampleSerializer());
    }

    @GET
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response obterTodosDados(@BeanParam CityFilter filtro) {
        List<CityDto> listaDados = this.controller.searchDTOs(filtro);
        return Response.ok(listaDados).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response obterDadoServidor(@PathParam("id") Integer id) {
        List<CityDto> listaDados = this.controller.searchDTOs(new CityFilter(id));
        return Response.ok(listaDados).build();
    }
    
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDados(@PathParam("id") Integer id, JSonPatchOp[] ops) {
        CityDto servidor = this.controller.updateModel(new CityFilter(id), Arrays.asList(ops));
        return Response.ok(servidor).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response removerRegistro(@BeanParam CityFilter filtro) {
        this.controller.deleteModel(filtro);
        return Response.ok().build();
    }
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRegistro(@BeanParam CityFilter filtro, CityDto dados) {
//    	filtro.setId(dados.getCodigo());
        CityDto servidor = this.controller.createModel(filtro, dados);
        return Response.ok(servidor).build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRegistro(@BeanParam CityFilter filtro, CityDto dados) {
        CityDto servidor = this.controller.updateFullModel(filtro, dados);
        return Response.ok(servidor).build();
    }
 
}