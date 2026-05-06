package com.bogdanlopatenko.dto;

import com.bogdanlopatenko.dto.brand.BrandRequestDto;
import lombok.Data;

import java.util.Set;

@Data
public class HotelFilterDto {

    private String name;

    private BrandRequestDto brand;

    private String city;

    private String country;

    private Set<String> amenities;
}
