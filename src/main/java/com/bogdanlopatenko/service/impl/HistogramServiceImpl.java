package com.bogdanlopatenko.service.impl;

import com.bogdanlopatenko.enums.HistogramParam;
import com.bogdanlopatenko.repository.HotelAmenityRepository;
import com.bogdanlopatenko.repository.HotelRepository;
import com.bogdanlopatenko.service.HistogramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistogramServiceImpl implements HistogramService {

    private final HotelRepository hotelRepository;

    private final HotelAmenityRepository hotelAmenityRepository;

    @Override
    public Map<String, Long> getHistogram(HistogramParam param) {

        return switch (param){
            case CITY -> toMap(hotelRepository.groupByCity());
            case COUNTRY -> toMap(hotelRepository.groupByCountry());
            case BRAND -> toMap(hotelRepository.groupByBrand());
            case AMENITIES -> toMap(hotelAmenityRepository.groupByAmenities());
        };
    }

    private Map<String, Long> toMap(List<Object[]> raw) {

        return raw.stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0],
                        r -> (Long) r[1]
                ));
    }
}
