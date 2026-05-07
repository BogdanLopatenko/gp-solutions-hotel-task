package com.bogdanlopatenko.mapper;

import com.bogdanlopatenko.dto.amenity.AmenityRequestDto;
import com.bogdanlopatenko.dto.amenity.AmenityResponseDto;
import com.bogdanlopatenko.dto.amenity.AmenityShortResponseDto;
import com.bogdanlopatenko.entity.Amenity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper
        (
                componentModel = "spring",
                unmappedTargetPolicy = ReportingPolicy.ERROR,
                injectionStrategy = InjectionStrategy.CONSTRUCTOR
        )
public interface AmenityMapper {

     AmenityShortResponseDto toShortResponseDto(Amenity amenity);

     AmenityResponseDto toResponseDto(Amenity amenity);

     List<AmenityResponseDto> toResponseDtoList(List<Amenity> amenities);

     @Mapping(target = "id", ignore = true)
     @Mapping(target = "hotels", ignore = true)
     Amenity toAmenity(AmenityRequestDto dto);

     List<Amenity> toAmenityList(List<AmenityRequestDto> dtoList);

}
