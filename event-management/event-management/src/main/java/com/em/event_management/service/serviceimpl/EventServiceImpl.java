package com.em.event_management.service.serviceimpl;

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
    public Event findEventByName(String EventName){
        return eventRepository.findByEvent_Name(EventName);
    }

    @Override
    public void deleteEvent(Long EventId) {
        eventRepository.deleteById(EventId);
    }
}
