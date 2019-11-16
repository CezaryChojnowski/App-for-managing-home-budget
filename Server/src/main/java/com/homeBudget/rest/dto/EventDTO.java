package com.homeBudget.rest.dto;

import com.homeBudget.domain.event.Event;
import lombok.*;

import java.time.LocalDate;


@Value
@Data
public class EventDTO {
    private String name;
    private LocalDate startDate;
    private LocalDate finishDate;
    private boolean over;

    public EventDTO(Event event){
        this.name=event.getName();
        this.startDate=event.getStartDate();
        this.finishDate=event.getFinishDate();
        this.over=event.isOver();
    }
}
