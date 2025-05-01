package com.marcos.proyectoparkingdigital.parking_digital.services;

import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ActualizarParkingSpotDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ObtenerParkingSpotsDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ObtenerVehiclesDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.RegistrarParkingSpotDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.MensageResponseDto;
import com.marcos.proyectoparkingdigital.parking_digital.entities.ParkingSpot;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;

    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    //Metodo para obtener todas las plazas de estacionamiento parking
    public List<ObtenerParkingSpotsDto> getAllparkingSpots() {

        List<ParkingSpot> parkingSpots = (List<ParkingSpot>) parkingSpotRepository.findAll();
        return parkingSpots.stream()
                .map(parkingSpot -> new ObtenerParkingSpotsDto(
                        parkingSpot.getId(),
                        parkingSpot.getCode(),
                        parkingSpot.getAvailable()
                )).collect(Collectors.toList());

    }

    //Metodo para obtener una plaza estacionamiento por su id
    public ParkingSpot getParkingSpotById(Long id) {
        Optional<ParkingSpot> parkingSpot = parkingSpotRepository.findById(id);
        return parkingSpot.orElse(null);

    }

    //Metodo para crear una nueva plaza de estacionamiento
    public MensageResponseDto createParkingSpot(RegistrarParkingSpotDto spotDto) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setCode(spotDto.getCode());
        parkingSpot.setAvailable(spotDto.getAvailable());
        ParkingSpot savedSpot = parkingSpotRepository.save(parkingSpot);

                return new MensageResponseDto(
                "plaza creada",
                200,
                "servici/vehiculo",
                LocalDateTime.now(),
                savedSpot
        );
    }

    //Actualizar una plaza  estacionamiento existente
    public ParkingSpot updateParkingSpot(Long id, ActualizarParkingSpotDto updateparkingSpotDto) {
        Optional<ParkingSpot>parkingSpotOptional = parkingSpotRepository.findById(id);
        if (parkingSpotOptional.isPresent()){
            ParkingSpot parkingSpot = parkingSpotOptional.get();
            parkingSpot.setCode(updateparkingSpotDto.getCode());
            parkingSpot.setAvailable(updateparkingSpotDto.getAvailable());
            return parkingSpotRepository.save(parkingSpot);
        }
        return null;
    }

    //Metodo para eliminar una plaza de estacionamiento por su id
    public void deleteParkingSpot(@PathVariable Long id) {
        parkingSpotRepository.deleteById(id);
    }

    //Metodo para marcar una plaza estacionamiento como no disponible
    public ParkingSpot markSpotAsUnavailable(Long id){
        Optional<ParkingSpot> parkingSpot = parkingSpotRepository.findById(id);
        if(parkingSpot.isPresent()){
            ParkingSpot spot = parkingSpot.get();
            spot.setAvailable(3);//Marca como no disponible status 1
            return parkingSpotRepository.save(spot); //Guarda el cambio
        }
        return null;   //retorna null si no encuentra la plaza
    }

}
