package com.harambase.hackathon.security;

import com.harambase.hackathon.security.model.User;
import com.harambase.hackathon.server.pojo.base.Person;
import com.harambase.hackathon.server.service.PersonServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by fan.jin on 2016-10-31.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonServerService personServerService;

    @Autowired
    public CustomUserDetailsService(PersonServerService personServerService) {
        this.personServerService = personServerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person user = (Person) personServerService.retrieveByKeyword(username).getData();
        User userDetail = new User(user);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return userDetail;
        }
    }

}
