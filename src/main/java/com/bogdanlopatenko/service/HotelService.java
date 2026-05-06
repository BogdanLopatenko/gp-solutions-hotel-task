package com.bogdanlopatenko.service;

import com.bogdanlopatenko.dto.HotelFilterDto;
import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;
import com.bogdanlopatenko.entity.Amenity;

import java.util.List;

public interface HotelService {

    List<HotelShortResponseDto> getAll();

    HotelResponseDto getById(Long id);

    List<HotelShortResponseDto> search(HotelFilterDto filterDto);

    HotelShortResponseDto create(HotelRequestDto requestDto);

    void addAmenities(Long hotelId, List<Amenity> amenities);


}
