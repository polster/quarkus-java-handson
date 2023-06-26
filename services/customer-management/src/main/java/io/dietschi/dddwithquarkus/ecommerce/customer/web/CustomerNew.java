package io.dietschi.dddwithquarkus.ecommerce.customer.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record CustomerNew(
        @JsonProperty("first_name")
        @Schema(
                example = "Silke"
        )
        String firstName,
        @JsonProperty("last_name")
        @Schema(
                example = "Nötzli"
        )
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
