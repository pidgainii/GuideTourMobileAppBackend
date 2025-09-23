package com.guideapp.backend.controller;


import com.guideapp.backend.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;





}
