package com.marcos.proyectoparkingdigital.parking_digital.dto.req;

import com.marcos.proyectoparkingdigital.parking_digital.AvailableStatus;

public class ActualizarParkingSpotDto {
    private String code;
    private AvailableStatus available;

    public ActualizarParkingSpotDto() {
    }

    public ActualizarParkingSpotDto(String code, AvailableStatus available) {
        this.code = code;
        this.available = available;
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
