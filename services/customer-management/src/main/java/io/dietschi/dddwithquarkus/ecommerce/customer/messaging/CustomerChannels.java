package io.dietschi.dddwithquarkus.ecommerce.customer.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
