package io.breezil.queryfiersamples.server;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		exception.printStackTrace();
		return Response.status(
				Response.Status.NOT_FOUND). entity(exception.getMessage()).build();
	}

}
