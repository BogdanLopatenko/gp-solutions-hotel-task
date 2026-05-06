package com.bogdanlopatenko.dto;


import com.bogdanlopatenko.entity.Amenity;
import com.bogdanlopatenko.entity.Hotel;
import com.bogdanlopatenko.entity.HotelAmenity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {

    public static Specification<Hotel> withFilters(HotelFilterDto filter) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter == null) {
                return cb.conjunction();
            }

            if (filter.getName() != null && !filter.getName().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("name" )),
                                "%" + filter.getName().toLowerCase() + "%"
                        )
                );
            }


            if (filter.getCity() != null && !filter.getCity().isBlank()) {
                predicates.add(
                        cb.equal(
                                cb.lower(root.get("city" )),
                                filter.getCity().toLowerCase()
                        )
                );
            }

            if (filter.getCountry() != null && !filter.getCountry().isBlank()) {
                predicates.add(
                        cb.equal(
                                cb.lower(root.get("country" )),
                                filter.getCountry().toLowerCase()
                        )
                );
            }


            if (filter.getBrand() != null &&
                    filter.getBrand().getName() != null &&
                    !filter.getBrand().getName().isBlank()) {

                Join<Hotel, ?> brandJoin = root.join("brand" );

                predicates.add(
                        cb.equal(
                                cb.lower(brandJoin.get("name" )),
                                filter.getBrand().getName().toLowerCase()
                        )
                );
            }

            if (filter.getAmenities() != null && !filter.getAmenities().isEmpty()) {

                Join<Hotel, HotelAmenity> haJoin = root.join("amenities" );
                Join<HotelAmenity, Amenity> amenityJoin = haJoin.join("amenity" );

                CriteriaBuilder.In<String> inClause =
                        cb.in(cb.lower(amenityJoin.get("name" )));

                for (String name : filter.getAmenities()) {
                    inClause.value(name.toLowerCase());
                }

                predicates.add(inClause);

                query.distinct(true);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
