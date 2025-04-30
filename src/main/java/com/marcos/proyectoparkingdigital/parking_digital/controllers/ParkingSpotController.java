package com.marcos.proyectoparkingdigital.parking_digital.controllers;

import com.marcos.proyectoparkingdigital.parking_digital.dto.req.*;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.MensageResponseDto;
import com.marcos.proyectoparkingdigital.parking_digital.entities.ParkingSpot;
import com.marcos.proyectoparkingdigital.parking_digital.services.ParkingSpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="api gestion espot",description = "endpoint gestion spots")
@RestController
@RequestMapping("/api/parking-spots")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;
    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    //Obtener todas las plazas de estacionamiento
    @Operation(summary = "Obtener todas las plazas de estacionamiento",
            description ="api creada con funciones base de springboot",
            deprecated = false)
    @GetMapping
    public ResponseEntity<List<ObtenerParkingSpotsDto>> getAllParkingSpots(){
        List<ObtenerParkingSpotsDto> parkingSpots = parkingSpotService.getAllparkingSpots();
        return new ResponseEntity<>(parkingSpots, HttpStatus.OK);
    }

    //Obtener una plaza estacionamiento por id
    @Operation(summary = "Obtener plaza de estacionamiento por id", description = "Retorna la plaza por su id")
    @GetMapping("/{id}")
    public ResponseEntity <ParkingSpot> getParkingSpotById(@PathVariable Long id){
        ParkingSpot parkingSpot = parkingSpotService.getParkingSpotById(id);
        if(parkingSpot == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(parkingSpot, HttpStatus.OK);
    }

    //Crear una nueva plaza de estaconamiento
    @PostMapping
    @Operation(summary = "crea una nueva plaza de estacionamiento", description = "crea una nueva plaza de estacionamiento con nuevo id")
    public ResponseEntity<MensageResponseDto> createParkingSpot(@RequestBody RegistrarParkingSpotDto spotDto){
        return ResponseEntity.ok(parkingSpotService.createParkingSpot(spotDto));

    }

    //Actualizar una plaza de estacionamiento exixtente

    @PutMapping("/{id}")
    @Operation(summary = "atualiza plaza estacionamiento", description = "actauliza la informacion de una plaza")
    public ResponseEntity<ParkingSpot> modificarSpot(@PathVariable Long id, @RequestBody ActualizarParkingSpotDto updateDto) {
        Optional<ParkingSpot> spot = Optional.ofNullable(parkingSpotService.updateParkingSpot(id, updateDto));
        return spot.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Eliminar una plaza de aparcamiento
    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar plaza de estacionamiento", description = "eliminar plaza del sistema")
    public ResponseEntity<Object> eliminarSpot(@PathVariable Long id) {
        parkingSpotService.deleteParkingSpot(id);
        return ResponseEntity.noContent().build();
    }

    //Metodo para marcar una plaza como no disponible
    @PatchMapping("/{id}/ocupar")
    @Operation(summary = "meto marcar plaza como ocupada", description = "marca la plaza con estado ocupado")
    public ResponseEntity<ParkingSpot>markSpotOcupado(@PathVariable Long id){
        ParkingSpot spot = parkingSpotService.markSpotAsUnavailable(id);
        if (spot == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spot);
    }

}
