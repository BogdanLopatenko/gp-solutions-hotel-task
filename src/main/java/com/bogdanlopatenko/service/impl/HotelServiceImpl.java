package com.bogdanlopatenko.service.impl;

import com.bogdanlopatenko.constant.ExceptionConstant;
import com.bogdanlopatenko.dto.HotelFilterDto;
import com.bogdanlopatenko.dto.HotelSpecification;
import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;
import com.bogdanlopatenko.entity.Amenity;
import com.bogdanlopatenko.entity.Hotel;
import com.bogdanlopatenko.exception.HotelNotFoundException;
import com.bogdanlopatenko.mapper.HotelMapper;
import com.bogdanlopatenko.repository.HotelRepository;
import com.bogdanlopatenko.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;

    private final HotelMapper hotelMapper;

    @Override
    public List<HotelShortResponseDto> getAll() {

        List<Hotel> allHotels = repository.findAll();

        return hotelMapper.toShortResponseList(allHotels);
    }

    @Override
    public HotelResponseDto getById(Long id) {

        Hotel hotelById = getHotelById(id);

        return hotelMapper.toResponseDto(hotelById);
    }

    @Override
    public List<HotelShortResponseDto> search(HotelFilterDto filterDto) {

        Specification<Hotel> hotelSpecification = HotelSpecification.withFilters(filterDto);

        List<Hotel> hotelsWithSpecification = repository.findAll(hotelSpecification);

        return hotelMapper.toShortResponseList(hotelsWithSpecification);
    }

    @Override
    public HotelShortResponseDto create(HotelRequestDto requestDto) {

        Hotel hotel = hotelMapper.toHotel(requestDto);

        Hotel savedHotel = repository.save(hotel);

        return hotelMapper.toShortResponseDto(savedHotel);
    }

    @Override
    public void addAmenities(Long hotelId, List<Amenity> amenities) {

        Hotel hotelById = getHotelById(hotelId);

        amenities.forEach(hotelById::addAmenity);

        repository.save(hotelById);
    }

    private Hotel getHotelById(Long id) {

        return repository.findById(id).orElseThrow(()
                -> new HotelNotFoundException(ExceptionConstant.HOTEL_NOT_FOUND_BY_ID + id));
    }
}
