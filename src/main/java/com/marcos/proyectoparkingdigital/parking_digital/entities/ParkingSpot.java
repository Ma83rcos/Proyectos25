package com.marcos.proyectoparkingdigital.parking_digital.entities;

import com.marcos.proyectoparkingdigital.parking_digital.AvailableStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(name="available")
    private AvailableStatus available;
    // Status:
    // 1 disponible-Available
    // 2 reservado-Reserved
    // 3 ocupado-Occupied

    public ParkingSpot() {
    }

    public ParkingSpot(Long id, String code, AvailableStatus available) {
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

    public AvailableStatus getAvailable() {
        return available;
    }

    public void setAvailable(AvailableStatus available) {
        this.available = available;
    }


}
