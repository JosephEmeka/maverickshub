package com.semicolon.security.services;

import com.semicolon.maverickshub.exceptions.UserNotFoundException;
import com.semicolon.maverickshub.models.User;
import com.semicolon.maverickshub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userService.getUserByUsername(username);
        }
        catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return null;

    }


}
