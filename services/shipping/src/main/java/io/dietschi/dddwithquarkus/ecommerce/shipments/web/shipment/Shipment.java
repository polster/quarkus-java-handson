package io.dietschi.dddwithquarkus.ecommerce.shipments.web.shipment;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
        description = "The representation of a shipment."
)
public record Shipment(
        @Schema(
                example = "6c4be71b-7227-44ed-91ef-17dbc994cee3"
        )
        String shipmentId,
        String userId,
        ShipmentStatus status,
        String recipientAddressId
) { }
