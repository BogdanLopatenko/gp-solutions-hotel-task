package com.bogdanlopatenko.service;

import com.bogdanlopatenko.enums.HistogramParam;

import java.util.Map;

public interface HistogramService {

    Map<String, Long> getHistogram(HistogramParam param);
}
