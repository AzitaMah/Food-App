package com.datingfood.backend.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.datingfood.backend.entities.Person;
import com.datingfood.backend.entities.Role;
import com.datingfood.backend.repositories.PersonRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PersonRepository personRepository;

    @Autowired
    public CustomUserDetailsService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /***
     * Loads user details from the database based on the provided username.
     * @param username the username for which user details are to be loaded
     * @return the User Information retrieved from the database
     * @throws UsernameNotFoundException if the provided username is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Person person = personRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new User(person.getUsername(), person.getPassword(), mapRolesToAuthorities(person.getRoles()));
    }

    /**
     * Maps roles to GrantedAuthority objects for user authorization.
     * @param roles the list of roles to be mapped
     * @return a collection of GrantedAuthority objects representing the user's roles
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(final List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()); // pass on each role and tun into a list
    }
}
