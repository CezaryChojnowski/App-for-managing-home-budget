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
    private int idEvent;

    @Setter
    private String nameEvent;

    @Setter
    private Date startDate;

    @Setter
    private Date finishDate;

    @Setter
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
