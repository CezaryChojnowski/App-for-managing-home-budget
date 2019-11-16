package com.homeBudget.rest.controller;

import com.homeBudget.domain.event.Event;
import com.homeBudget.domain.event.EventService;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventRestController {
    public final EventService eventService;
    public final UserService userService;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(eventService.createNewEvent(event.getName(), event.getStartDate(), event.getFinishDate(), email));
    }

    @GetMapping
    public ResponseEntity getAllEventsByUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(eventService.getAllEventsByUser(user));
    }
}
