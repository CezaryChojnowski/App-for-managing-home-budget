package com.homeBudget.domain.event;

import com.homeBudget.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;

    @Setter
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Setter
    @Column(name = "finish_date", columnDefinition = "DATE")
    private LocalDate finishDate;

    @Setter
    private boolean over;

    @Setter
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("event{" +
                "idEvent=" + id +
                ", nameEvent='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", eventOver='" + over + '\'' +
                '}');
        return result.toString();
    }

}
