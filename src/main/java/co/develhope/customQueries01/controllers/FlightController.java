package co.develhope.customQueries01.controllers;

import co.develhope.customQueries01.entities.Flight;
import co.develhope.customQueries01.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static co.develhope.customQueries01.entities.FlightStatusEnum.ONTIME;

@RestController
@RequestMapping("/flights")
public class FlightController {

    public String generateRandomValue(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/getFlights")
    public List<Flight> getFlights(){
        List<Flight> flights = new ArrayList<>();
        for (long i = 1; i <= 50; i++) {
            flights.add(new Flight(i, generateRandomValue(), generateRandomValue(),generateRandomValue(),ONTIME));
        }
        flightRepository.saveAllAndFlush(flights);
        return flights;
    }

    @GetMapping("/getAllFlights")
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }
}
