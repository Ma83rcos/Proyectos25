package com.marcos.proyectoparkingdigital.parking_digital.dto.req;

public class ObtenerVehiclesDto {

    private String brand;
    private String plate;
    private Long id ;

    public ObtenerVehiclesDto() {
    }

    public ObtenerVehiclesDto(String brand, String plate, Long id) {
        this.brand = brand;
        this.plate = plate;
        this.id = id;
    }

    public ObtenerVehiclesDto(Long id, String code, int available) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
