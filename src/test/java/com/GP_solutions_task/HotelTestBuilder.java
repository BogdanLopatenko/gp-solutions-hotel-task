package com.GP_solutions_task;

import com.bogdanlopatenko.entity.Brand;
import com.bogdanlopatenko.entity.Hotel;
import com.bogdanlopatenko.entity.HotelAmenity;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class HotelTestBuilder {

    private Long id = 1L;
    private String name = "Trump Tower";
    private String description = "Some description";
    private Brand brand = new Brand(1L, "Trump");
    private String houseNumber = "12";
    private String street = "Some street";
    private String city = "New-York";
    private String country = "USA";
    private String postCode = "00050";
    private String phone = "+375256546565";
    private String email = "sometrump@email.com";
    private LocalTime checkIn = LocalTime.of(12, 0);
    private LocalTime checkOut = LocalTime.of(14, 0);

    private Set<HotelAmenity> hotelAmenity = new HashSet<>();

    public HotelTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public HotelTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public HotelTestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public HotelTestBuilder withBrand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public HotelTestBuilder withHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public HotelTestBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public HotelTestBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public HotelTestBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public HotelTestBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public HotelTestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public HotelTestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public HotelTestBuilder withCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public HotelTestBuilder withCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public HotelTestBuilder withAmenities(Set<HotelAmenity> hotelAmenities) {
        this.hotelAmenity = hotelAmenities;
        return this;
    }

    public Hotel build() {
        return new Hotel(
                id,
                name,
                description,
                brand,
                houseNumber,
                street,
                city,
                country,
                postCode,
                phone,
                email,
                checkIn,
                checkOut,
                hotelAmenity
        );
    }
}
