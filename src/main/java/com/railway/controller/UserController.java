package com.railway.controller;

import com.railway.dto.TrainAvailabilityResponse;
import com.railway.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
@RequiredArgsConstructor
public class UserController {

    private final TrainService trainService;

    @GetMapping("/availability")
    public ResponseEntity<List<TrainAvailabilityResponse>> getAvailability(
            @RequestParam String source,
            @RequestParam String destination
    ) {
        return ResponseEntity.ok(trainService.getTrainsBetween(source, destination));
    }
}
