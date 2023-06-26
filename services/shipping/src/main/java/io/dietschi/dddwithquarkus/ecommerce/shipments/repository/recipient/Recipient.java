package io.dietschi.dddwithquarkus.ecommerce.shipments.repository.recipient;

import java.time.Instant;
import java.util.UUID;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(
        collection = "recipients"
)
public class Recipient {

    public String userId;

    public String recipientId = UUID.randomUUID().toString();

    public Address recipientAddress;

    public Instant creationDate = Instant.now();

    public static class Address {

        public String name;
        public String street;
        public String houseNumber;
        public String city;
        public String zip;
    }
}
