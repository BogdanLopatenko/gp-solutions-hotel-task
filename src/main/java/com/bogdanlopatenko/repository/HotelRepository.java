package com.bogdanlopatenko.repository;

import com.bogdanlopatenko.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

    @Query("select h.city, count(h) from Hotel h group by h.city")
    List<Object[]> groupByCity();

    @Query("select h.country, count(h) from Hotel h group by h.country")
    List<Object[]> groupByCountry();

    @Query("select h.brand.name, count(h) from Hotel h group by h.brand.name")
    List<Object[]> groupByBrand();


}
