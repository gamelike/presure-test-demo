package org.example;

import org.assertj.core.api.Assertions;
import org.example.base.annotation.BaseTest;
import org.example.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author violet
 */
@BaseTest
@SpringBootTest
public class ApplicationTest {

    @MockBean
    UserRepository userRepository;

    @Test
    public void application_smoke_test() {
        Assertions.assertThat(userRepository).isNotNull();
    }

}
