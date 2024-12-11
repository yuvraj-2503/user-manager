package com.yuvraj.user.client.urls;

import com.yuvraj.user.common.Env;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLocatorClientTest {

    private ServiceLocatorClient client;

    @BeforeEach
    void setUp() {
        this.client = new ServiceLocatorClient("http://localhost:8080/api/v1", Env.LOCAL);
    }

    @AfterEach
    void tearDown() {
        this.client = null;
    }

    @Test
    void get() {
        var map = client.get().join();
        assertNotNull(map);
        for(String x: map.keySet()){
            System.out.println(x + " `" + map.get(x) + "`" );
        }
    }
}