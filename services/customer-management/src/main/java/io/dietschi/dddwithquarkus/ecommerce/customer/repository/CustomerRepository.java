package io.dietschi.dddwithquarkus.ecommerce.customer.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements ReactivePanacheMongoRepository<Customer> {

    public Uni<Customer> findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }
}
