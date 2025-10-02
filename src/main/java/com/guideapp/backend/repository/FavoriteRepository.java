package com.guideapp.backend.repository;

import com.guideapp.backend.entity.Favorite;
import com.guideapp.backend.entity.Tour;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    List<Favorite> findByUserId(UUID userId);

    @Query("SELECT f FROM Favorite f JOIN FETCH f.tour WHERE f.user.id = :userId")
    List<Favorite> findByUserIdWithTour(@Param("userId") UUID userId);


    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.user.id = :userId AND f.tour.id = :tourId")
    void deleteByUserIdAndTourId(@Param("userId") UUID userId, @Param("tourId") UUID tourId);
}
