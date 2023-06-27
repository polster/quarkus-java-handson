package io.dietschi.dddwithquarkus.ecommerce.customer.web;

import io.dietschi.dddwithquarkus.ecommerce.customer.event.CustomerCreatedEvent;
import io.dietschi.dddwithquarkus.ecommerce.customer.messaging.CustomerChannels;
import io.dietschi.dddwithquarkus.ecommerce.customer.repository.CustomerRepository;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final CustomerChannels customerChannels;

    public CustomerController(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper,
            CustomerChannels customerChannels) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerChannels = customerChannels;
    }

    @GET
    @Path("/{id}")
    @Operation(
            description = "Query a customer by user ID."
    )
    public Uni<Customer> one(String id) {

        return customerRepository
                .findByUserId(id)
                .onItem().ifNull().failWith(NotFoundException::new)
                .map(customerMapper::toResource);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            description = "Onboard a new customer."
    )
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "201",
                            description = "Customer created"
                    )
            }
    )
    public Uni<Response> create(CustomerNew customer) {

        return customerRepository
                .persist(customerMapper.toModel(customer))
                .invoke(customerNew -> customerChannels
                        .customerCreatedOut(
                                new CustomerCreatedEvent(customerNew.userId)
                        )
                )
                .map(customerMapper::toResource)
                .map(c -> Response
                        .status(Response.Status.CREATED)
                        .entity(c)
                        .build());
    }
}
