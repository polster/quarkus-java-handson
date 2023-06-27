package io.dietschi.dddwithquarkus.ecommerce.shipments.access;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/customers")
@RegisterRestClient(configKey = "customer-api")
public interface CustomerResourceClient {

    @GET
    @Path("/{id}")
    Uni<Customer> getById(String id);
}
