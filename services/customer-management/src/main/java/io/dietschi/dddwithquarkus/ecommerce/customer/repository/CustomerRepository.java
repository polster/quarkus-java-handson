package io.dietschi.dddwithquarkus.ecommerce.customer.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class CustomerRepository implements ReactivePanacheMongoRepository<Customer> {

    public Uni<Customer> findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }
}
