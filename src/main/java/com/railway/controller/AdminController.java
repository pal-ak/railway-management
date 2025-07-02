package com.railway.controller;

import com.railway.dto.AddTrainRequest;
import com.railway.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final TrainService trainService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-train")
    public ResponseEntity<String> addTrain(@RequestBody AddTrainRequest request) {
        trainService.addTrain(request);
        return ResponseEntity.ok("Train added successfully");
    }
}
