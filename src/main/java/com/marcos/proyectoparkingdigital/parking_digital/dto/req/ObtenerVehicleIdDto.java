package com.marcos.proyectoparkingdigital.parking_digital.dto.req;

public class ObtenerVehicleIdDto {

    private Long id;
    private String brand;
    private String plate;

    public ObtenerVehicleIdDto() {
    }

    public ObtenerVehicleIdDto(Long id, String brand, String plate) {
        this.id = id;
        this.brand = brand;
        this.plate = plate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
