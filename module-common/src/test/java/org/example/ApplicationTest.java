package org.example;

import org.assertj.core.api.Assertions;
import org.example.base.annotation.BaseTest;
import org.example.infrastructure.model.po.User;
import org.example.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

/**
 * @author violet
 */
@BaseTest
public class ApplicationTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void application_smoke_test() {
        Assertions.assertThat(userRepository).isNotNull();
    }

}
