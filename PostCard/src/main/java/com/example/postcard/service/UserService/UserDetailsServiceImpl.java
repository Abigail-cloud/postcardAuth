package com.example.postcard.service.UserService;

import com.example.postcard.entity.User;
import com.example.postcard.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private  UserRepository userRepository;


    public UserDetailsServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with email: " + email));
        return UserDetailsImpl.build(user);
    }
}
