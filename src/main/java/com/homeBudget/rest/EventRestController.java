package com.homeBudget.rest;

import com.homeBudget.domain.event.Event;
import com.homeBudget.domain.event.EventDAO;
import com.homeBudget.domain.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventRestController {
    public final EventDAO eventDAO;
    public final UserDAO userDAO;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(eventDAO.createNewEvent(event.getName(), event.getStartDate(), event.getFinishDate(), email));
    }
}
