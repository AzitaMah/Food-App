package com.datingfood.backend.api;

import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    @Autowired
    public AuthController(final AuthenticationManager authenticationManager, final PersonRepository personRepository,
            final RoleRepository roleRepository, final PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("registration")
    public ResponseEntity<String> register(@RequestBody final RegisterDto registerDto) {
        if(personRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("This username is taken.", HttpStatus.BAD_REQUEST);
        }

        Person person = new Person();
        person.setUsername(registerDto.getUsername());
        person.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        person.setFirstName(registerDto.getFirstName());
        person.setLastName(registerDto.getLastName());
        person.setBirthDate(registerDto.getBirthdate());
        person.setContact(registerDto.getContact());

        Role roles = roleRepository.findByName("USER").get();
        person.setRoles(Collections.singletonList(roles));

        personRepository.save(person);

        return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
    }
}
