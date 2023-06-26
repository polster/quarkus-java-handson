package io.dietschi.dddwithquarkus.ecommerce.customer.web;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CustomerMapper {

    Customer toResource(io.dietschi.dddwithquarkus.ecommerce.customer.repository.Customer customer);

    io.dietschi.dddwithquarkus.ecommerce.customer.repository.Customer toModel(CustomerNew customer);

    Address toResourceAddress(io.dietschi.dddwithquarkus.ecommerce.customer.repository.Customer.Address address);

    io.dietschi.dddwithquarkus.ecommerce.customer.repository.Customer.Address toModelAddress(Address address);
}
