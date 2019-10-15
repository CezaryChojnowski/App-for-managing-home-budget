package com.homeBudget.domain.event;

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
    private Long id;

    @Setter
    private String name;

    @Setter
    private Date startDate;

    @Setter
    private Date finishDate;

    @Setter
    private boolean eventOver;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("event{" +
                "idEvent=" + id +
                ", nameEvent='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", eventOver='" + eventOver + '\'' +
                '}');
        return result.toString();
    }
}
