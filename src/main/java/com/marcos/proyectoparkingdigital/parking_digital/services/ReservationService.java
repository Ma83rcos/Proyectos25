package com.marcos.proyectoparkingdigital.parking_digital.services;

import com.marcos.proyectoparkingdigital.parking_digital.entities.ParkingSpot;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Reservation;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.ParkingSpotRepository;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.ReservationRepository;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingSpotRepository spotRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, VehicleRepository vehicleRepository, ParkingSpotRepository spotRepository) {
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.spotRepository = spotRepository;
    }

    //Obtener todas las reservas
    public List<Reservation> getAllReservations(){
        return (List<Reservation>) reservationRepository.findAll();
    }

    //Obtener una reserva por Id
    public Reservation getReservationfindById(Long id){
         Optional<Reservation> reservation = reservationRepository.findById(id);
         return reservation.orElse(null);
    }

    //Crear una nueva reserva
    public Reservation crearReservation(Reservation reservation){
            // 1. Verificar si el vehículo está disponible
            Optional<Vehicle> vehicleOptional = vehicleRepository.findById(reservation.getVehicle().getId());
            if (!vehicleOptional.isPresent()) {
                throw new IllegalArgumentException("El vehículo no existe.");
            }
            Vehicle vehicle = vehicleOptional.get();

            // Verificar si el vehículo ya tiene una reserva en ese rango de tiempo.
            List<Reservation> existingReservations = reservationRepository.findOverlappingReservations(vehicle,
                                          reservation.getStartTime(), reservation.getEndTime());
            if (!existingReservations.isEmpty()) {
                throw new IllegalStateException("El vehículo ya tiene una reserva en ese rango de tiempo.");
            }

            // 2. Verificar si la plaza de estacionamiento está disponible.
            Optional<ParkingSpot> parkingSpotOptional = spotRepository.findById(reservation.getSpot().getId());
            if (!parkingSpotOptional.isPresent()) {
                throw new IllegalArgumentException("La plaza de estacionamiento no existe.");
            }
            ParkingSpot parkingSpot = parkingSpotOptional.get();

            // Verificar si la plaza de estacionamiento está disponible
            // esta reservado o esta ocupado?
            if (parkingSpot.isAvailable() == 2 || parkingSpot.isAvailable() == 3) {
                throw new IllegalStateException("La plaza de estacionamiento no está disponible.");
            }

            // 3. Si todo está correcto, guardamos la reserva
            reservation.setVehicle(vehicle);
            reservation.setSpot(parkingSpot);

            // Marcar la plaza como reservado
            parkingSpot.setAvailable(2);
            spotRepository.save(parkingSpot);

            return reservationRepository.save(reservation); // Guardar la reserva
        }
    }



