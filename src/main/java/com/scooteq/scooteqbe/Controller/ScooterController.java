package com.scooteq.scooteqbe.Controller;

import com.scooteq.scooteqbe.Model.Scooter;
import com.scooteq.scooteqbe.Model.User;
import com.scooteq.scooteqbe.Service.ScooterService;
import com.scooteq.scooteqbe.Service.UserService;
import com.scooteq.scooteqbe.dto.ScooterUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scooters")
public class ScooterController {

    private final ScooterService scooterService;

    public ScooterController(ScooterService scooterService){
        this.scooterService = scooterService;
    }

    @GetMapping
    public List<Scooter> getAllScooter(){
        return scooterService.getAllScooters();
    }

    @PatchMapping
    public ResponseEntity<Scooter> updateScooter(@RequestBody ScooterUpdateRequest request){
    Scooter update = scooterService.updateScooter(request.id(), request.status(), request.description());
    return ResponseEntity.ok(update);
    }

}
