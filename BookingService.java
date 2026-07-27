package com.poojitha.ticketbooking.service;

import com.poojitha.ticketbooking.model.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final List<Event> events =
            new ArrayList<>();

    public Event createEvent(Event event) {

        events.add(event);

        return event;
    }

    public Optional<Event> findEvent(Long eventId) {

        return events.stream()
                .filter(event ->
                        event.getEventId().equals(eventId))
                .findFirst();
    }

    public List<Event> getAllEvents() {

        return new ArrayList<>(events);

    }

    public synchronized String reserveTickets(Long eventId,
                                              int seats) {

        Optional<Event> event =
                findEvent(eventId);

        if (event.isEmpty()) {
            return "Event Not Found";
        }

        boolean booked =
                event.get().reserveSeats(seats);

        if (booked) {
            return seats +
                    " seat(s) booked successfully.";
        }

        return "Seats unavailable.";
    }

    public synchronized String cancelBooking(Long eventId,
                                             int seats) {

        Optional<Event> event =
                findEvent(eventId);

        if (event.isEmpty()) {
            return "Event Not Found";
        }

        event.get().releaseSeats(seats);

        return "Booking cancelled successfully.";
    }

    public long soldOutEvents() {

        return events.stream()
                .filter(Event::isSoldOut)
                .count();
    }

    public double totalRevenue() {

        return events.stream()
                .mapToDouble(event ->
                        (event.getTotalSeats() -
                                event.getAvailableSeats())
                                * event.getTicketPrice())
                .sum();
    }

    public Optional<Event> mostPopularEvent() {

        return events.stream()
                .min(Comparator.comparingInt(
                        Event::getAvailableSeats));
    }

    public String bookingSummary() {

        StringBuilder summary =
                new StringBuilder();

        summary.append("Event Booking Summary\n");
        summary.append("-----------------------------\n");
        summary.append("Generated : ")
                .append(LocalDateTime.now())
                .append("\n");

        summary.append("Total Events : ")
                .append(events.size())
                .append("\n");

        summary.append("Sold Out Events : ")
                .append(soldOutEvents())
                .append("\n");

        summary.append("Revenue : $")
                .append(totalRevenue())
                .append("\n");

        mostPopularEvent().ifPresent(event ->
                summary.append("Most Popular : ")
                        .append(event.getEventName())
                        .append("\n"));

        return summary.toString();
    }

}
