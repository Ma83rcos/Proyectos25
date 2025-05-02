package com.marcos.proyectoparkingdigital.parking_digital.repositories;

import com.marcos.proyectoparkingdigital.parking_digital.entities.Reservation;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation " +
            "r WHERE r.vehicle = :vehicle " +
            "AND r.startTime <= :endTime " +
            "AND r.endTime >= :startTime")
    List<Reservation> findOverlappingReservations(
            @Param("vehicle") Vehicle vehicle,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    boolean existsByVehicleAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Vehicle vehicle, LocalDateTime endTime, LocalDateTime startTime
    );

}
