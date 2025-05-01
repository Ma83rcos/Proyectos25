package com.marcos.proyectoparkingdigital.parking_digital.dto.req;

public class ObtenerParkingSpotsDto {
    private Long id;
    private String code;
    private int available;
    public ObtenerParkingSpotsDto() {
    }
    public ObtenerParkingSpotsDto(Long id, String code, int available) {
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
