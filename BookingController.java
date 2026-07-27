package com.poojitha.ticketbooking.controller;

import com.poojitha.ticketbooking.model.Event;
import com.poojitha.ticketbooking.service.BookingService;
import com.poojitha.ticketbooking.service.SeatAllocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class BookingController {

    private final BookingService bookingService;
    private final SeatAllocationService seatAllocationService;

    public BookingController(BookingService bookingService,
                             SeatAllocationService seatAllocationService) {
        this.bookingService = bookingService;
        this.seatAllocationService = seatAllocationService;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {

        return bookingService.createEvent(event);

    }

    @GetMapping
    public List<Event> getEvents() {

        return bookingService.getAllEvents();

    }

    @GetMapping("/{eventId}")
    public Optional<Event> getEvent(
            @PathVariable Long eventId) {

        return bookingService.findEvent(eventId);

    }

    @PostMapping("/{eventId}/book")
    public String reserveTickets(
            @PathVariable Long eventId,
            @RequestParam int seats) {

        return seatAllocationService.allocateSeats(
                eventId,
                seats);

    }

    @PostMapping("/{eventId}/cancel")
    public String cancelBooking(
            @PathVariable Long eventId,
            @RequestParam int seats) {

        return bookingService.cancelBooking(
                eventId,
                seats);

    }

    @GetMapping("/summary")
    public String bookingSummary() {

        return bookingService.bookingSummary();

    }

}
