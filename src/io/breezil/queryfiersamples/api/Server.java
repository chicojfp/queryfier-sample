package io.breezil.queryfiersamples.api;

import java.awt.PageAttributes.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.jersey.server.ResourceConfig;

public class Server {

    public static void main(String[] args) {

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(GreetingsResource.class);

        javax.xml.ws.spi.http.HttpHandler handler = RuntimeDelegate.getInstance()
                .createEndpoint(resourceConfig, javax.xml.ws.spi.http.HttpHandler.class);

//        HttpServer server = HttpServer.createSimpleServer(null, 8880);
//        server.getServerConfiguration().addHttpHandler(handler);

        try {
//            server.start();
            System.out.println("Press any key to stop the server...");
            System.in.read();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Path("/greetings")
    public static class GreetingsResource {

        @GET
//        @Produces(MediaType.)
        public String getGreeting(){
            return "Hello from the other side.";
        }
    }
}
