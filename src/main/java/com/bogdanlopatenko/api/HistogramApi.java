package com.bogdanlopatenko.api;

import com.bogdanlopatenko.enums.HistogramParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Tag(name = "Histogram", description = "Hotel histograms")
public interface HistogramApi {

    @Operation(summary = "Create histogram by param", description = "Create hotel histogram using param.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Histogram was created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request param."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    Map<String, Long> createHotelHistogram(@PathVariable HistogramParam param);
}
