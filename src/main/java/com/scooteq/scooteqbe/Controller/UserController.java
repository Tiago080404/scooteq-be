package com.scooteq.scooteqbe.Controller;

import com.scooteq.scooteqbe.Model.User;
import com.scooteq.scooteqbe.Service.JwtService;
import com.scooteq.scooteqbe.Service.UserService;
import com.scooteq.scooteqbe.dto.AuthRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;
    public UserController(UserService userService,AuthenticationManager authenticationManager,JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager=authenticationManager;
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginAndGetToken(@RequestBody AuthRequest authRequest ){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        if (authentication.isAuthenticated()){
            String token = jwtService.generateToken(authRequest.getUsername());

            // Benutzer aus DB holen (Optional auflösen)
            User user = userService.getUserByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in DB"));



            Map<String, Object> userData = new HashMap<>();
            userData.put("username", user.getUsername());
            userData.put("role", user.getRole()); // direkt aus DB

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", userData);
            return ResponseEntity.ok(response);
            //return jwtService.generateToken(authRequest.getUsername());
        }else{
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkSession(HttpServletResponse request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("message", "Kein Token gefunden"));
        }

        String token = authHeader.substring(7);

        try {
            String username = jwtService.extractUsername(token);//hier wird überprüft ob der Token noch valide ist
            User user = userService.getUserByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User nicht gefunden"));

            Map<String, Object> userData = new HashMap<>();
            userData.put("username", user.getUsername());
            userData.put("role", user.getRole());

            return ResponseEntity.ok(Map.of("message", "Session gültig", "user", userData));
        } catch (Exception e) {
            return ResponseEntity.status(403)
                    .body(Map.of("message", "Token ungültig oder abgelaufen"));
        }
        }
    }
