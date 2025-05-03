package com.marcos.proyectoparkingdigital.parking_digital.dto.res;

import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;

import java.time.LocalDateTime;

public class MensageResponseDto {

    private String mensaje;
    private int status;
    private int codigo;
    private String ruta;
    private LocalDateTime fecha;
    private Object data;

    public MensageResponseDto() {
    }

    public MensageResponseDto(String mensaje, int codigo, String ruta, LocalDateTime fecha, Object data) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.ruta = ruta;
        this.fecha = fecha;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
