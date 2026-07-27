package com.poojitha.ticketbooking.service;

import com.poojitha.ticketbooking.model.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SeatAllocationService {

    private final BookingService bookingService;

    private final ConcurrentHashMap<Long, Object> locks =
            new ConcurrentHashMap<>();

    public SeatAllocationService(
            BookingService bookingService) {

        this.bookingService = bookingService;

    }

    public String allocateSeats(Long eventId,
                                int seats) {

        Object lock =
                locks.computeIfAbsent(
                        eventId,
                        id -> new Object());

        synchronized (lock) {

            Optional<Event> event =
                    bookingService.findEvent(eventId);

            if (event.isEmpty()) {
                return "Event Not Found";
            }

            if (!event.get().isBookingOpen()) {
                return "Booking Closed";
            }

            String result =
                    bookingService.reserveTickets(
                            eventId,
                            seats);

            System.out.println(
                    "Seat Allocation : "
                            + LocalDateTime.now());

            return result;
        }
    }

    public int availableSeats(Long eventId) {

        return bookingService.findEvent(eventId)
                .map(Event::getAvailableSeats)
                .orElse(0);

    }

    public boolean soldOut(Long eventId) {

        return bookingService.findEvent(eventId)
                .map(Event::isSoldOut)
                .orElse(false);

    }

}
