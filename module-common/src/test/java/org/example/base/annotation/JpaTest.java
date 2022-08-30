package org.example.base.annotation;

import org.example.base.config.DataSourceTestConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author violet
 */
@AutoConfigureTestDatabase
@DataJpaTest
@Import(DataSourceTestConfiguration.class)
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JpaTest {
}
