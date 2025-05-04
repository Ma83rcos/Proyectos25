package com.marcos.proyectoparkingdigital.parking_digital.controllers;

import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ReservationRequestDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.MensageResponseDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.ReservationsResponse;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Reservation;
import com.marcos.proyectoparkingdigital.parking_digital.services.ParkingSpotService;
import com.marcos.proyectoparkingdigital.parking_digital.services.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Api para gestion de reservas", description = "Endpoints para lagestion de reservas")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ParkingSpotService parkingSpotService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ParkingSpotService parkingSpotService2;

    public ReservationController(ParkingSpotService parkingSpotService, ReservationService reservationService, ParkingSpotService parkingSpotService2) {
        this.parkingSpotService = parkingSpotService;
        this.reservationService = reservationService;
        this.parkingSpotService2 = parkingSpotService2;
    }

    //Obtener todas las plazas de estacionamiento
    @Operation(summary = "Obtener todas las reservas",
            description ="api creada con funciones base de springboot",
            deprecated = false)
    @GetMapping
    public ResponseEntity<List<ReservationsResponse>> getAllParkingSpots(){
        List<ReservationsResponse> parkingSpots = reservationService.getAllReservations();
        return new ResponseEntity<>(parkingSpots, HttpStatus.OK);
    }

    //Obtener una plaza estacionamiento por id
    @Operation(summary = "Obtener reservas por id", description = "Retorna la reserva por su id")
    @GetMapping("/{id}")
    public ResponseEntity <Reservation> getParkingSpotById(@PathVariable Long id){
        Reservation reservation = reservationService.getReservationfindById(id);
        if(reservation == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    //Crear una nueva plaza de estaconamiento
    @PostMapping
    @Operation(summary = "crea una nueva plaza de estacionamiento", description = "crea una nueva plaza de estacionamiento con nuevo id")
    public ResponseEntity<MensageResponseDto> createReservation(@RequestBody ReservationRequestDto reservationRequestDto){
        return ResponseEntity.ok(reservationService.crearReservation(reservationRequestDto));

    }

    //Cancelar una reserva de estacionamiento
    @PutMapping("/{id}/cancelar")
    @Operation(summary = "cancelar una reserva", description = "cancela la reserva y deja libre la plaza")
    public ResponseEntity<MensageResponseDto> cancelarReserva(@PathVariable Long id){
        MensageResponseDto response = reservationService.cancelarReserva(id);
        return ResponseEntity.status(response.getCodigo()).body(response);
    }

    /// http:localhost:8080/api/reservations/{id}
    //Modificar una reserva
    @PutMapping("/{id}")
    @Operation(summary = "modificar una reserva ya creada", description = "Modifica los valores de una reserva ya creada")
    public ResponseEntity<MensageResponseDto> modificarReserva(@RequestBody ReservationRequestDto reservationRequestDto, @PathVariable Long id){
        MensageResponseDto response = reservationService.modificarReserva(reservationRequestDto, id);
        return ResponseEntity.status(response.getCodigo()).body(response);
    }





}
