package com.guideapp.backend.dto.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateLocationRequest {
    private String name;
    private String latitude;
    private String longitude;
    private String description;
    private String category;
    private List<String> mediaUrls;
}