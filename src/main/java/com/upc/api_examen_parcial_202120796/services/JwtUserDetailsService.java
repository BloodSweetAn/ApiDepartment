package com.upc.api_examen_parcial_202120796.services;

import com.upc.api_examen_parcial_202120796.entities.User;
import com.upc.api_examen_parcial_202120796.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository gcRepository;

    public UserDetails loadUserByUsername(String gcusername) throws UsernameNotFoundException {
        User gcuser = gcRepository.findByGcUsername(gcusername);

        if(gcuser == null) {
            throw new UsernameNotFoundException(String.format("User not exists", gcusername));
        }

        List<GrantedAuthority> gcroles = new ArrayList<>();

        gcuser.getGcRoles().forEach(rol -> {
            //System.out.println(rol.getRol());
            gcroles.add(new SimpleGrantedAuthority(rol.getGcRole()));
        });

        UserDetails gcUserDetails = new org.springframework.security.core.userdetails.User(gcuser.getGcUsername(), gcuser.getGcPassword(), gcuser.getGcEnabled(), true, true, true, gcroles);

        return gcUserDetails;
    }
}