package com.guideapp.backend.repository;

import com.guideapp.backend.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
}
