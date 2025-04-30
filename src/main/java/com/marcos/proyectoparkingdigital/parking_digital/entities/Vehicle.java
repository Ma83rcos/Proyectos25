package com.marcos.proyectoparkingdigital.parking_digital.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_vehicle", nullable = false)
    private Long id;

    @Column(name="plate", nullable = false, unique = true, length = 50)
    private String plate;

    @Column(name="brand", nullable = false, unique = true, length = 50)
    private String brand;

    @OneToMany(mappedBy = "vehicle")
    private List<Reservation> reservations;


    public Vehicle() {
    }


    public Vehicle(List<Reservation> reservations, String brand, String plate, Long id) {
        this.reservations = reservations;
        this.brand = brand;
        this.plate = plate;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
