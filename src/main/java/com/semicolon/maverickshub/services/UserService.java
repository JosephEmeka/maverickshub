package com.semicolon.maverickshub.services;


import com.semicolon.maverickshub.dtos.requests.CreateUserRequest;
import com.semicolon.maverickshub.dtos.response.CreateUserResponse;
import com.semicolon.maverickshub.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
 CreateUserResponse register(CreateUserRequest request);

 User getById(Long userId);

 User getUserByUsername(String username) throws UsernameNotFoundException;

}


