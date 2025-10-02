package com.guideapp.backend.repository;

import com.guideapp.backend.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TourRepository extends JpaRepository<Tour, UUID> {
    List<Tour> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String query1, String query2);
    List<Tour> findByGuideId(UUID id);
    @Query("SELECT t FROM Tour t WHERE t.id NOT IN " +
            "(SELECT f.tour.id FROM Favorite f WHERE f.user.id = :userId)")
    List<Tour> findAllExcludingAcquired(@Param("userId") UUID userId);
}
