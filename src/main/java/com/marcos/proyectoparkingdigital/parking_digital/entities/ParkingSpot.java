package com.marcos.proyectoparkingdigital.parking_digital.entities;

import jakarta.persistence.*;

@Entity
@Table(name="parking_spots")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_spot")
    private Long id;

    @Column(name="code", nullable = false, unique = true)
    private String code;

    @Column(name="available")
    private int available;
    // Status:
    // 1 disponible
    // 2 reservado
    // 3 ocupado

    public ParkingSpot() {
    }

    public ParkingSpot(Long id, String code, int available) {
        this.id = id;
        this.code = code;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }


}
