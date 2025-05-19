package com.em.event_management.service.serviceimpl;

import com.em.event_management.dto.NewEventDto;
import com.em.event_management.model.Event;
import com.em.event_management.repository.EventRepository;
import com.em.event_management.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public Event findEventById(Long eventID) {
        return eventRepository.findById(eventID)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventID));
    }

    @Override
    public Event findEventByName(String eventName) {
        return eventRepository.findByEventName(eventName)
                .orElseThrow(() -> new RuntimeException("Event not found with name: " + eventName));
    }


    @Override
    public Event newEvent(NewEventDto event) {
        try {
            Event newEvent = new Event();
            newEvent.setEventName(event.getEventName());
            newEvent.setEventDescription(event.getEventDescription());
            newEvent.setEventDate(event.getEventDate());
            newEvent.setEventVenue(event.getEventVenue());
            return eventRepository.save(newEvent);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating new event", e);
        }
    }

    @Override
    public void deleteEvent(Long EventId) {
        if (eventRepository.existsById(EventId)) {
            eventRepository.deleteById(EventId);
        }
    }
}
