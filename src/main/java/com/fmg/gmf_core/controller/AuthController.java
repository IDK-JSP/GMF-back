package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.UserDao;
import com.fmg.gmf_core.entitys.User;
import com.fmg.gmf_core.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserDao userDao, PasswordEncoder encoder, JwtUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        User newUser = getUser(user);
        userDao.save(newUser);
        return ResponseEntity.ok("User registered successfully!");

    }

    private User getUser(User user) {
        return new User(
                user.getEmail(),
                encoder.encode(user.getPassword()),
                user.getPseudo(),
                user.getImage()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    )
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream().toList().getFirst().getAuthority();
            System.out.println(role.substring(5));
            String token = jwtUtils.generateToken(userDetails.getUsername(),role);
            return ResponseEntity.ok(token); // Retourne le token si l'authentification est réussie
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erreur : Identifiants invalides");
        }
    }

}
