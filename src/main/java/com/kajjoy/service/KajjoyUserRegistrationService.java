package com.kajjoy.service;

import com.kajjoy.model.AuthenticatedUser;
import com.kajjoy.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KajjoyUserRegistrationService {

    @Resource
    private UserRepository userRepository;

    public void register(String user,String pwd){
        userRepository.save(new AuthenticatedUser(user,pwd));
    }

}
