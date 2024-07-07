package org.example.shared.eventsourcing.data;

public record Event<EVENT, STATE>(
        String entityId,
        String eventId,
        EVENT event,
        STATE state
) { }
