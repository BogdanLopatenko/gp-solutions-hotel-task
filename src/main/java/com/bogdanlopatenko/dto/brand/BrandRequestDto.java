package com.bogdanlopatenko.dto.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequestDto {

    @NotBlank(message = "Name can't be null or blank")
    @Size(max = 70, message = "Size must be max 70 symbols")
    private String name;
}
