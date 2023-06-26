package io.dietschi.dddwithquarkus.ecommerce.shipments.web.shipment;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ShipmentMapper {

    Shipment toResource(io.dietschi.dddwithquarkus.ecommerce.shipments.repository.shipment.Shipment shipment);

    io.dietschi.dddwithquarkus.ecommerce.shipments.repository.shipment.Shipment toModel(Shipment shipment);
}
