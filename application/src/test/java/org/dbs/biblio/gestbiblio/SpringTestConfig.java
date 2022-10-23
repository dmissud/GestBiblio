package org.dbs.biblio.gestbiblio;

import org.dbs.biblio.gestbiblio.application.port.out.AdherentRepository;
import org.dbs.biblio.gestbiblio.application.port.out.ExemplaireRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cucumber", "org.dbs.biblio.gestbiblio"})
public class SpringTestConfig {
    @MockBean
    private AdherentRepository adherentRepository;
    @MockBean
    private ExemplaireRepository exemplaireRepository;

    public static void main(String[] args) {

        SpringApplication.run(SpringTestConfig.class, args);
    }
}
