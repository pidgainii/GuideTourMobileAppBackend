package com.guideapp.backend.repository;

import com.guideapp.backend.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TourRepository extends JpaRepository<Tour, UUID> {

}
