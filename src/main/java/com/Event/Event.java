package com.Event;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //rename to id
    private int idEvent;

    @Setter
    //rename to name
    private String nameEvent;

    @Setter
    //read about local date time or zone date time or timestamp
    private Date startDate;

    @Setter
    private Date finishDate;

    @Setter
    //remove event form name
    private boolean eventOver;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Event{" +
                "idEvent=" + idEvent +
                ", nameEvent='" + nameEvent + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", eventOver='" + eventOver + '\'' +
                '}');
        return result.toString();
    }
}
