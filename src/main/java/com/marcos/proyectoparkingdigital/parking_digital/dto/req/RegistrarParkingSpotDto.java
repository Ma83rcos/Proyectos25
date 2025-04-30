package com.marcos.proyectoparkingdigital.parking_digital.dto.req;

public class RegistrarParkingSpotDto {

    private String code;
    private int available;

    public RegistrarParkingSpotDto() {
    }

    public RegistrarParkingSpotDto(String code, int available) {
        this.code = code;
        this.available = available;
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
