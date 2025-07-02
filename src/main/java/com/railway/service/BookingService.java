package com.railway.service;

import com.railway.dto.*;
import com.railway.model.*;
import com.railway.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final TrainRepository trainRepo;
    private final BookingRepository bookingRepo;

    @Transactional
    public BookingResponse bookSeat(BookingRequest request) {
        Train train = trainRepo.findTrainByIdForUpdate(request.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        if (train.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available");
        }

        // Decrement seat and save train
        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepo.save(train);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Booking booking = Booking.builder()
                .username(username)
                .train(train)
                .source(request.getSource())
                .destination(request.getDestination())
                .bookedAt(LocalDateTime.now())
                .build();

        bookingRepo.save(booking);

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .trainName(train.getName())
                .source(booking.getSource())
                .destination(booking.getDestination())
                .bookedAt(booking.getBookedAt().toString())
                .build();
    }

    public BookingResponse getBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .trainName(booking.getTrain().getName())
                .source(booking.getSource())
                .destination(booking.getDestination())
                .bookedAt(booking.getBookedAt().toString())
                .build();
    }
}
