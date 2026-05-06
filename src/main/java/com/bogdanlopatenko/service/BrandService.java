package com.bogdanlopatenko.service;

import com.bogdanlopatenko.dto.brand.BrandRequestDto;
import com.bogdanlopatenko.dto.brand.BrandResponseDto;

public interface BrandService {

    BrandResponseDto getById(Long id);

    BrandResponseDto create(BrandRequestDto dto);
}
