package com.railway.service;

import com.railway.dto.AddTrainRequest;
import com.railway.dto.TrainAvailabilityResponse;
import com.railway.model.Train;
import com.railway.repository.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainService {

    private final TrainRepository repo;

    public void addTrain(AddTrainRequest request) {
        Train train = Train.builder()
                .name(request.getName())
                .source(request.getSource())
                .destination(request.getDestination())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getTotalSeats())
                .build();

        repo.save(train);
    }
    public List<TrainAvailabilityResponse> getTrainsBetween(String source, String destination) {
        return repo.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination)
                .stream()
                .map(t -> TrainAvailabilityResponse.builder()
                        .trainId(t.getId())
                        .name(t.getName())
                        .source(t.getSource())
                        .destination(t.getDestination())
                        .availableSeats(t.getAvailableSeats())
                        .build())
                .toList();
    }
}
