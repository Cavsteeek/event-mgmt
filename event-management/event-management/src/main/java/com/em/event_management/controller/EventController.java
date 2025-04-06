package com.em.event_management.controller;

import com.em.event_management.dto.NewEventDto;
import com.em.event_management.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/new-event")
    public ResponseEntity<?> newEvent(@RequestBody NewEventDto newEventDto) {
        return ResponseEntity.ok(eventService.newEvent(newEventDto));
    }
}