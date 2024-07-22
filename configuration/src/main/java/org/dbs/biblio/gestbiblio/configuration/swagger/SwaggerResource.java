package org.dbs.biblio.gestbiblio.configuration.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class SwaggerResource {
    /**
     * Default constructor
     */
    public SwaggerResource() {
        super();
    }

    @RequestMapping(value = "/")
    public String index() {
        log.info("swagger-ui");
        return "redirect:swagger-ui/index.html";
    }

}
