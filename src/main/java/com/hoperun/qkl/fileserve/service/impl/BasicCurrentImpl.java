package com.hoperun.qkl.fileserve.service.impl;

import com.hoperun.qkl.fileserve.domain.User;
import com.hoperun.qkl.fileserve.repository.UserRepository;
import com.hoperun.qkl.fileserve.service.BasicCurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BasicCurrentImpl implements BasicCurrentUser {

    private UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        return userRepository.findByLogin(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
