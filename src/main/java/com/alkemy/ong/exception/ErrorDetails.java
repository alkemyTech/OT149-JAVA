package com.alkemy.ong.exception;

import com.alkemy.ong.enumeration.ApplicationErrorCode;
import com.alkemy.ong.enumeration.Location;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "description", "field", "value", "location"})
public class ErrorDetails {

    /**
     * The unique and fine-grained application-level error code.
     */
    @NotNull
    private ApplicationErrorCode code;

    /**
     * The human-readable description for an issue. Provide non-standard more granular error message.
     */
    @NotNull
    private String description;

    /**
     * The field that caused the error. If the field is in the body, set this value to the JSON pointer to that field.
     * Example: "field": {"$ref": "#/body-field"}
     */
    private String field;

    /**
     * The value of the field that caused the error.
     */
    private Object value;

    /**
     * The location of the field that caused the error. Value is `BODY`, `PATH`, `QUERY` or `HEADER`.
     */
    private Location location;

}
