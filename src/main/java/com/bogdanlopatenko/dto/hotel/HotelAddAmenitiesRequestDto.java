package com.bogdanlopatenko.dto.hotel;

import com.bogdanlopatenko.dto.amenity.AmenityRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelAddAmenitiesRequestDto {

    @NotNull(message = "Amenities can't be null")
    @Length(min = 1, message = "Amenities list can't be empty")
    private Set<AmenityRequestDto> amenities;
}
