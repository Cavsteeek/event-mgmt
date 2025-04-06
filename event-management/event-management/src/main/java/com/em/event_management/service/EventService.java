package com.em.event_management.service;

import com.em.event_management.dto.NewEventDto;
import com.em.event_management.model.Event;

public interface EventService {
    Event findEventByName(String EventName);

    Event newEvent (NewEventDto event);

    void deleteEvent(Long EventId);
}
