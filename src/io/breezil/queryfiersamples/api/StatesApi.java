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
import io.breezil.queryfiersamples.api.dto.StateDto;
import io.breezil.queryfiersamples.api.filters.StateFilter;
import io.breezil.queryfiersamples.controller.GenericController;
import io.breezil.queryfiersamples.entities.State;
 
@Path("states")
public class StatesApi {
	private GenericController<State> controller;
 
    public void setcontroller(GenericController<State> controller) {
        this.controller = controller;
    }
    
    public StatesApi() {
    	this.controller = new GenericController<State>();
    }

    @GET
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response obterTodosDados(@BeanParam StateFilter filtro) {
        List<StateDto> listaDados = this.controller.searchDTOs(filtro);
        return Response.ok(listaDados).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response obterDadoServidor(@PathParam("id") Integer id) {
        List<StateDto> listaDados = this.controller.searchDTOs(new StateFilter(id));
        return Response.ok(listaDados).build();
    }
    
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDados(@PathParam("id") Integer id, JSonPatchOp[] ops) {
        StateDto servidor = this.controller.updateModel(new StateFilter(id), Arrays.asList(ops));
        return Response.ok(servidor).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response removerRegistro(@BeanParam StateFilter filtro) {
        this.controller.deleteModel(filtro);
        return Response.ok().build();
    }
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRegistro(@BeanParam StateFilter filtro, StateDto dados) {
    	filtro.setId(dados.getId());
        StateDto servidor = this.controller.createModel(filtro, dados);
        return Response.ok(servidor).build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRegistro(@BeanParam StateFilter filtro, StateDto dados) {
        StateDto servidor = this.controller.updateFullModel(filtro, dados);
        return Response.ok(servidor).build();
    }
 
}