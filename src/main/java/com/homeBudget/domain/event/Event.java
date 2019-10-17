package com.homeBudget.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeBudget.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Setter
    @Column(name = "finish_date", columnDefinition = "DATE")
    private LocalDate finishDate;

    @Setter
    private boolean over;

    @Setter
    @ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
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

    public static final class Builder {
        private Long id;
        private String name;
        private LocalDate startDate;
        private LocalDate finishDate;
        private boolean over;
        private User user;

        public Builder id(Long idSubcategory) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }
        public Builder finishDate(LocalDate finishDate) {
            this.finishDate = finishDate;
            return this;
        }
        public Builder over(boolean over) {
            this.over = over;
            return this;
        }
        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Event build() {
            Event event = new Event();
            event.id = this.id;
            event.name = this.name;
            event.startDate=this.startDate;
            event.finishDate=this.finishDate;
            event.over=this.over;
            event.user=this.user;
            return event;
        }
    }
}
