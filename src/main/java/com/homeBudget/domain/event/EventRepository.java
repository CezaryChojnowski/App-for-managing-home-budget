package com.homeBudget.domain.event;

import com.homeBudget.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findEventById(int id);
    List<Event> findEventsByUser(User user);
}
