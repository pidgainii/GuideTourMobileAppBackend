package com.guideapp.backend.dto.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTourRequest {
    private String title;
    private String description;
    private String country;
    private String thumbnail;
    private float rating_avg;
    private Integer rating_n;

    private List<CreateLocationRequest> locations;
}