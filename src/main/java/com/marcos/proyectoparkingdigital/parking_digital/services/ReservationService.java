package com.marcos.proyectoparkingdigital.parking_digital.services;

import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ReservationRequestDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.MensageResponseDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.ReservationsResponse;
import com.marcos.proyectoparkingdigital.parking_digital.entities.ParkingSpot;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Reservation;
import com.marcos.proyectoparkingdigital.parking_digital.entities.Vehicle;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.ParkingSpotRepository;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.ReservationRepository;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ReservationsResponse> getAllReservations() {
        List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();

        List<ReservationsResponse> resultado = reservations.stream()
                .map(res -> new ReservationsResponse(
                        res.getId(),
                        res.getVehicle().getPlate(),
                        res.getVehicle().getBrand(),
                        res.getSpot().getCode(),
                        res.getStartTime(),
                        res.getEndTime()
                ))
                .collect(Collectors.toList());

        Collections.reverse(resultado); // Opcional: mostrar los más recientes arriba
        return resultado;
    }


    //Obtener una reserva por Id
    public Reservation getReservationfindById(Long id){
         Optional<Reservation> reservation = reservationRepository.findById(id);
         return reservation.orElse(null);
    }

    //Crear una nueva reserva
    public MensageResponseDto crearReservation(ReservationRequestDto dto) {
        // Buscar vehículo
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(dto.getIdVehiculo());
        if (vehicleOpt.isEmpty()) {
            return new MensageResponseDto("Vehículo no encontrado", 404, "/api/v1/reservas", LocalDateTime.now(), null);
        }

        // Buscar plaza
        Optional<ParkingSpot> spotOpt = spotRepository.findById(dto.getIdSpot());
        if (spotOpt.isEmpty()) {
            return new MensageResponseDto("Plaza de estacionamiento no encontrada", 404, "/api/v1/reservas", LocalDateTime.now(), null);
        }

        Vehicle vehicle = vehicleOpt.get();
        ParkingSpot spot = spotOpt.get();

        // Validar solapamiento
        boolean existeSolapamiento = reservationRepository
                .existsByVehicleAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        vehicle, dto.getEndTime(), dto.getStartTime());
        if (existeSolapamiento) {
            return new MensageResponseDto("El vehículo ya tiene una reserva en ese horario", 409, "/api/v1/reservas", LocalDateTime.now(), null);
        }

        // Validar disponibilidad de la plaza
        if (spot.getAvailable() == 2 || spot.getAvailable() == 3) {
            return new MensageResponseDto("La plaza no está disponible", 409, "/api/v1/reservas", LocalDateTime.now(), null);
        }

        // Crear reserva
        Reservation reserva = new Reservation();
        reserva.setVehicle(vehicle);
        reserva.setSpot(spot);
        reserva.setStartTime(dto.getStartTime());
        reserva.setEndTime(dto.getEndTime());
        reserva.setStatus(1);// reserva activa

        Reservation guardada = reservationRepository.save(reserva);

        // Marcar plaza como reservada
        spot.setAvailable(2);
        spotRepository.save(spot);

        // Construir DTO de respuesta
        ReservationsResponse respuesta = new ReservationsResponse(
                guardada.getId(),
                vehicle.getPlate(),
                vehicle.getBrand(),
                spot.getCode(),
                guardada.getStartTime(),
                guardada.getEndTime()
        );

        return new MensageResponseDto(
                "✅ Reserva creada correctamente",
                201,
                "/api/v1/reservas",
                LocalDateTime.now(),
                respuesta
        );
    }
    //Cancelar una reserva de estacionamiento
    public MensageResponseDto cancelarReserva(Long id){
        Optional<Reservation> resrvaCanc = reservationRepository.findById(id);
        if(resrvaCanc.isEmpty()){
            return new MensageResponseDto(
                    "Reserva no encontrada",
                    404,
                    "/api/reservations" + id + "/cancelar",
                    LocalDateTime.now(),
                    null);
        }
        Reservation reserva = resrvaCanc.get();
        // preguntamos si el satsu ya es 0, es decir si ya esta cancelada
        if(reserva.getStatus() == 0){
            return new MensageResponseDto(
                    "La reserva ya esta cancelada",
                    400,
                    "/api/reservations/" + id + "/cancelar",
                    LocalDateTime.now(),
                    null
            );
        }
        // ponemos setStatus 0 para cancelar esta reserva
        reserva.setStatus(0);
        reservationRepository.save(reserva);
        //liberar plaza de estacionamiento
        ParkingSpot spot = reserva.getSpot();
        spot.setAvailable(1);  //1 = disponible
        spotRepository.save(spot);
        //respuesta final
        return  new MensageResponseDto(
                "✅ Reserva cancelada con exito",
                200,
                "api/resrvations/" + id + "/cancelar",
                LocalDateTime.now(),
                null
        );
    }


    //Crear una nueva reserva
    public MensageResponseDto modificarReserva(ReservationRequestDto reservationRequestDto, Long id) {
        // Buscar vehículo
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(reservationRequestDto.getIdVehiculo());
        if (vehicleOpt.isEmpty()) {
            return new MensageResponseDto("Vehículo no encontrado", 404, "/api/v1/reservas", LocalDateTime.now(), null);
        }

        // Buscar plaza
        Optional<ParkingSpot> spotOpt = spotRepository.findById(reservationRequestDto.getIdSpot());
        if (spotOpt.isEmpty()) {
            return new MensageResponseDto("Plaza de estacionamiento no encontrada", 404, "/api/v1/reservas", LocalDateTime.now(), null);
        }

        Vehicle vehicle = vehicleOpt.get();
        ParkingSpot spot = spotOpt.get();

        Optional<Reservation>reservationFromId  = reservationRepository.findById(id);

        Reservation reservation = reservationFromId.get();
        reservation.setVehicle(vehicle);
        reservation.setSpot(spot);
        reservation.setEndTime(reservationRequestDto.getEndTime());
        reservation.setStartTime(reservationRequestDto.getStartTime());

        reservationRepository.save(reservation);


        Reservation guardada = reservationRepository.save(reservation);


        // Construir DTO de respuesta
        ReservationsResponse respuesta = new ReservationsResponse(
                guardada.getId(),
                vehicle.getPlate(),
                vehicle.getBrand(),
                spot.getCode(),
                guardada.getStartTime(),
                guardada.getEndTime()
        );

        return new MensageResponseDto(
                "✅ Reserva modificada correctamente",
                201,
                "/api/v1/reservas",
                LocalDateTime.now(),
                respuesta
        );
    }


}




