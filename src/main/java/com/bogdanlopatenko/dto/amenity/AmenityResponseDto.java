package com.bogdanlopatenko.dto.amenity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityResponseDto {

    private Long id;

    private String name;
}
