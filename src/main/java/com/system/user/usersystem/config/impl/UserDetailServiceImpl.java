package com.system.user.usersystem.config.impl;

import com.system.user.usersystem.models.User;
import com.system.user.usersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email ) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail( email );

        if( user == null ){
            throw new UsernameNotFoundException("El usuario " + email + " No existe en la base de datos");
        }
        System.out.println( user.getEmail() );
        System.out.println( user.getPassword() );
        return new UserDetailsImpl( user );
    }
}
