package com.poojitha.ticketbooking.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {

    private Long eventId;
    private String eventName;
    private String venue;
    private LocalDateTime eventDate;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private String organizer;
    private boolean bookingOpen;
    private LocalDateTime createdDate;

    public Event() {
    }

    public Event(Long eventId,
                 String eventName,
                 String venue,
                 LocalDateTime eventDate,
                 int totalSeats,
                 double ticketPrice,
                 String organizer) {

        this.eventId = eventId;
        this.eventName = eventName;
        this.venue = venue;
        this.eventDate = eventDate;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.organizer = organizer;
        this.bookingOpen = true;
        this.createdDate = LocalDateTime.now();
    }

    public synchronized boolean reserveSeats(int seats) {

        if (!bookingOpen) {
            return false;
        }

        if (availableSeats < seats) {
            return false;
        }

        availableSeats -= seats;

        if (availableSeats == 0) {
            bookingOpen = false;
        }

        return true;
    }

    public synchronized void releaseSeats(int seats) {

        availableSeats += seats;

        if (availableSeats > totalSeats) {
            availableSeats = totalSeats;
        }

        bookingOpen = true;
    }

    public boolean isSoldOut() {
        return availableSeats == 0;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public boolean isBookingOpen() {
        return bookingOpen;
    }

    public void setBookingOpen(boolean bookingOpen) {
        this.bookingOpen = bookingOpen;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Event)) {
            return false;
        }

        Event event = (Event) object;

        return Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }

}
