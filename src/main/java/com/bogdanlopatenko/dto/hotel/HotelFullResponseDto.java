package com.bogdanlopatenko.dto.hotel;

import com.bogdanlopatenko.dto.amenity.AmenityShortResponseDto;
import com.bogdanlopatenko.dto.brand.BrandResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelFullResponseDto {

    private Long id;

    private String name;

    private String description;

    private BrandResponseDto brand;

    private String houseNumber;

    private String street;

    private String city;

    private String country;

    private String postCode;

    private String phone;

    private String email;

    private LocalTime checkIn;

    private LocalTime checkOut;

    private Set<AmenityShortResponseDto> amenities;
}
