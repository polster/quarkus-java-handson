package io.dietschi.dddwithquarkus.ecommerce.customer.repository;

import java.time.Instant;
import java.util.UUID;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(
        collection="customers"
)
public class Customer {

    public String userId = UUID.randomUUID().toString();
    public String firstName;
    public String lastName;
    public Address domicileAddress;
    public Address billingAddress;
    public Instant createdDate = Instant.now();

    public static class Address {
        public String street;
        public String houseNumber;
        public String city;
        public String zip;
    }
}
