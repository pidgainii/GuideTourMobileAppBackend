package com.guideapp.backend.controller;


import com.guideapp.backend.entity.Tour;
import com.guideapp.backend.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getById(@PathVariable("id") UUID id) {
        Tour tour =  tourService.findById(id);
        return ResponseEntity.ok(tour);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tour>> search(@RequestParam("query") String searchQuery) {
        List<Tour> tours = tourService.searchTours(searchQuery);
        return ResponseEntity.ok(tours);
    }

    @GetMapping("/mytours")
    public ResponseEntity<List<Tour>> getMyTours() {
        List<Tour> tours = tourService.getMyTours();
        return ResponseEntity.ok(tours);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Tour>> getFavorites() {
        List<Tour> tours = tourService.getFavorites();
        return ResponseEntity.ok(tours);
    }
}
