package com.amchiche.product_shop.controller;

import com.amchiche.product_shop.controller.dto.AuthRequest;
import com.amchiche.product_shop.controller.dto.AuthResponse;
import com.amchiche.product_shop.controller.dto.RegisterRequest;
import com.amchiche.product_shop.model.User;
import com.amchiche.product_shop.repository.UserRepository;
import com.amchiche.product_shop.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }
@PostMapping("/account")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("un compte existant est associé à cet email !");
        }

        User u = User.builder().username(request.username()).firstname(request.firstname()).email(request.email()).password(passwordEncoder.encode(request.password())).build();
        userRepository.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/token")

    public ResponseEntity<?> token (@RequestBody AuthRequest request){

        try{
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
            String jwt = jwtUtil.generateToken(request.email());
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch(BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Les crédentiels sont invalide");
        }
    }

}
