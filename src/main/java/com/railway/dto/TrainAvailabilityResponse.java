package com.railway.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TrainAvailabilityResponse {
    private Long trainId;
    private String name;
    private String source;
    private String destination;
    private int availableSeats;
}
