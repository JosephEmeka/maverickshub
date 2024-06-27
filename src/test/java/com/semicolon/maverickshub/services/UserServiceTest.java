package com.semicolon.maverickshub.services;

import com.semicolon.maverickshub.dtos.response.CreateUserResponse;
import com.semicolon.maverickshub.dtos.requests.CreateUserRequest;
import com.semicolon.maverickshub.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void registerTest(){
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("62787727");
        CreateUserResponse response = userService.register(request);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("user Registered Successfully"));
    }

    @Test
    @DisplayName("test that user can be retrieved")
    @Sql(scripts = {"/db/data.sql"})
    public void testGetUserId(){
        User user = userService.getById(200L);
        assertThat(user).isNotNull();
    }
}
