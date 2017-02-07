package com.kajjoy.service;

import com.kajjoy.model.AuthenticatedUser;
import com.kajjoy.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KajjoyUserRegistrationService {

    @Resource
    private UserRepository userRepository;

    public boolean register(final String username,final String pwd){
        if(userRepository.findByUsername(username) == null){
            userRepository.save(new AuthenticatedUser(username,pwd));
            return true;
        }
        return false;
    }

}
