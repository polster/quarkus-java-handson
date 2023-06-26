package io.dietschi.dddwithquarkus.ecommerce.customer.web;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
        description = "The customer`s address"
)
public record Address(
        @Schema(
                example = "Badenerstrasse",
                required = true
        )
        String street,
        @Schema(
                example = "23a",
                required = false
        )
        String houseNumber,
        @Schema(
                example = "Zürich",
                required = true
        )
        String city,
        @Schema(
                example = "8000",
                required = true
        )
        Integer zip
) { }
