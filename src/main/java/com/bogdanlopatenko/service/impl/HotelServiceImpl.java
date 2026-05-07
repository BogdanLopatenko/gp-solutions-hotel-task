package com.bogdanlopatenko.service.impl;

import com.bogdanlopatenko.constant.ExceptionConstant;
import com.bogdanlopatenko.dto.HotelFilterDto;
import com.bogdanlopatenko.dto.HotelSpecification;
import com.bogdanlopatenko.dto.amenity.AmenityRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelFullResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;
import com.bogdanlopatenko.entity.Amenity;
import com.bogdanlopatenko.entity.Brand;
import com.bogdanlopatenko.entity.Hotel;
import com.bogdanlopatenko.entity.HotelAmenity;
import com.bogdanlopatenko.enums.ResponseStatus;
import com.bogdanlopatenko.exception.AmenityNotFoundException;
import com.bogdanlopatenko.exception.BrandNotFoundException;
import com.bogdanlopatenko.exception.HotelNotFoundException;
import com.bogdanlopatenko.mapper.HotelMapper;
import com.bogdanlopatenko.repository.AmenityRepository;
import com.bogdanlopatenko.repository.BrandRepository;
import com.bogdanlopatenko.repository.HotelRepository;
import com.bogdanlopatenko.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final AmenityRepository amenityRepository;

    private final BrandRepository brandRepository;

    private final HotelRepository hotelRepository;

    private final HotelMapper hotelMapper;

    @Override
    public List<HotelShortResponseDto> getAll() {

        List<Hotel> allHotels = hotelRepository.findAll();

        return hotelMapper.toShortResponseList(allHotels);
    }

    @Override
    public HotelFullResponseDto getById(Long id) {

        Hotel hotelById = getHotelById(id);

        return hotelMapper.toFullResponseDto(hotelById);
    }

    @Override
    public List<HotelShortResponseDto> search(HotelFilterDto filterDto) {

        Specification<Hotel> hotelSpecification = HotelSpecification.withFilters(filterDto);

        List<Hotel> hotelsWithSpecification = hotelRepository.findAll(hotelSpecification);

        return hotelMapper.toShortResponseList(hotelsWithSpecification);
    }

    @Override
    public HotelShortResponseDto create(HotelRequestDto requestDto) {

        Brand brandByName = getBrandByName(requestDto.getBrand().getName());

        Hotel hotel = hotelMapper.toHotel(requestDto);

        hotel.setBrand(brandByName);

        Hotel savedHotel = hotelRepository.save(hotel);

        return hotelMapper.toShortResponseDto(savedHotel);
    }

    @Override
    public void addAmenities(Long hotelId, List<AmenityRequestDto> amenitiesDto) {

        Hotel hotelById = getHotelById(hotelId);

        Set<String> amenitiesNames = amenitiesDto.stream()
                .map(AmenityRequestDto::getName)
                .collect(Collectors.toSet());

        List<Amenity> allAmenitiesByNames = amenityRepository.findAllByNameIn(amenitiesNames);

        if (allAmenitiesByNames.isEmpty()){

            throw new AmenityNotFoundException(ExceptionConstant.EMPTY_AMENITY_LIST, ResponseStatus.NOT_FOUND.name());
        }

        allAmenitiesByNames
                .forEach(amenity -> {

                    HotelAmenity hotelAmenity = new HotelAmenity();
                    hotelAmenity.setHotel(hotelById);
                    hotelAmenity.setAmenity(amenity);

                    hotelById.getAmenities().add(hotelAmenity);
                });

        hotelRepository.save(hotelById);
    }

    private Hotel getHotelById(Long id) {

        return hotelRepository.findById(id).orElseThrow(()
                -> new HotelNotFoundException(ExceptionConstant.HOTEL_NOT_FOUND_BY_ID + id, ResponseStatus.NOT_FOUND.name()));
    }

    private Brand getBrandByName(String brandName) {

        return brandRepository.findByName(brandName).orElseThrow(()
                -> new BrandNotFoundException(ExceptionConstant.BRAND_NOT_FOUND_BY_NAME + brandName, ResponseStatus.NOT_FOUND.name()));
    }
}
