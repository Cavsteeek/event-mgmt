package com.em.event_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Event_ID;

    private String Event_Name;

    private String Event_Description;

    private LocalDate Date;

    private String Event_Venue;
}
