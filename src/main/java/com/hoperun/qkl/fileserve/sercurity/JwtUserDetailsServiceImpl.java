package com.hoperun.qkl.fileserve.sercurity;


import com.hoperun.qkl.fileserve.bo.UserBO;
import com.hoperun.qkl.fileserve.domain.User;
import com.hoperun.qkl.fileserve.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(user,userBO);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with login '%s'.", login));
        } else {
            return  userBO;
        }
    }
}
