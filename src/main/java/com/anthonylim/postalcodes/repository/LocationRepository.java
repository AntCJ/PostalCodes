package com.anthonylim.postalcodes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthonylim.postalcodes.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByPostcode(String postcode);
}
