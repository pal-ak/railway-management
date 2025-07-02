package com.railway.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BookingResponse {
    private Long bookingId;
    private String trainName;
    private String source;
    private String destination;
    private String bookedAt;
}
