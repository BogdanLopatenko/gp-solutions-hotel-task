package com.bogdanlopatenko.repository;

import com.bogdanlopatenko.entity.HotelAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, Long> {

    @Query("select ha.amenity.name, count(distinct ha.hotel) from HotelAmenity ha group by ha.amenity.name")
    List<Object[]> groupByAmenities();
}
