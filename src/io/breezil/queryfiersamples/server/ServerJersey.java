package io.breezil.queryfiersamples.server;

/**
 * @author Crunchify.com
 * 
 */
 
@SuppressWarnings("restriction")
public class ServerJersey {
 /*
    public static void main(String[] args) throws IOException {
        System.out.println("Starting Crunchify's Embedded Jersey HTTPServer...\n");
        HttpServer crunchifyHTTPServer = createHttpServer();
        crunchifyHTTPServer.start();
        System.out.println(String.format("\nJersey Application Server started with WADL available at " + "%sapplication.wadl\n", getCrunchifyURI()));
        System.out.println("Started Crunchify's Embedded Jersey HTTPServer Successfully !!!");
    }
 
        private static HttpServer createHttpServer() throws IOException {
        PackagesResourceConfig crunchifyResourceConfig = new PackagesResourceConfig("io.breezil.queryfiersamples.api");
        Map<String, Object> entries = new HashMap<>();
        entries.put("jersey.config.server.provider.packages", "io.breezil.queryfiersamples.api.filters");
		crunchifyResourceConfig.setPropertiesAndFeatures(entries );
        
//        crunchifyResourceConfig.set
        
//        ClientResponse response = crunchifyResourceConfig.type(MediaType.APPLICATION_XML).put(ClientResponse.class, b1);
        // This tutorial required and then enable below line: http://crunchify.me/1VIwInK
        //crunchifyResourceConfig.getContainerResponseFilters().add(CrunchifyCORSFilter.class);
        return HttpServerFactory.create(getCrunchifyURI(), crunchifyResourceConfig);
    }
 
    private static URI getCrunchifyURI() {
        return UriBuilder.fromUri("http://" + crunchifyGetHostName() + "/").port(8085).build();
    }
 
    private static String crunchifyGetHostName() {
        String hostName = "localhost";
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }
    
    */
}