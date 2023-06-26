package io.dietschi.dddwithquarkus.ecommerce.shipments.access;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/customers")
@RegisterRestClient(configKey = "customer-api")
public interface CustomerResourceClient {

    @GET
    @Path("/{id}")
    Uni<Customer> getById(String id);
}
