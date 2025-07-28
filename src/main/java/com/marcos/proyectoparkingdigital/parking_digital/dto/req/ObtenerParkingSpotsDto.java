package com.marcos.proyectoparkingdigital.parking_digital.dto.req;

import com.marcos.proyectoparkingdigital.parking_digital.AvailableStatus;

public class ObtenerParkingSpotsDto {
    private Long id;
    private String code;
    private AvailableStatus available;
    public ObtenerParkingSpotsDto() {
    }
    public ObtenerParkingSpotsDto(Long id, String code, AvailableStatus available) {
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
