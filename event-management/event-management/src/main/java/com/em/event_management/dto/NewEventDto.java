package com.em.event_management.dto;

import lombok.Data;

@Data
public class NewEventDto {
    private String eventName;
    private String eventDescription;
    private String eventDate;
    private String eventVenue;
}
