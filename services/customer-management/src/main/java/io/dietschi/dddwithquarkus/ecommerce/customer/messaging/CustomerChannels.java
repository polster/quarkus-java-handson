package io.dietschi.dddwithquarkus.ecommerce.customer.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.dietschi.dddwithquarkus.ecommerce.customer.event.CustomerCreatedEvent;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class CustomerChannels {

    @Inject
    @Channel("customers")
    Emitter<CustomerCreatedEvent> emitter;

    public void customerCreatedOut(CustomerCreatedEvent event) {

        emitter.send(event);
    }
}
