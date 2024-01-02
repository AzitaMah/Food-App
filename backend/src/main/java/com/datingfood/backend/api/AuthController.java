package com.datingfood.backend.api;

import java.util.Collections;


import com.datingfood.backend.dto.AuthResponseDTO;
import com.datingfood.backend.dto.LoginDto;
import com.datingfood.backend.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datingfood.backend.dto.RegisterDto;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.entities.Role;
import com.datingfood.backend.repositories.PersonRepository;
import com.datingfood.backend.repositories.RoleRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(final AuthenticationManager authenticationManager, final PersonRepository personRepository,
            final RoleRepository roleRepository, final PasswordEncoder passwordEncoder, final JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("registration")
    public ResponseEntity<String> register(@RequestBody final RegisterDto registerDto) {
        if(personRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("This username is taken.", HttpStatus.BAD_REQUEST);
        }

        final Person person = new Person();
        person.setUsername(registerDto.getUsername());
        person.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        person.setFirstName(registerDto.getFirstname());
        person.setLastName(registerDto.getLastname());
        person.setBirthDate(registerDto.getBirthdate());
        person.setContact(registerDto.getContact());

        Role roles = roleRepository.findByName("USER").get();
        person.setRoles(Collections.singletonList(roles));

        personRepository.save(person);

        return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
    }
}
