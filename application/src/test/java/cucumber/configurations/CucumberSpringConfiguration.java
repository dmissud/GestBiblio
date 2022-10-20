package cucumber.configurations;

import io.cucumber.spring.CucumberContextConfiguration;
import org.dbs.biblio.gestbiblio.SpringTestConfig;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {SpringTestConfig.class})
public class CucumberSpringConfiguration {
}
