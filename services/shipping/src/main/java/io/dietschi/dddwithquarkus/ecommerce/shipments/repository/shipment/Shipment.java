package io.dietschi.dddwithquarkus.ecommerce.shipments.repository.shipment;

import java.time.Instant;
import java.util.UUID;

import io.dietschi.dddwithquarkus.ecommerce.shipments.web.shipment.ShipmentStatus;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(
        collection = "shipments"
)
public class Shipment {

    public String userId;
    public String shipmentId = UUID.randomUUID().toString();
    public String status = ShipmentStatus.NEW.name();
    public String recipientAddressId;
    public Instant creationDate = Instant.now();
}
