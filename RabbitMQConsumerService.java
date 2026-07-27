package com.poojitha.ticketbooking.service;

import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {

    private final RabbitMQPublisherService publisherService;
    private final BookingService bookingService;

    public RabbitMQConsumerService(
            RabbitMQPublisherService publisherService,
            BookingService bookingService) {

        this.publisherService = publisherService;
        this.bookingService = bookingService;

    }

    public String processNextMessage() {

        String message =
                publisherService.nextMessage();

        if (message == null) {
            return "Queue Empty";
        }

        String[] data =
                message.split("\\|");

        Long eventId =
                Long.parseLong(data[1]);

        int seats =
                Integer.parseInt(data[2]);

        if ("BOOK".equals(data[0])) {

            return bookingService.reserveTickets(
                    eventId,
                    seats);

        }

        if ("CANCEL".equals(data[0])) {

            return bookingService.cancelBooking(
                    eventId,
                    seats);

        }

        return "Unknown Message";

    }

    public int pendingMessages() {

        return publisherService.queueSize();

    }

}
