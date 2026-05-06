package com.bogdanlopatenko.dto.amenity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityRequestDto {

    @NotBlank(message = "Name can't be null or blank")
    @Size(min = 2, max = 150, message = "Size must be in range 2-150 symbols")
    private String name;
}
