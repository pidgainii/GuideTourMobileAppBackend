package com.guideapp.backend.repository;


import com.guideapp.backend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
    List<Location> findByTourId(UUID tourId);

}
