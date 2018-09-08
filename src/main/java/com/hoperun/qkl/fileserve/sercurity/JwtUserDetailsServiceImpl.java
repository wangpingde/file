package com.hoperun.qkl.fileserve.sercurity;


import com.hoperun.qkl.fileserve.domain.User;
import com.hoperun.qkl.fileserve.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with login '%s'.", login));
        } else {
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(),user.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
                                  new SimpleGrantedAuthority("ROLE_USER")));
        }
    }
}
