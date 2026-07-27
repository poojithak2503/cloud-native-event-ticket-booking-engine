package com.poojitha.ticketbooking.service;

import com.poojitha.ticketbooking.model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class BookingServiceTest {

    private BookingService bookingService;

    @BeforeEach
    void setup() {

        bookingService =
                Mockito.spy(new BookingService());

    }

    @Test
    void shouldCreateEvent() {

        Event event =
                new Event(
                        1L,
                        "Music Festival",
                        "New York",
                        LocalDateTime.now().plusDays(20),
                        500,
                        150.0,
                        "Live Nation"
                );

        bookingService.createEvent(event);

        Assertions.assertEquals(
                1,
                bookingService.getAllEvents().size());

    }

    @Test
    void shouldReserveSeats() {

        Event event =
                new Event(
                        2L,
                        "Tech Conference",
                        "Chicago",
                        LocalDateTime.now().plusDays(30),
                        200,
                        300.0,
                        "Open Tech"
                );

        bookingService.createEvent(event);

        bookingService.reserveTickets(
                2L,
                20);

        Assertions.assertEquals(
                180,
                bookingService.findEvent(2L)
                        .get()
                        .getAvailableSeats());

    }

    @Test
    void shouldCancelBooking() {

        Event event =
                new Event(
                        3L,
                        "Cricket Match",
                        "Dallas",
                        LocalDateTime.now().plusDays(15),
                        100,
                        75.0,
                        "Sports India"
                );

        bookingService.createEvent(event);

        bookingService.reserveTickets(3L, 40);

        bookingService.cancelBooking(3L, 20);

        Assertions.assertEquals(
                80,
                bookingService.findEvent(3L)
                        .get()
                        .getAvailableSeats());

    }

    @Test
    void shouldGenerateSummary() {

        Mockito.doCallRealMethod()
                .when(bookingService)
                .bookingSummary();

        Assertions.assertNotNull(
                bookingService.bookingSummary());

    }

}
