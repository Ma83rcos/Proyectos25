package com.marcos.proyectoparkingdigital.parking_digital.dto.res;


import com.marcos.proyectoparkingdigital.parking_digital.entities.ParkingSpot;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationsResponse implements Serializable{
    private Long idReserva;
    private String placaVehiculo;
    private String marcaVehiculo;
    private String codigoPlaza;
    private LocalDateTime inicio;
    private LocalDateTime fin;

    public ReservationsResponse(Long idReserva, String placaVehiculo, String marcaVehiculo, String codigoPlaza, LocalDateTime inicio, LocalDateTime fin) {
        this.idReserva = idReserva;
        this.placaVehiculo = placaVehiculo;
        this.marcaVehiculo = marcaVehiculo;
        this.codigoPlaza = codigoPlaza;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getCodigoPlaza() {
        return codigoPlaza;
    }

    public void setCodigoPlaza(String codigoPlaza) {
        this.codigoPlaza = codigoPlaza;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }
}
