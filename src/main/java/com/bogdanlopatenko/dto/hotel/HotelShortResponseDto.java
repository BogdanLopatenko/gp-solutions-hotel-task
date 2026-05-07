package com.bogdanlopatenko.dto.hotel;

import com.bogdanlopatenko.dto.brand.BrandShortResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelShortResponseDto {

    private Long id;

    private String name;

    private String description;

    private BrandShortResponseDto brand;

    private String address;

    private String phone;
}
