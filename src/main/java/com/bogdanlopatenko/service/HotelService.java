package com.bogdanlopatenko.service;

import com.bogdanlopatenko.dto.HotelFilterDto;
import com.bogdanlopatenko.dto.amenity.AmenityRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelFullResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;

import java.util.List;

public interface HotelService {

    List<HotelShortResponseDto> getAll();

    HotelFullResponseDto getById(Long id);

    List<HotelShortResponseDto> search(HotelFilterDto filterDto);

    HotelShortResponseDto create(HotelRequestDto requestDto);

    void addAmenities(Long hotelId, List<AmenityRequestDto> amenities);

}
