package com.bogdanlopatenko.service.impl;

import com.bogdanlopatenko.constant.ExceptionConstant;
import com.bogdanlopatenko.dto.brand.BrandRequestDto;
import com.bogdanlopatenko.dto.brand.BrandResponseDto;
import com.bogdanlopatenko.entity.Brand;
import com.bogdanlopatenko.exception.BrandNotFoundException;
import com.bogdanlopatenko.mapper.BrandMapper;
import com.bogdanlopatenko.repository.BrandRepository;
import com.bogdanlopatenko.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    private final BrandMapper mapper;


    @Override
    public BrandResponseDto getById(Long id) {

        Brand brandById = repository.findById(id).orElseThrow(() ->
                new BrandNotFoundException(ExceptionConstant.BRAND_NOT_FOUND_BY_ID + id));

        return mapper.toResponseDto(brandById);
    }

    @Override
    public BrandResponseDto create(BrandRequestDto dto) {

        Brand brand = mapper.toBrand(dto);

        Brand savedBrand = repository.save(brand);

        return mapper.toResponseDto(savedBrand);
    }
}
