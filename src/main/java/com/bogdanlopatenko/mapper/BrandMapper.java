package com.bogdanlopatenko.mapper;

import com.bogdanlopatenko.dto.brand.BrandRequestDto;
import com.bogdanlopatenko.dto.brand.BrandResponseDto;
import com.bogdanlopatenko.dto.brand.BrandShortResponseDto;
import com.bogdanlopatenko.entity.Brand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper
        (
                componentModel = "spring",
                unmappedTargetPolicy = ReportingPolicy.ERROR,
                injectionStrategy = InjectionStrategy.CONSTRUCTOR
        )
public interface BrandMapper {

    BrandResponseDto toResponseDto(Brand brand);

    @Mapping(target = "id", ignore = true)
    Brand toBrand(BrandRequestDto requestDto);

    BrandShortResponseDto toShortResponseDto(Brand brand);
}
