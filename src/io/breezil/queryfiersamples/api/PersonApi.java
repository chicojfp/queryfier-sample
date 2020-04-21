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
import io.breezil.queryfiersamples.api.filters.PersonFilter;
import io.breezil.queryfiersamples.controller.GenericController;
import io.breezil.queryfiersamples.entities.Person;
 
@Path("people")
public class PersonApi {
	private GenericController<Person> controller;
 
    public void setcontroller(GenericController<Person> controller) {
        this.controller = controller;
    }
    
    public PersonApi() {
    	this.controller = new GenericController<Person>();
    }

    @GET
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response obterTodosDados(@BeanParam PersonFilter filtro) {
        List<Person> data = this.controller.searchDTOs(filtro);
        return Response.ok(data).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response obterDadoServidor(@PathParam("id") Integer id) {
        List<Person> data = this.controller.searchDTOs(new PersonFilter(id));
        return Response.ok(data).build();
    }
    
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDados(@PathParam("id") Integer id, JSonPatchOp[] ops) {
    	Person dto = this.controller.updateModel(new PersonFilter(id), Arrays.asList(ops));
        return Response.ok(dto).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response removerRegistro(@BeanParam PersonFilter filtro) {
        this.controller.deleteModel(filtro);
        return Response.ok().build();
    }
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRegistro(@BeanParam PersonFilter filtro, Person dados) {
    	Person dto = this.controller.createModel(filtro, dados);
        return Response.ok(dto).build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRegistro(@BeanParam PersonFilter filtro, Person dados) {
    	Person dto = this.controller.updateFullModel(filtro, dados);
        return Response.ok(dto).build();
    }
 
}