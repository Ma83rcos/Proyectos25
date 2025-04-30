package com.marcos.proyectoparkingdigital.parking_digital.dto.req;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RegistrarVehicleDto {

    private String plate;
    private String brand;

    public RegistrarVehicleDto() {
    }

    public RegistrarVehicleDto(String plate, String brand) {
        this.plate = plate;
        this.brand = brand;
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
}