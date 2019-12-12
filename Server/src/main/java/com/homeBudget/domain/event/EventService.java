package com.homeBudget.domain.event;

import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    public final EventRepository eventRepository;
    private final UserRepository userRepository;

    public Event createNewEvent(String name, LocalDate startDate, LocalDate finishDate, String emailUser){
        Event event = new Event.EventBuilder()
                .name(name)
                .startDate(startDate)
                .finishDate(finishDate)
                .over(false)
                .user(userRepository.findUserByEmail(emailUser).get())
                .build();
        return eventRepository.save(event);
    }

    public List<Event> getAllEventsByUser(User user){
        return eventRepository.findEventsByUser(user);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}
