package com.johnllave.dentalclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TeethDto {

    private String id;
    private String description;
    private String category;
    private Integer number;
}
