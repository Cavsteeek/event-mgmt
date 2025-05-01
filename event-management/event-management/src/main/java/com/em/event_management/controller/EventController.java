package com.em.event_management.controller;

import com.em.event_management.dto.NewEventDto;
import com.em.event_management.repository.EventRepository;
import com.em.event_management.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventRepository eventRepository;

    @PostMapping("/new-event")
    public ResponseEntity<?> newEvent(@RequestBody NewEventDto newEventDto) {
        return ResponseEntity.ok(eventService.newEvent(newEventDto));
    }

    @GetMapping("/view-all-events")
    public ResponseEntity<?> viewAllEvents() {
        try {
            return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view-event/{eventId}")
    public ResponseEntity<?> viewEvent(@PathVariable Long eventId) {
        try {
            return new ResponseEntity<>(eventService.findEventById(eventId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/view-event/{eventName}")
//    public ResponseEntity<?> viewEvent(@PathVariable String eventName) {
//        try{
//            return new ResponseEntity<>(eventService.findEventByName(eventName), HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/delete-event/{eventId}")
    public ResponseEntity<?> deleteEventById(@PathVariable Long eventId) {
        try {
            eventService.deleteEvent(eventId);
            return ResponseEntity.ok("Event Deleted Successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}