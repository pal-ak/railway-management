package com.railway.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AddTrainRequest {
    private String name;
    private String source;
    private String destination;
    private int totalSeats;
}
