package com.bogdanlopatenko.mapper;

import com.bogdanlopatenko.dto.amenity.AmenityResponseDto;
import com.bogdanlopatenko.dto.amenity.AmenityShortResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelFullResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;
import com.bogdanlopatenko.entity.Amenity;
import com.bogdanlopatenko.entity.Hotel;
import com.bogdanlopatenko.entity.HotelAmenity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
        (
                componentModel = "spring",
                unmappedTargetPolicy = ReportingPolicy.ERROR,
                injectionStrategy = InjectionStrategy.CONSTRUCTOR,
                uses = {BrandMapper.class, AmenityMapper.class}
        )
public interface HotelMapper {

    HotelResponseDto toResponseDto(Hotel hotel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    Hotel toHotel(HotelRequestDto dto);

    HotelShortResponseDto toShortResponseDto(Hotel hotel);

    List<HotelShortResponseDto> toShortResponseList(List<Hotel> hotels);

    @Mapping(target = "amenities", source = "amenities")
    HotelFullResponseDto toFullResponseDto(Hotel hotel);

    default Set<AmenityShortResponseDto> mapAmenities(Set<HotelAmenity> amenities) {

        return amenities.stream()
                .map(HotelAmenity::getAmenity)
                .map(this::mapAmenity)
                .collect(Collectors.toSet());
    }

    AmenityShortResponseDto mapAmenity(Amenity amenity);

}
