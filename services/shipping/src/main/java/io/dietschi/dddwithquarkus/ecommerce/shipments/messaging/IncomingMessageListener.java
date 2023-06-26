package io.dietschi.dddwithquarkus.ecommerce.shipments.messaging;

import javax.enterprise.context.ApplicationScoped;

import io.dietschi.dddwithquarkus.ecommerce.shipments.access.Address;
import io.dietschi.dddwithquarkus.ecommerce.shipments.access.Customer;
import io.dietschi.dddwithquarkus.ecommerce.shipments.access.CustomerResourceClient;
import io.dietschi.dddwithquarkus.ecommerce.shipments.events.incoming.CustomerCreatedEvent;
import io.dietschi.dddwithquarkus.ecommerce.shipments.repository.recipient.RecipientRepository;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class IncomingMessageListener {

    private final CustomerResourceClient customerResourceClient;
    private final RecipientRepository recipientRepository;

    private final RecipientMapper recipientMapper;

    public IncomingMessageListener(
            @RestClient CustomerResourceClient customerResourceClient,
            RecipientRepository recipientRepository,
            RecipientMapper recipientMapper) {

        this.customerResourceClient = customerResourceClient;
        this.recipientRepository = recipientRepository;
        this.recipientMapper = recipientMapper;
    }

    @Incoming("customers")
    public void receiveCustomerCreatedEvent(JsonObject payload) {

        CustomerCreatedEvent event = payload.mapTo(CustomerCreatedEvent.class);
        customerResourceClient
                .getById(event.customerId())
                .onFailure().retry().atMost(3)
                .map(Customer::domicileAddress)
                .map(address -> recipientMapper.toModel(event.customerId(), address))
                .flatMap(recipientRepository::persist)
                .subscribe()
                .with(
                        c -> Log.info("New recipient entry created for user: " + c.userId),
                        failure -> Log.error("Failed with error: " + failure)
                );
    }
}
