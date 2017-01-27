package com.kajjoy.service;

import com.kajjoy.model.AuthenticatedUser;
import com.kajjoy.repo.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Transactional
public class KajjoyUserDetailsService implements UserDetailsService,Serializable {

    private UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(KajjoyUserDetailsService.class);

    public KajjoyUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            AuthenticatedUser user = userRepository.findByUsername(s);
            if (null == user) {
                logger.debug("User not found with username: " + s);
                return null;
            }
            logger.debug("user from username: " + user.toString());
            return new User(user.getUsername(), user.getPassword(), getAuthorities());
        } catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        authorities.add(grantedAuthority);
        return authorities;
    }

}
