package com.bogdanlopatenko.controller;

import com.bogdanlopatenko.dto.HotelFilterDto;
import com.bogdanlopatenko.dto.amenity.AmenityRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelFullResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;
import com.bogdanlopatenko.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;


    @GetMapping
    public List<HotelShortResponseDto> getAll() {

        return hotelService.getAll();
    }

    @GetMapping("/{id}")
    public HotelFullResponseDto getById(@PathVariable Long id) {

        return hotelService.getById(id);
    }

    @PostMapping("/search")
    public List<HotelShortResponseDto> search(@RequestBody HotelFilterDto filterDto) {

        return hotelService.search(filterDto);
    }

    @PostMapping("/create")
    public HotelShortResponseDto create(@RequestBody HotelRequestDto requestDto) {

        return hotelService.create(requestDto);
    }

    @PostMapping("/{id}")
    public void addAmenities(@PathVariable Long id, @RequestBody List<AmenityRequestDto> amenities) {

        hotelService.addAmenities(id, amenities);
    }


}
