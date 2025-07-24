package com.marcos.proyectoparkingdigital.parking_digital.services;

import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ActualizarVehicleDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ObtenerVehicleIdDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ObtenerVehiclesDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.RegistrarVehicleDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.MensageResponseDto;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    // Constructor para inyectar el repositorio
    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Método para obtener todos los vehículos
    public List<ObtenerVehiclesDto> getAllVehicles() {

        List<Vehicle> vehicles = (List<Vehicle>) vehicleRepository.findAll();
        return vehicles.stream()
                .map(vehicle -> new ObtenerVehiclesDto(
                        vehicle.getBrand(),
                        vehicle.getPlate(),
                        vehicle.getId()
                )).collect(Collectors.toList());
    }

    // Método para obtener un vehículo por su ID
    public ObtenerVehicleIdDto getVehicleById(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if(vehicleOptional.isPresent()){
            Vehicle vehicle = vehicleOptional.get();
            return new ObtenerVehicleIdDto(vehicle.getId(),vehicle.getPlate(),vehicle.getBrand());
        }
        return null;  // Retorna null si no encuentra el vehículo
    }

    // Método para crear un nuevo vehículo por marca y matricula
    public MensageResponseDto createVehicle(RegistrarVehicleDto vehicleDto) {

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setPlate(vehicleDto.getPlate());
        // if valores vacios - nulos
        if (vehicle.getBrand() == null || vehicle.getBrand().isEmpty() || vehicle.getPlate() == null || vehicle.getPlate().isEmpty()) {
            log.error("El vehiculo no puede tener campos vacíos o nulos");
            return new MensageResponseDto(
                    "El vehiculo no puede tener campos vacíos o nulos",
                    400,
                    "servicio/vehiculo",
                    LocalDateTime.now(),
                    null
            );
        }

        // if registro duplicado -> plate debe ser unico en neustra db



        Vehicle vehicleGuardado = vehicleRepository.save(vehicle);
        MensageResponseDto response = new MensageResponseDto(
                "vehiculo creado",
                200,
                "servici/vehiculo",
                LocalDateTime.now(),
                vehicleGuardado
        );
        return response;



    }

    // Método para actualizar un vehículo existente
    public Vehicle updateVehicle(Long id, ActualizarVehicleDto updatedVehicleDto) {
        Optional<Vehicle>vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            vehicle.setPlate(updatedVehicleDto.getPlate());
            vehicle.setBrand(updatedVehicleDto.getBrand());
            return vehicleRepository.save(vehicle);
        } else {
            return null;  // Retorna null si el vehículo no existe
        }
    }

    // Método para eliminar un vehículo por su ID
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }


}
