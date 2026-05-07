package com.bogdanlopatenko.repository;

import com.bogdanlopatenko.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findAllByNameIn(Set<String> names);
}
