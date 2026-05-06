package com.bogdanlopatenko.dto.hotel_amenity;

import com.bogdanlopatenko.dto.amenity.AmenityResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelAmenityResponseDto {

    private Long id;

    private HotelResponseDto hotel;

    private AmenityResponseDto amenity;
}
