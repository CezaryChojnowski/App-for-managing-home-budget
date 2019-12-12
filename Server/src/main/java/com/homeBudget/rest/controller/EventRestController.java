package com.homeBudget.rest.controller;

import com.homeBudget.domain.event.Event;
import com.homeBudget.domain.event.EventService;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
@CrossOrigin
public class EventRestController {
    public final EventService eventService;
    public final UserService userService;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event, Principal principal){
        String email = userService.findUserByEmail(principal.getName()).getEmail();
        return ResponseEntity.ok(eventService.createNewEvent(event.getName(), event.getStartDate(), event.getFinishDate(), email));
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity getAllEventsByUser(Principal principal){
        String email = userService.findUserByEmail(principal.getName()).getEmail();
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(eventService.getAllEventsByUser(user));
    }

    @RequestMapping(value = "/{idEvent}", method = RequestMethod.DELETE)
    @Transactional
    public void deleteWallet(@PathVariable Long idEvent, Principal principal){
        eventService.deleteEvent(idEvent);
    }
}
