package com.marcos.proyectoparkingdigital.parking_digital.dto.req;

import com.marcos.proyectoparkingdigital.parking_digital.entities.ParkingSpot;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationRequestDto {


    private Long idVehiculo;
    private Long idSpot;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Long getIdSpot() {
        return idSpot;
    }

    public void setIdSpot(Long idSpot) {
        this.idSpot = idSpot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ReservationRequestDto(Long idVehiculo, Long idSpot, LocalDateTime startTime, LocalDateTime endTime) {
        this.idVehiculo = idVehiculo;
        this.idSpot = idSpot;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
