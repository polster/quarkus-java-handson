package io.dietschi.dddwithquarkus.ecommerce.shipments.web.shipment;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dietschi.dddwithquarkus.ecommerce.shipments.repository.recipient.Recipient;
import io.dietschi.dddwithquarkus.ecommerce.shipments.repository.recipient.RecipientRepository;
import io.dietschi.dddwithquarkus.ecommerce.shipments.repository.shipment.ShipmentRepository;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/shipments")
@Produces(MediaType.APPLICATION_JSON)
public class ShipmentController {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;

    private final RecipientRepository recipientRepository;

    public ShipmentController(
            ShipmentRepository shipmentRepository,
            ShipmentMapper shipmentMapper,
            RecipientRepository recipientRepository) {

        this.shipmentRepository = shipmentRepository;
        this.shipmentMapper = shipmentMapper;
        this.recipientRepository = recipientRepository;
    }

    @GET
    @Path("/{id}")
    @Operation(
            description = "Query a shipment by user ID."
    )
    public Uni<Shipment> one(String id) {

        return shipmentRepository
                .findByShipmentId(id)
                .onItem().ifNull().failWith(NotFoundException::new)
                .map(shipmentMapper::toResource);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            description = "Create a new shipment."
    )
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "201",
                            description = "Shipment created"
                    )
            }
    )
    public Uni<Shipment> create(ShipmentNew shipment) {

        Uni<Recipient> recipient = recipientRepository
                .findByUserId(shipment.userId())
                .onItem().ifNull().failWith(() -> new NotFoundException("No recipient address found"));

        // TODO: Use Recipient + userId from shipment to create a new Shipment...
        return Uni.createFrom().item(new Shipment("", "", null, null));
    }
}
