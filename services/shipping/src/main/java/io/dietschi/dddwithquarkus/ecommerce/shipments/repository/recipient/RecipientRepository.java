package io.dietschi.dddwithquarkus.ecommerce.shipments.repository.recipient;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class RecipientRepository implements ReactivePanacheMongoRepository<Recipient> {

    public Uni<Recipient> findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }
}
