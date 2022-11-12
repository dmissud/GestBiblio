package org.dbs.biblio.gestbiblio;

import org.dbs.biblio.gestbiblio.application.port.out.MemberRepository;
import org.dbs.biblio.gestbiblio.application.port.out.CopyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cucumber", "org.dbs.biblio.gestbiblio"})
public class SpringTestConfig {
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private CopyRepository copyRepository;

    public static void main(String[] args) {

        SpringApplication.run(SpringTestConfig.class, args);
    }
}
