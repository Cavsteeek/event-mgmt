package com.em.event_management.service;

import com.em.event_management.model.Event;

public interface EventService {
    Event findEventByName(String EventName);

    void deleteEvent(Long EventId);
}
