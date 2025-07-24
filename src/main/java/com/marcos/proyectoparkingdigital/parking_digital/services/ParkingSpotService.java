package com.marcos.proyectoparkingdigital.parking_digital.services;

import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ActualizarParkingSpotDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.ObtenerParkingSpotsDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.req.RegistrarParkingSpotDto;
import com.marcos.proyectoparkingdigital.parking_digital.dto.res.MensageResponseDto;
import com.marcos.proyectoparkingdigital.parking_digital.entities.ParkingSpot;
import com.marcos.proyectoparkingdigital.parking_digital.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
// convertir de DTO:


    public List<ObtenerParkingSpotsDto> getAllparkingSpots() {

        List<ParkingSpot> parkingSpots = (List<ParkingSpot>) parkingSpotRepository.findAll();


        List<ObtenerParkingSpotsDto> resultado = parkingSpots.stream()
                .map(spot -> new ObtenerParkingSpotsDto(
                        spot.getId(),
                        spot.getCode(),
                        spot.getAvailable()
                        ))
                .collect(Collectors.toList());
        Collections.reverse(resultado);

    return resultado;

    }

    //Metodo para obtener una plaza estacionamiento por su id
    public ParkingSpot getParkingSpotById(Long id) {
        Optional<ParkingSpot> parkingSpot = parkingSpotRepository.findById(id);
        return parkingSpot.orElse(null);

    }

    // encapsulamiento == public
    // externa o interna == externa
    // tipo de dato == MensageResponseDto, Objeto
    // nombre de la funcion == createParkingSpot
    // parametros (tipo_de_dato parametro) == sin parametros ()


    //Metodo para crear una nueva plaza de estacionamiento
    public MensageResponseDto createParkingSpot(RegistrarParkingSpotDto spotDto) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setCode(spotDto.getCode());
        parkingSpot.setAvailable(spotDto.getAvailable());

        //Repositorio

        // Variable = <- '55'
        ParkingSpot savedSpot = parkingSpotRepository.save(parkingSpot);

                return new MensageResponseDto(
                "plaza creada",
                200,
                "servici/vehiculo",
                LocalDateTime.now(),
                savedSpot
        );
    }

    // encapsulamiento == public
    // externa o interna == externa
    // tipo de dato == ResponseEntity, ParkingSpot Objeto
    // nombre de la funcion == updateParkingSpot
    // parametros (tipo_de_dato parametro) (Long id, Dto Objeto)



    //Actualizar una plaza  estacionamiento existente
    public MensageResponseDto updateParkingSpot(Long id, ActualizarParkingSpotDto updateparkingSpotDto) {
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository.findById(id);
        if (parkingSpotOptional.isPresent()){
            ParkingSpot parkingSpot = parkingSpotOptional.get();
            parkingSpot.setCode(updateparkingSpotDto.getCode());
            parkingSpot.setAvailable(updateparkingSpotDto.getAvailable());
            ParkingSpot spotEditada = parkingSpotRepository.save(parkingSpot);

            MensageResponseDto respuesta = new MensageResponseDto(
                    "plaza modificada correctamente",
                    200,
                    "servicio/plaza",
                    LocalDateTime.now(),
                    spotEditada
            );

            return respuesta;

        }
        return new MensageResponseDto(
                "Plata no encontrada",
                200,
                "servicio/plaza",
                LocalDateTime.now(),
                null
        );
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
