package io.dietschi.dddwithquarkus.ecommerce.shipments.access;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record Customer(
        String userId,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @Schema(
                description = "The customer`s domicile address.",
                required = true
        )
        Address domicileAddress,
        @Schema(
                description = "The customer`s billing address (Not needed in case it is the same as the domicile)."
        )
        Address billingAddress
) { }
