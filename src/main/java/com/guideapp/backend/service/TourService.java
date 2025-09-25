package com.guideapp.backend.service;


import com.guideapp.backend.dto.request.CreateLocationRequest;
import com.guideapp.backend.dto.request.CreateTourRequest;
import com.guideapp.backend.dto.response.SuccessResponse;
import com.guideapp.backend.entity.Location;
import com.guideapp.backend.entity.Tour;
import com.guideapp.backend.entity.User;
import com.guideapp.backend.exception.AuthenticationException;
import com.guideapp.backend.repository.LocationRepository;
import com.guideapp.backend.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.Point;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TourService {

    // TourService needs userService, because tours are "attached" to a user (guide)
    private final UserService userService;
    private final TourRepository tourRepository;
    private final LocationRepository locationRepository;

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

    public List<Tour> getAcquired() {
        return null;
    }

    public SuccessResponse createTour(CreateTourRequest request) {

        Tour tour = new Tour();
        tour.setGuide(userService.getCurrentUser());
        tour.setTitle(request.getTitle());
        tour.setDescription(request.getDescription());
        tour.setCountry(request.getCountry());
        tour.setThumbnailUrl(request.getThumbnail());
        tour.setRatingAvg((request.getRating_avg()));
        tour.setViews((request.getRating_n()));
        tour.setCreatedAt(new Date());

        tourRepository.save(tour);

        Integer locationIndex = 0;

        List<CreateLocationRequest> locations = request.getLocations();
        for (CreateLocationRequest lReq: locations) {
            Location location = new Location();
            location.setTour(tour);
            location.setName(lReq.getName());

            // --- CREATING THE COORDINATES IN PROPER FORMAT ---
            Coordinate[] coordinates = new Coordinate[]{new Coordinate(lReq.getLongitude(), lReq.getLatitude())};
            CoordinateSequence coordinateSequence = new CoordinateArraySequence(coordinates);
            GeometryFactory geometryFactory = new GeometryFactory();
            Point point = geometryFactory.createPoint(coordinateSequence);

            location.setCoordinates(point);
            location.setDescription(lReq.getDescription());
            location.setCategory(lReq.getCategory());
            location.setMediaUrls(lReq.getMediaUrls());
            location.setOrderIndex(locationIndex);
            locationIndex++;

            location.setCreatedAt(new Date());

            locationRepository.save(location);
        }

        return new SuccessResponse("SUCCESS", Instant.now());
    }
}
