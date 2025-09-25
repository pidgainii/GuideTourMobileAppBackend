package com.guideapp.backend.service;


import com.guideapp.backend.entity.Tour;
import com.guideapp.backend.entity.User;
import com.guideapp.backend.exception.AuthenticationException;
import com.guideapp.backend.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TourService {

    // TourService needs userService, because tours are "attached" to a user (guide)
    private final UserService userService;
    private final TourRepository tourRepository;

    public Tour findById(UUID id) {
        return tourRepository.getReferenceById(id);
    }

    public List<Tour> searchTours(String searchQuery) {
        return tourRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchQuery, searchQuery);
    }

    public List<Tour> getMyTours() {
        User guide = userService.getCurrentUser();
        return tourRepository.findByGuideId(guide.getId());
    }

    public List<Tour> getFavorites() {

        return null;
    }
}
