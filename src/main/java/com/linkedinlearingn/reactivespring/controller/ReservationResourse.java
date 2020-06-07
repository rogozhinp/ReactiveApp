package com.linkedinlearingn.reactivespring.controller;

import com.linkedinlearingn.reactivespring.model.Reservation;
import com.linkedinlearingn.reactivespring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ReservationResourse.ROOM_V_1_RESERVATION)
@CrossOrigin
public class ReservationResourse {
    public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation/";

    private final ReservationService reservationService;

    @Autowired
    public ReservationResourse(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Reservation> getReservationById(@PathVariable String id){

        return reservationService.getReservation(id);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation) {

        return reservationService.createReservation(reservation);
    }

    @PutMapping(path = "{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Reservation> updatePrice(@PathVariable String id,
            @RequestBody Mono<Reservation> reservation){
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping(path = "{id}")
    public Mono<Boolean> deleteReservation(@PathVariable String id){
        return reservationService.deleteReservation(id);
    }
}
