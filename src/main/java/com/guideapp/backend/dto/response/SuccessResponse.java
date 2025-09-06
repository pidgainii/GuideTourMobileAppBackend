package com.guideapp.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class SuccessResponse<T> {
    private String message;
    private int status;
    private Instant timestamp;
    private T data;
}
