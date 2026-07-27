package com.poojitha.ticketbooking.repository;

import com.poojitha.ticketbooking.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventRepository {

    private final ConcurrentHashMap<Long, Event> database =
            new ConcurrentHashMap<>();

    public Event save(Event event) {

        database.put(
                event.getEventId(),
                event);

        return event;
    }

    public Optional<Event> findById(Long eventId) {

        return Optional.ofNullable(
                database.get(eventId));

    }

    public List<Event> findAll() {

        return new ArrayList<>(
                database.values());

    }

    public void delete(Long eventId) {

        database.remove(eventId);

    }

    public long totalEvents() {

        return database.size();

    }

    public long soldOutEvents() {

        return database.values()
                .stream()
                .filter(Event::isSoldOut)
                .count();

    }

    public Optional<Event> highestSellingEvent() {

        return database.values()
                .stream()
                .min(Comparator.comparingInt(
                        Event::getAvailableSeats));

    }

    public List<Event> organizerEvents(
            String organizer) {

        return database.values()
                .stream()
                .filter(event ->
                        event.getOrganizer()
                                .equalsIgnoreCase(organizer))
                .toList();

    }

}
