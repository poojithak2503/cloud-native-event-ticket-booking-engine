package com.poojitha.ticketbooking.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class RabbitMQPublisherService {

    private final Queue<String> bookingQueue =
            new LinkedList<>();

    @Async
    public void publishBooking(Long eventId,
                               int seats) {

        String message =
                "BOOK|" +
                eventId +
                "|" +
                seats;

        bookingQueue.offer(message);

        System.out.println("Booking Published");
        System.out.println(message);
        System.out.println(LocalDateTime.now());
    }

    @Async
    public void publishCancellation(Long eventId,
                                    int seats) {

        String message =
                "CANCEL|" +
                eventId +
                "|" +
                seats;

        bookingQueue.offer(message);

        System.out.println("Cancellation Published");
    }

    public String nextMessage() {

        return bookingQueue.poll();

    }

    public int queueSize() {

        return bookingQueue.size();

    }

}
