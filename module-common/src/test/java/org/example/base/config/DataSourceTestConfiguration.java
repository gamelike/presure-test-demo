package org.example.base.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class DataSourceTestConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public PostgreSQLContainer<?> postgreSQLContainer() {
        //noinspection resource
        return new PostgreSQLContainer<>("postgres:latest").waitingFor(Wait.forListeningPort());
    }

    @Bean
    public HikariDataSource hikariDataSource(PostgreSQLContainer<?> container) {
       var hikariDataSource = new HikariDataSource();
       hikariDataSource.setJdbcUrl(container.getJdbcUrl());
       hikariDataSource.setUsername(container.getUsername());
       hikariDataSource.setPassword(container.getPassword());
       return hikariDataSource;
    }

}
