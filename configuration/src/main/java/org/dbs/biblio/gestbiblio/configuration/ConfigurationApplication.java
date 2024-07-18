package org.dbs.biblio.gestbiblio.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableCaching
public class ConfigurationApplication {

    private static final String HTTP_DEFAULT_PORT = "8080";

    public static void main(String[] args) throws UnknownHostException {

        final SpringApplication ligneCmdApplication = new SpringApplication(ConfigurationApplication.class);
        logApplicationStartup(ligneCmdApplication.run(args).getEnvironment());
    }

    private static void logApplicationStartup(ConfigurableEnvironment environment) throws UnknownHostException {
        String protocol = "http";
        if (environment.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        final String serverPort = Optional.ofNullable(environment.getProperty("server.port")).orElse(HTTP_DEFAULT_PORT);
        final String contextPath =  Optional.ofNullable(environment.getProperty("server.servlet.context-path")).orElse("/");
        final String hostAddress = InetAddress.getLocalHost().getHostAddress();

        log.info("""
                --------------------------------------------------
                Application '{} ({}) is runnning !
                Access URLs:
                Local: \t{}://localhost:{}{}
                External: \t{}://{}:{}{}
                Profile(s): \t{}
                --------------------------------------------------
                        """,
                environment.getProperty("application.name", "as noName"),
                environment.getProperty("application.version", "VO.O.O"),
                protocol, serverPort, contextPath,
                protocol, hostAddress, serverPort, contextPath,
                environment.getActiveProfiles());
    }
}
