package com.yuvraj.user.client.remote;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignUpClientTest {
    private SignUpClient signUpClient;

    @BeforeEach
    void setUp() {
        this.signUpClient = new SignUpClient("http://localhost:8080/api/v1");
    }

    @AfterEach
    void tearDown() {
        this.signUpClient = null;
    }

    @Test
    void sendOTP() {
        var sessionId = signUpClient.sendOTP("singh.yuvraj.yuvr@gmail.com").join();
        System.out.println(sessionId);
    }
}