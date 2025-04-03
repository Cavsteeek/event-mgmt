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
    public Event findEventByName(String EventName){
        return eventRepository.findByEventName(EventName);
    }

    @Override
    public void newEvent(NewEventDto event){
        try{
            Event newEvent = new Event();
            newEvent.setEventName(event.getEventName());
            newEvent.setEventDescription(event.getEventDescription());
            newEvent.setEventDate(event.getEventDate());
            newEvent.setEventVenue(event.getEventVenue());
            eventRepository.save(newEvent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEvent(Long EventId) {
        eventRepository.deleteById(EventId);
    }
}
