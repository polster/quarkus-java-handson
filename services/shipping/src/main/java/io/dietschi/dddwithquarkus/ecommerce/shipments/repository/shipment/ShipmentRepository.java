package io.dietschi.dddwithquarkus.ecommerce.shipments.repository.shipment;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ShipmentRepository implements ReactivePanacheMongoRepository<Shipment> {

    public Uni<Shipment> findByShipmentId(String shipmentId) {
        return find("shipmentId", shipmentId).firstResult();
    }
}
