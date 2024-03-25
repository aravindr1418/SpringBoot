package com.aravind.Springboot;

import org.junit.jupiter.api.Test;

import static com.aravind.Springboot.Abstracttestcontainers.postgreSQLContainer;
import static org.assertj.core.api.Assertions.assertThat;


public class TestcontainerTest {

    @Test
    void canStartpostgresDB() {

        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
    }


}
