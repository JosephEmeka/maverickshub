package com.semicolon.maverickshub.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() {

        String email = "test@gmail.com";
        String response = mailService.sendMail(email);
        assertThat(response).isNotNull();
    }
}
