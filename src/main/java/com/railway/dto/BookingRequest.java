package com.railway.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BookingRequest {
    private Long trainId;
    private String source;
    private String destination;
}
