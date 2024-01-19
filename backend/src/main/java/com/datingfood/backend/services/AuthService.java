package com.datingfood.backend.services;

import com.datingfood.backend.dto.AuthResponseDTO;
import com.datingfood.backend.dto.LoginDTO;
import com.datingfood.backend.dto.RegisterDTO;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.entities.Role;
import com.datingfood.backend.repositories.PersonRepository;
import com.datingfood.backend.repositories.RoleRepository;
import com.datingfood.backend.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public AuthService(final AuthenticationManager authenticationManager, final PersonRepository personRepository,
                       final RoleRepository roleRepository, final PasswordEncoder passwordEncoder, final JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    public AuthResponseDTO authenticatePerson(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new AuthResponseDTO(token);
    }

    public void registerPerson(RegisterDTO registerDTO) {
        if (personRepository.existsByUsername(registerDTO.getUsername())) {
            throw new IllegalArgumentException("Username '" + registerDTO.getUsername() + "' is already taken.");
        }

        final Person person = new Person();
        person.setUsername(registerDTO.getUsername());
        person.setPassword(passwordEncoder.encode((registerDTO.getPassword())));
        person.setFirstName(registerDTO.getFirstname());
        person.setLastName(registerDTO.getLastname());
        person.setBirthDate(registerDTO.getBirthdate());
        person.setContact(registerDTO.getContact());
        person.setProfileImage(registerDTO.getProfileimage());

        Role roles = roleRepository.findByName("USER").get();
        person.setRoles(Collections.singletonList(roles));

        personRepository.save(person);
    }
}
