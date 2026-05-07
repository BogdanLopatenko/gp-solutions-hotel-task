package com.bogdanlopatenko.dto.hotel;

import com.bogdanlopatenko.dto.brand.BrandRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDto {

    @NotBlank(message = "Name can't be null or blank")
    @Size(max = 150, message = "Name can't be longer 150 symbols")
    private String name;

    private String description;

    @NotNull(message = "Brand can't be null")
    private BrandRequestDto brand;

    @Size(max = 20, message = "House number can't be more than 20 symbols")
    private String houseNumber;

    @Size(max = 150, message = "Street name can't be more than 150 symbols")
    private String street;

    @NotBlank(message = "City can't be null or blank")
    @Size(min = 1, max = 200, message = "City name must be in range 1-200 symbols")
    private String city;

    @NotBlank(message = "Country can't be null or blank")
    @Size(min = 4, max = 200, message = "Country name must be in range 4-200 symbols")
    private String country;

    @NotBlank(message = "Post code can't be null or blank")
    @Size(min = 5, max = 20, message = "Post code name must be in range 5-20 symbols")
    private String postCode;

    @NotBlank(message = "Phone cant be null or blank")
    @Pattern(regexp = "^\\+?[0-9\\s\\-\\(\\)]{7,20}$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Email can't be null")
    @Email(message = "Invalid email pattern")
    @Size(min = 5, max = 255, message = "Email size must be in range 5-255 symbols")
    private String email;

    @NotNull(message = "Check in time can't be null")
    @Schema(type = "string", example = "12:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime checkIn;

    @Schema(type = "string", example = "12:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime checkOut;
}
