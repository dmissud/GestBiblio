package org.dbs.biblio.gestbiblio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@Slf4j
@EnableCaching
public class LineCmdApplication {

    public static void main(String[] args) {
        SpringApplication.run(LineCmdApplication.class);
    }

}
