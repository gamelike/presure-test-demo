package org.example.base.annotation;

import org.example.base.config.DataSourceTestConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * @author violet
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@DataJpaTest
@Import(DataSourceTestConfiguration.class)
@AutoConfigureTestDatabase(replace = NONE)
public @interface JpaTest {
}
