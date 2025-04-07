package com.em.event_management.repository;

import com.em.event_management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByEventID(Long eventID);
    Event findByEventName(String eventName);

    boolean existsByEventName(String eventName);
}
