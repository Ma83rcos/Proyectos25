package com.marcos.proyectoparkingdigital.parking_digital.repositories;

import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle,Long > {

}
