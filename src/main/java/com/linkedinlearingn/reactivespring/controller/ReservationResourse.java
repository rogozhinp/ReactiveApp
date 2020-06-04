package com.linkedinlearingn.reactivespring.controller;

import com.linkedinlearingn.reactivespring.model.Reservation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ReservationResourse.ROOM_V_1_RESERVATION)
@CrossOrigin
public class ReservationResourse {
    public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation/";

    @GetMapping(path = "{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<String> getReservationById(@PathVariable String roomId){
        // reservationService.getReservation(roomId)
        return Mono.just("{}");
    }
}
