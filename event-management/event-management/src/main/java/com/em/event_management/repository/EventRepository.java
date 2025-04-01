package com.em.event_management.repository;

import com.em.event_management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByEvent_ID(Long event_ID);
    Event findByEvent_Name(String event_Name);
    Event findByEvent_Venue(String event_Venue);
    Event findByEvent_Date(LocalDate event_Date);

}
