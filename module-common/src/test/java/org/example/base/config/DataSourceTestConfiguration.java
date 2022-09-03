package org.example.base.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class DataSourceTestConfiguration {

    static {
        //noinspection resource
        GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:latest")).withExposedPorts(6379);
        redis.start();
        System.setProperty("spring.redis.host", redis.getHost());
        System.setProperty("spring.redis.port", redis.getMappedPort(6379).toString());
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public PostgreSQLContainer<?> postgreSQLContainer() {
        //noinspection resource
        return new PostgreSQLContainer<>("postgres:latest").waitingFor(Wait.forListeningPort());
    }

    @Bean
    @FlywayDataSource
    public HikariDataSource hikariDataSource(PostgreSQLContainer<?> container) {
       var hikariDataSource = new HikariDataSource();
       hikariDataSource.setJdbcUrl(container.getJdbcUrl());
       hikariDataSource.setUsername(container.getUsername());
       hikariDataSource.setPassword(container.getPassword());
       return hikariDataSource;
    }

}
