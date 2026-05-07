package com.bogdanlopatenko.controller;

import com.bogdanlopatenko.api.HistogramApi;
import com.bogdanlopatenko.enums.HistogramParam;
import com.bogdanlopatenko.service.HistogramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/property-view/histogram")
@RequiredArgsConstructor
public class HistogramController implements HistogramApi {

    private final HistogramService histogramService;

    @GetMapping("/{param}")
    public Map<String, Long> createHotelHistogram(@PathVariable HistogramParam param) {

        return histogramService.createHistogram(param);
    }
}
