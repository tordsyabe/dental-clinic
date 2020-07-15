package com.johnllave.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    private String uuid;
}
