package io.dietschi.dddwithquarkus.ecommerce.shipments.messaging;

import io.dietschi.dddwithquarkus.ecommerce.shipments.access.Address;
import io.dietschi.dddwithquarkus.ecommerce.shipments.repository.recipient.Recipient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "cdi")
public interface RecipientMapper {

    @Mapping(source = "address", target = "recipientAddress", qualifiedByName = "toModelAddress")
    Recipient toModel(String userId, Address address);

    @Named("toModelAddress")
    Recipient.Address toModelAddress(Address address);
}
