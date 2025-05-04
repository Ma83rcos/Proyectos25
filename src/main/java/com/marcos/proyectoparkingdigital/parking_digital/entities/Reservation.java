package com.marcos.proyectoparkingdigital.parking_digital.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reservation", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name="start_time")
    private LocalDateTime startTime;

    @Column(name="end_time")
    private LocalDateTime endTime;

    @Column(name="price_per_hour")
    private BigDecimal pricePerHour;

    @Column(name="status", nullable = false)
    private int status;
    // 1 registro activo
    // 0 registro eliminado - desactivado

    @ManyToOne
    @JoinColumn(name = "spot_id", nullable = false)
    private ParkingSpot spot;

    public Reservation() {
    }

    public Reservation(Long id, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime,
                       BigDecimal pricePerHour, int status, ParkingSpot spot) {
        this.id = id;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pricePerHour = pricePerHour;
        this.status = status;
        this.spot = spot;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }
}
