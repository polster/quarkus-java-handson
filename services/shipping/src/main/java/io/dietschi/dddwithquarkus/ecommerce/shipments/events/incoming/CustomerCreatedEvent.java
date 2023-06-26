package io.dietschi.dddwithquarkus.ecommerce.shipments.events.incoming;

public record CustomerCreatedEvent(
        String customerId
) { }
