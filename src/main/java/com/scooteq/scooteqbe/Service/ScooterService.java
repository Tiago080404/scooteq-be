package com.scooteq.scooteqbe.Service;

import com.scooteq.scooteqbe.Model.Scooter;
import com.scooteq.scooteqbe.Repository.ScooterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService {

    private final ScooterRepository scooterRepository;

    public ScooterService(ScooterRepository scooterRepository){
        this.scooterRepository = scooterRepository;
    }
    public List<Scooter> getAllScooters(){
        return scooterRepository.findAll();
    }

    public Scooter updateScooter(int id, String status, String description){
         scooterRepository.updateScooter(status,description,id);
         return scooterRepository.findById((long) id).orElse(null);
    }
}
