package io.dietschi.dddwithquarkus.ecommerce.customer.event;

public record CustomerCreatedEvent(
        String customerId
) { }
