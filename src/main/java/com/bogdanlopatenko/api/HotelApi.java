package com.bogdanlopatenko.api;

import com.bogdanlopatenko.dto.HotelFilterDto;
import com.bogdanlopatenko.dto.amenity.AmenityRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelFullResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Hotels", description = "Operations for managing hotels")
public interface HotelApi {

    @Operation(summary = "Get all hotels", description = "Get all hotels in a short form.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotels retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<HotelShortResponseDto> getAll();

    @Operation(summary = "Get hotel by ID", description = "Get hotel with amenities by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotel retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Hotel not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    HotelFullResponseDto getById(@PathVariable @Positive(message = "Hotel ID cant be negative or zero") Long id);

    @Operation(summary = "Search hotels", description = "Search hotels by filter criteria.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotels retrieved successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameter."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<HotelShortResponseDto> search(@RequestBody @Valid HotelFilterDto filterDto);

    @Operation(summary = "Create hotel", description = "Create a new hotel.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hotel created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request data."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    HotelShortResponseDto create(@RequestBody @Valid HotelRequestDto requestDto);

    @Operation(summary = "Add amenities to a hotel", description = "Add amenities list to a hotel using its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Amenities added successfully."),
            @ApiResponse(responseCode = "400", description = "Hotel not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    void addAmenities(@PathVariable @Positive(message = "Hotel ID cant be negative or zero") Long id, @RequestBody @Valid List<AmenityRequestDto> amenities);
}
