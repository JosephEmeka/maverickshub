package com.semicolon.maverickshub.services;

import com.semicolon.maverickshub.dtos.requests.CreateUserRequest;
import com.semicolon.maverickshub.dtos.response.CreateUserResponse;
import com.semicolon.maverickshub.exceptions.UserNotFoundException;
import com.semicolon.maverickshub.models.User;
import com.semicolon.maverickshub.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MavericksHubUserServices implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

//    public MavericksHubUserServices(UserRepository userRepository, ModelMapper modelMapper,  PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.modelMapper = modelMapper;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public CreateUserResponse register(CreateUserRequest request){
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        CreateUserResponse response = modelMapper.map(savedUser, CreateUserResponse.class);
        response.setMessage("user Registered Successfully");
        return response;
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                String.format("User with id %d not found", userId)
                ));
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new UserNotFoundException("User not found"));
    }
}