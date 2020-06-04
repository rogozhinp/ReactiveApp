package com.linkedinlearingn.reactivespring.service;

import com.linkedinlearingn.reactivespring.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReactiveMongoOperations reactiveMongoOperations;

    @Autowired
    public ReservationServiceImpl(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Reservation> getReservation(String id) {
        return reactiveMongoOperations.findById(id, Reservation.class);
    }

    @Override
    public Mono<Reservation> createReservation(Mono<Reservation> reservationMono) {
        return reactiveMongoOperations.save(reservationMono);
    }

    @Override
    public Mono<Reservation> updateReservation(String id, Mono<Reservation> reservationMono) {
       // return reactiveMongoOperations.save(reservationMono);
        return reservationMono.flatMap(reservation -> reactiveMongoOperations.findAndModify(
                    Query.query(Criteria.where("id").is(id)),
                    Update.update("price", reservation.getPrice()), Reservation.class
            ).flatMap(result->{
                result.setPrice(reservation.getPrice());
                return Mono.just(result);
                })

        );
    }



    @Override
    public Mono<Reservation> deleteReservation(String id) {
        return null;
    }
}
