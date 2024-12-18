package com.citronix.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmDTO {

    private Long id;
    private String name;
    private String address;
    private Double surface;
    private List<FieldDTO> fields;

}
