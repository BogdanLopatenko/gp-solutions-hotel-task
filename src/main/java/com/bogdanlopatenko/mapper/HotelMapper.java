package com.bogdanlopatenko.mapper;

import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;
import com.bogdanlopatenko.entity.Hotel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper
        (
                componentModel = "spring",
                unmappedTargetPolicy = ReportingPolicy.ERROR,
                injectionStrategy = InjectionStrategy.CONSTRUCTOR,
                uses = BrandMapper.class
        )
public interface HotelMapper {

    HotelResponseDto toResponseDto(Hotel hotel);

    @Mapping(target = "id", ignore = true)
    Hotel toHotel(HotelRequestDto dto);

    HotelShortResponseDto toShortResponseDto(Hotel hotel);

    List<HotelShortResponseDto> toShortResponseList(List<Hotel> hotels);

}
