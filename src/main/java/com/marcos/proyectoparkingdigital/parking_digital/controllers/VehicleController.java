package com.marcos.proyectoparkingdigital.parking_digital.controllers;

import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ActualizarVehicleDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ObtenerVehicleIdDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ObtenerVehiclesDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.RegistrarVehicleDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.MensageResponseDto;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;
import com.marcos.proyectoparkingdigital.parking_digital.services.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name="api gestion vehicles",description = "endpoint gestion vehicles")
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    //Metodo para obtener todos los vehiculos
    @Operation(summary = "obtener todos los vehuculos",
            description = "Retorna lista de todos los vehiculos")
    @GetMapping
    public ResponseEntity<List<ObtenerVehiclesDto>>getAllVehicles(){
        List<ObtenerVehiclesDto>vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    //Metodo para obtener un vehiculo por su id
    @Operation(summary = "obtener vehiculo por id", description = "Retorna los datos de un vehiculo por su id")
    @GetMapping("/{id}")
    public ResponseEntity<ObtenerVehicleIdDto>getVehicleById(Long id){
        ObtenerVehicleIdDto vehicleIdDto = vehicleService.getVehicleById(id);
        if (vehicleIdDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicleIdDto);
    }

    //Crear un nuevo vehiculo pasando la marca y la matricula
    @Operation(summary = "crear vehiculo",description = "usando DTO de request registrar VehiculeDto, dto mensaje respuesta")
    @PostMapping
    public ResponseEntity<MensageResponseDto> crearVehiculo(@RequestBody RegistrarVehicleDto vehicleDto) {
        MensageResponseDto respuesta = vehicleService.createVehicle(vehicleDto);
        return ResponseEntity.ok(respuesta);
    }

    //Actualizar un vehiculo
    @Operation(summary = "modificar vehiculo", description = "Actualizar la informacion de un vehiculo")
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> actualizarVehicle(@PathVariable Long id,@RequestBody ActualizarVehicleDto updatedVehicleDto){
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, updatedVehicleDto);
        if (updatedVehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVehicle);
    }

    //Eliminar un vehiculo
    @Operation(summary = "eliminar vehiculo", description = "Elimina un vehiculo del sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

}
