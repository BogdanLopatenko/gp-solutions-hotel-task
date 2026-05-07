package com.bogdanlopatenko.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be null or blank")
    @Size(max = 150, message = "Name can't be longer 150 symbols")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Size(max = 20, message = "House number can't be more than 20 symbols")
    @Column(name = "house_number")
    private String houseNumber;

    @Size(max = 150, message = "Street name can't be more than 150 symbols")
    @Column(name = "street")
    private String street;

    @NotBlank(message = "City can't be null or blank")
    @Size(min = 1, max = 200, message = "City name must be in range 1-200 symbols")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Country can't be null or blank")
    @Size(min = 4, max = 200, message = "Country name must be in range 4-200 symbols")
    @Column(name = "country", nullable = false)
    private String country;

    @NotBlank(message = "Post code can't be null or blank")
    @Size(min = 5, max = 20, message = "Post code name must be in range 5-20 symbols")
    @Column(name = "post_code", nullable = false)
    private String postCode;

    @NotBlank(message = "Phone cant be null or blank")
    @Pattern(regexp = "^\\+?[0-9\\s\\-\\(\\)]{7,20}$", message = "Invalid phone number")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank(message = "Email can't be null")
    @Email(message = "Invalid email pattern")
    @Size(min = 5, max = 255, message = "Email size must be in range 5-255 symbols")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "Check in time can't be null")
    @Column(name = "check_in", nullable = false)
    private LocalTime checkIn;

    @Column(name = "check_out")
    private LocalTime checkOut;

    @OneToMany(mappedBy = "hotel", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<HotelAmenity> amenities;
}
